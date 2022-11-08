package com.anjiplus.template.gaea.business.modules.project.dao.entity;

import com.anji.plus.gaea.curd.controller.GaeaBaseController;
import com.anji.plus.gaea.curd.service.GaeaBaseService;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.Supervisor;
import com.anjiplus.template.gaea.business.modules.dproject.dao.mapper.SupervisorMapper;
import com.anjiplus.template.gaea.business.modules.project.dao.entity.dto.SupervisorDto;
import com.anjiplus.template.gaea.business.modules.project.dao.entity.param.SupervisorParam;
import com.anjiplus.template.gaea.business.modules.project.dao.entity.service.SupervisorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RequestMapping("/test")
public class SupervisorTest /*extends GaeaBaseController<SupervisorParam,Supervisor, SupervisorDto>*/ {
    @Autowired
    private SupervisorMapper supervisorMapper;
//    @Autowired
//    private SupervisorService supervisorService;
    @Test
    public void test(){
        QueryWrapper<Supervisor> wrapper = new QueryWrapper<>();
        //这里字段名要和数据库一致 where name = "张%"
        wrapper.select("id","name","Phone_Number").likeRight("name","张");
        List<Map<String, Object>> maps = supervisorMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    //实体对象作为条件 调用构造函数创建一个Wrapper对象时，可以传入一个实体对象。后续使用这个Wrapper时，会以实体对象中的非空属性，构建WHERE条件
    @Test
    public void testEntity(){
        Supervisor supervisor = new Supervisor();
        supervisor.setName("刘福玲");
        supervisor.setPhoneNumber("13704216014");
        supervisor.setManagementDepartmentId(7941);
        QueryWrapper<Supervisor> wrapper = new QueryWrapper<>(supervisor);
        List<Supervisor> supervisors = supervisorMapper.selectList(wrapper);
        if(supervisors.size()!=0){
            System.out.println("数据库已经存在改对象");
        }else{
            System.out.println("准备插入该对象");
        }
    }

/*    public void testSearch(){
        SupervisorParam supervisorParam= new SupervisorParam();
        supervisorParam.setName("张");
        supervisorParam.setPhoneNumber("137");
        Supervisor supervisor = new Supervisor();
        SupervisorDto supervisorDto = new SupervisorDto();
        SupervisorTest g = new SupervisorTest();
        System.out.println(g.pageList(supervisorParam));;
    }*/
/*    @Override
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
    }*/
}
