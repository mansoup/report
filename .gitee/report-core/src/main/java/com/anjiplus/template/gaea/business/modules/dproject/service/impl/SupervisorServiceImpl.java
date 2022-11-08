package com.anjiplus.template.gaea.business.modules.dproject.service.impl;

import com.anji.plus.gaea.curd.mapper.GaeaBaseMapper;
import com.anjiplus.template.gaea.business.modules.dproject.dao.entity.Supervisor;
import com.anjiplus.template.gaea.business.modules.dproject.dao.mapper.SupervisorMapper;
import com.anjiplus.template.gaea.business.modules.dproject.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService{
    @Autowired
    private SupervisorMapper supervisorMapper;
    @Override
    public GaeaBaseMapper<Supervisor> getMapper() {
        return supervisorMapper;
    }
}
