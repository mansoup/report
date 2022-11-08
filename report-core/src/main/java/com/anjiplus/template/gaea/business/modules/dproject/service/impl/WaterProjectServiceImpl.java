package com.anjiplus.template.gaea.business.modules.dproject.service.impl;

import com.anji.plus.gaea.constant.BaseOperationEnum;
import com.anji.plus.gaea.curd.mapper.GaeaBaseMapper;
import com.anji.plus.gaea.exception.BusinessException;
import com.anji.plus.gaea.exception.BusinessExceptionBuilder;
import com.anjiplus.template.gaea.business.code.ResponseCode;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProject;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProjectDto;
import com.anjiplus.template.gaea.business.modules.dproject.dao.mapper.WaterProjectDtoMapper;
import com.anjiplus.template.gaea.business.modules.dproject.dao.mapper.WaterProjectMapper;
import com.anjiplus.template.gaea.business.modules.dproject.service.WaterProjectService;
import com.anjiplus.template.gaea.business.modules.file.dao.GaeaFileMapper;
import com.anjiplus.template.gaea.business.modules.file.entity.GaeaFile;
import com.anjiplus.template.gaea.business.modules.file.util.FileUtils;
import com.anjiplus.template.gaea.business.modules.file.util.StringPatternUtil;
import com.anjiplus.template.gaea.business.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WaterProjectServiceImpl implements WaterProjectService{
    //上传文件路径
    @Value("${customer.file.dist-path:''}")
    private String dictPath;
    //没用到
    @Value("${customer.file.white-list:''}")
    private String whiteList;

    @Value("${customer.file.excelSuffix:''}")
    private String excelSuffix;
    //下载文件路径
    @Value("${customer.file.mydownloadPath:''}")
    private String fileDownloadPath;
    @Resource
    WaterProjectMapper waterProjectMapper;
    @Autowired
    private GaeaFileMapper gaeaFileMapper;
    @Override
    public List<WaterProject> queryAllProject() {
        return waterProjectMapper.selectList(null);
    }



//    @Override
//    public WaterProject insert(WaterProject waterProject) {
//        Boolean sameProject = isSameProject(waterProject);
//        //没有相同经纬度的工程
//        if(sameProject==false){
//            //还要进行对整张表的拆分
//            waterProjectMapper.insert(waterProject);
//            //获取插入dto的id
//            Integer id = waterProject.getId();
//            return waterProjectMapper.selectById(id);
//        }else{
//            //疑似同一个工程 后续改如何处理？
////            return waterProjectDto;
//            throw BusinessExceptionBuilder.build(ResponseCode.SAME_PROJECT);
//        }
//
//    }

    @Override
    public Boolean isSameProject(WaterProject waterProject) {
        //输入dto的经纬度
        BigDecimal latitude = waterProject.getLatitude();
        BigDecimal longitude = waterProject.getLongitude();

        //数据库是否存在这样经纬度 用条件构造器 精确到小数点后两位数 误差经纬1000m*1500m where latitude属于[latitude-0.005,latitude+0.005]
        QueryWrapper<WaterProject> wrapper = new QueryWrapper<>();
//        BigDecimal testLatitude = latitude-0.005;
        //添加一个偏差 BigDecimal不支持直接减法
        BigDecimal bias = new BigDecimal(0.005);
        BigDecimal latitudeSub = latitude.subtract(bias);
        BigDecimal latitudeAdd = latitude.add(bias);
        BigDecimal longitudeSub = longitude.subtract(bias);
        BigDecimal longitudeAdd = longitude.add(bias);
        wrapper.between("latitude",latitudeSub,latitudeAdd);
        wrapper.between("longitude",longitudeSub,longitudeAdd);
        List<WaterProject> waterProjects = waterProjectMapper.selectList(wrapper);
        //没有找到相似经纬度工程
        if(waterProjects.size()==0){
            return false;
        }else{
            return  true;
        }
    }


    @Override
//    @Transactional(rollbackFor = Exception.class)
    public GaeaFile upload(MultipartFile multipartFile, File file, String customFileName) {
        try {
            String fileName = "";
            if (null != multipartFile) {
                fileName = multipartFile.getOriginalFilename();//获取上传源文件名称
                System.out.println("fileName:"+fileName);
            }else {
                fileName = file.getName();
            }
            //如果没有文件名抛出异常
            if (StringUtils.isBlank(fileName)) {
                throw BusinessExceptionBuilder.build(ResponseCode.FILE_EMPTY_FILENAME);
            }

            String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
            String fileInstruction = fileName.substring(0, fileName.lastIndexOf("."));//文件原名不带扩展
            //白名单校验(不区分大小写) 没用到
            List<String> list = new ArrayList<>(Arrays.asList(whiteList.split("\\|")));
            list.addAll(list.stream().map(String::toUpperCase).collect(Collectors.toList()));
            if (!list.contains(suffixName)) {
                throw BusinessExceptionBuilder.build(ResponseCode.FILE_SUFFIX_UNSUPPORTED);
            }
            // 生成文件唯一性标识
            String fileId;
            if (StringUtils.isBlank(customFileName)) {
                fileId = UUID.randomUUID().toString();
            } else {
                fileId = customFileName;
            }
            String newFileName = fileId + suffixName;
            // 本地文件保存路径

            String filePath = dictPath + newFileName;
            System.out.println("filePath:"+filePath);
            String urlPath = fileDownloadPath + "/" + fileId;


            System.out.println("urlPath"+urlPath);
            GaeaFile gaeaFile = new GaeaFile();
            gaeaFile.setFilePath(filePath);
            gaeaFile.setFileId(fileId);
            gaeaFile.setUrlPath(urlPath);
            gaeaFile.setFileType(suffixName.replace(".", ""));
            gaeaFile.setFileInstruction(fileInstruction);
            gaeaFileMapper.insert(gaeaFile);

            //写文件 将文件保存/app/dictPath/upload/下
            java.io.File dest = new java.io.File(dictPath + newFileName);
            System.out.println("dest文件目录"+dest);
            System.out.println("multipartFile不存在？"+multipartFile);
            java.io.File parentFile = dest.getParentFile();//得到父文件夹
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (null != multipartFile) {
                multipartFile.transferTo(dest);
            }else {
                FileUtil.copyFileUsingFileChannels(file, dest);
            }
            // 将完整的http访问路径返回
            System.out.println("gaeaFile"+gaeaFile);
            return gaeaFile;
        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("file upload error: {}", e);
            throw BusinessExceptionBuilder.build(ResponseCode.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public GaeaFile upload(MultipartFile multipartFile) {
        return upload(multipartFile, null, null);
    }

    @Override
    public GaeaFile upload(File file, String customFileName) {
        return upload(null, file, customFileName);
    }

    @Override
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, String fileId) {
        try {
            String userAgent = request.getHeader("User-Agent");
            boolean isIeBrowser = userAgent.indexOf("MSIE") > 0;
            //根据fileId，从gaea_file中读出filePath
            LambdaQueryWrapper<GaeaFile> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(GaeaFile::getFileId, fileId);
            GaeaFile gaeaFile = gaeaFileMapper.selectOne(queryWrapper);
            if (null == gaeaFile) {
                throw BusinessExceptionBuilder.build(ResponseCode.FILE_ONT_EXSIT);
            }
            //解析文件路径、文件名和后缀
            String filePath = gaeaFile.getFilePath();
            System.out.println("filePath"+filePath);
            if (StringUtils.isBlank(filePath)) {
                throw BusinessExceptionBuilder.build(ResponseCode.FILE_ONT_EXSIT);
            }
            String filename = filePath.substring(filePath.lastIndexOf(File.separator));
            String fileSuffix = filename.substring(filename.lastIndexOf("."));

            //根据文件后缀来判断，是显示图片\视频\音频，还是下载文件
            File file = new File(filePath);
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
            builder.contentLength(file.length());
            if (StringPatternUtil.stringMatchIgnoreCase(fileSuffix, "(.png|.jpg|.jpeg|.bmp|.gif|.icon)")) {
                builder.cacheControl(CacheControl.noCache()).contentType(MediaType.IMAGE_PNG);
            } else if (StringPatternUtil.stringMatchIgnoreCase(fileSuffix, "(.flv|.swf|.mkv|.avi|.rm|.rmvb|.mpeg|.mpg|.ogg|.ogv|.mov|.wmv|.mp4|.webm|.wav|.mid|.mp3|.aac)")) {
                builder.header("Content-Type", "video/mp4; charset=UTF-8");
            } else {
                //application/octet-stream 二进制数据流（最常见的文件下载）
                builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
                filename = URLEncoder.encode(filename, "UTF-8");
                if (isIeBrowser) {
                    builder.header("Content-Disposition", "attachment; filename=" + filename);
                } else {
                    builder.header("Content-Disposition", "attacher; filename*=UTF-8''" + filename);
                }
            }
            return builder.body(FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            log.error("file download error: {}", e);
            return null;
        }
    }

    @Override
    public void changeUrl(Integer rowid,String url) {
        //根据查询的waterpriject id 获取该工程
        WaterProject waterProject = waterProjectMapper.selectById(rowid);
        waterProject.setImgUrl(url);
        waterProjectMapper.insert(waterProject);

    }


    @Override
    public GaeaBaseMapper<WaterProject> getMapper() {
        return waterProjectMapper;
    }

}
