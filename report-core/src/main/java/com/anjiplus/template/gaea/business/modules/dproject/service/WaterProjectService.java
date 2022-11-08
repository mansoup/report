package com.anjiplus.template.gaea.business.modules.dproject.service;

import com.anji.plus.gaea.curd.service.GaeaBaseService;
import com.anjiplus.template.gaea.business.modules.dproject.controller.param.SupervisorParam;
import com.anjiplus.template.gaea.business.modules.dproject.controller.param.WaterProjectParam;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.Supervisor;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProject;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.WaterProjectDto;
import com.anjiplus.template.gaea.business.modules.file.entity.GaeaFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface WaterProjectService extends GaeaBaseService<WaterProjectParam, WaterProject> {
    /**
    @Author soup
    @Date 2022/04/25
    @Description
    */
    public List<WaterProject> queryAllProject();
//    /**
//    @Author soup
//    @Date 2022/4/26
//    @Description 插入供水工程数据
//    */
//    public WaterProject insert(WaterProject waterProject);
    /**
    @Author soup
    @Date 2022/05/01
    @Description  对插入数据经纬度判断是否插入同一工程
    */
    public Boolean isSameProject(WaterProject waterProject);

    /**
     * 文件上传
     *
     * @param multipartFile  文件
     * @param file 文件
     * @param customFileName 自定义文件名，默认给null
     * @return
     */
    GaeaFile upload(MultipartFile multipartFile, File file, String customFileName);

    /**
     * 文件上传
     *
     * @param multipartFile  文件
     * @return
     */
    GaeaFile upload(MultipartFile multipartFile);


    /**
     * 文件上传
     *
     * @param file 二选一
     * @param customFileName 自定义文件名
     * @return
     */
    GaeaFile upload(File file, String customFileName);
    /**
     * 根据fileId显示图片或者下载文件
     *
     * @param request
     * @param response
     * @param fileId
     * @return
     */

    ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, String fileId);
    /**
    @Author soup
    @Date 2022/09/05
    @Description 修改后的imgurl并保存
    */
    public void changeUrl(Integer rowid,String url);

}
