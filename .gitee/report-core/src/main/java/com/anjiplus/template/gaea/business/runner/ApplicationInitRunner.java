package com.anjiplus.template.gaea.business.runner;

import com.anjiplus.template.gaea.business.modules.accessauthority.service.AccessAuthorityService;
import com.anjiplus.template.gaea.business.modules.dict.service.GaeaDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @desc 启动加载器
 * @author WongBin <abc13579d@163.com>
 * @date 2019-02-17 08:50:10.009
 **/
/**
@Author soup
@Date 2022/04/29
@Description Spring boot项目启动后需要马上执行一些任务，比如初始化的任务 ApplicationRunner springBoot项目启动时，若想在启动之后直接执行某一段代码，就可以用 ApplicationRunner这个接口，并实现接口里面的run(ApplicationArguments args)方法，方法中写上自己的想要的代码逻辑。
*/
public class ApplicationInitRunner implements ApplicationRunner {

    @Autowired
    private GaeaDictService gaeaDictService;

    @Autowired
    private AccessAuthorityService accessAuthorityService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //1、数据字典刷新
        // gaeaDictService.refreshCache(null);

        //2. 建立url权限拦截体系
        accessAuthorityService.scanGaeaSecurityAuthorities();
    }
}
