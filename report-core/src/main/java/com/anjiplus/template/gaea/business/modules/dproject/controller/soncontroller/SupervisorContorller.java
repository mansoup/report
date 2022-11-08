package com.anjiplus.template.gaea.business.modules.dproject.controller.soncontroller;


import com.anji.plus.gaea.bean.ResponseBean;
import com.anji.plus.gaea.curd.controller.GaeaBaseController;
import com.anji.plus.gaea.curd.service.GaeaBaseService;
import com.anji.plus.gaea.utils.GaeaBeanUtils;
import com.anji.plus.gaea.utils.GaeaUtils;
import com.anjiplus.template.gaea.business.modules.dproject.controller.dto.SupervisorDto;
import com.anjiplus.template.gaea.business.modules.dproject.controller.param.SupervisorParam;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.Supervisor;
import com.anjiplus.template.gaea.business.modules.dproject.service.SupervisorService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/supervisor")
public class SupervisorContorller extends GaeaBaseController<SupervisorParam, Supervisor, SupervisorDto>{
    @Resource
    SupervisorService supervisorService;
    @Override
    public GaeaBaseService<SupervisorParam, Supervisor> getService() {
        return supervisorService;
    }

    @Override
    public Supervisor getEntity() {
        return new Supervisor();
    }

    @Override
    public SupervisorDto getDTO() {
        return new SupervisorDto();
    }
//    @Override
//    @GetMapping({"/{id}"})
//    public ResponseBean detail(@PathVariable("id") Long id) {
//        this.logger.info("{}根据ID查询服务开始，id为：{}", this.getClass().getSimpleName(), id);
//        Supervisor result = this.getService().selectOne(id);
//        SupervisorDto dto = this.getDTO();
//        System.out.println("result:"+result);
//        System.out.println("dto:"+dto);
//        GaeaBeanUtils.copyAndFormatter(result, dto);
//        this.detailResultHandler(dto);
//        ResponseBean responseBean = this.responseSuccessWithData(this.resultDtoHandle(dto));
//        this.logger.info("{}根据ID查询结束，结果：{}", this.getClass().getSimpleName(), GaeaUtils.toJSONString(responseBean));
//        return responseBean;
//    }

}
