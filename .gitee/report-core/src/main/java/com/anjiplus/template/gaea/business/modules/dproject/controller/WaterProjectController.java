package com.anjiplus.template.gaea.business.modules.dproject.controller;

import com.anji.plus.gaea.annotation.Permission;
import com.anji.plus.gaea.annotation.log.GaeaAuditLog;
import com.anji.plus.gaea.bean.ResponseBean;
import com.anji.plus.gaea.curd.controller.GaeaBaseController;
import com.anji.plus.gaea.curd.service.GaeaBaseService;
import com.anji.plus.gaea.utils.GaeaUtils;
import com.anjiplus.template.gaea.business.modules.dproject.controller.param.WaterProjectParam;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProject;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProjectDto;
import com.anjiplus.template.gaea.business.modules.dproject.service.WaterProjectService;
import com.anjiplus.template.gaea.business.modules.file.entity.GaeaFile;
import com.anjiplus.template.gaea.business.modules.file.service.GaeaFileService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Permission(code = "projectManage",name="工程管理")
@Api(tags = "工程管理")
@RequestMapping("/waterProject")
public class WaterProjectController extends GaeaBaseController<WaterProjectParam, WaterProject, WaterProjectDto>{

    @Resource
    WaterProjectService waterProjectService;
    @Autowired
    private GaeaFileService gaeaFileService;
    //默认创建一个空返回对象
    WaterProject waterProject= new WaterProject();
    String imgurl="";
    //查询整张大表
    @GetMapping("/queryAllProject")
    public  ResponseBean queryAllProject(){
//        return waterProjectDtoService.queryAllProject(); List<WaterProjectDto>
        return ResponseBean.builder().data(waterProjectService.queryAllProject()).build();
    }

    @Override
    @PostMapping
    @Permission(
            code = "insert",
            name = "新增"
    )
    @GaeaAuditLog(
            pageTitle = "新增"
    )
    public ResponseBean insert(@Validated @RequestBody WaterProjectDto waterProjectDto) {
        this.logger.info("重写的{}新增服务开始，参数：{}", this.getClass().getSimpleName(), GaeaUtils.toJSONString(waterProjectDto));
        ResponseBean responseBean = this.responseSuccess();
        waterProject = this.getEntity();
        BeanUtils.copyProperties(waterProjectDto, waterProject);
        waterProject.setImgUrl(imgurl);
        this.getService().insert(waterProject);
        this.logger.info("{}新增服务结束，结果：{}", this.getClass().getSimpleName(), GaeaUtils.toJSONString(responseBean));
        return responseBean;
    }
    //根据id查看
//    @GetMapping("/detailBysetId/{id}")
//    public ResponseBean detailBysetId(@PathVariable("id") Long id){
//        return null;
//    }
    //增加自定义上传文件路径功能并且保存到数据库路径
   @PostMapping("/upload")
   @Permission(code = "upload", name = "文件上传")
    public ResponseBean upload(@RequestParam("file") MultipartFile file,@RequestParam Integer rowid) {
       GaeaFile gaeaFile = new GaeaFile();
       gaeaFile=waterProjectService.upload(file);
       System.out.println("rowid"+rowid);
       if(rowid!=null){
//           WaterProject waterProject = waterProjectService.selectOne(rowid.longValue());
//           waterProject.setImgUrl(gaeaFile.getUrlPath());
           waterProjectService.changeUrl(rowid,gaeaFile.getUrlPath());
           //怎么将新对象保存在数据库
           System.out.println("getImgUrl"+waterProject.getImgUrl());
       }

       //获得该id的工程对象
//       System.out.println("myid"+id.longValue());
       if(gaeaFile!=null){
            imgurl =gaeaFile.getUrlPath();//将图片下载路径保存在该对象上
        }
//       this.getService().insert(waterProject);
        //        return null;
     return ResponseBean.builder().message("success").data((gaeaFile)).build();
   }
    @GetMapping(value = "/download/{fileId}")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId) {
        System.out.println("waterProjectService.download(request, response, fileId)"+waterProjectService.download(request, response, fileId));
        return waterProjectService.download(request, response, fileId);
    }

    @Override
    public GaeaBaseService<WaterProjectParam, WaterProject> getService() {
        return waterProjectService;
    }

    @Override
    public WaterProject getEntity() {
        return new WaterProject();
    }

    @Override
    public WaterProjectDto getDTO() {
        return new WaterProjectDto();
    }
}
