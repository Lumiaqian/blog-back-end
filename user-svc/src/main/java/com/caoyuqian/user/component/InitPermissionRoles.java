package com.caoyuqian.user.component;

import com.caoyuqian.user.service.SysPermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: InitPermissionRoles
 * @Package: com.caoyuqian.user.component
 * @Description: 容器启动完成时加载角色权限规则至Redis缓存
 * @date 2021/5/22 5:26 下午
 **/
@Component
@AllArgsConstructor
@Slf4j
public class InitPermissionRoles implements CommandLineRunner {

    private SysPermissionService sysPermissionService;

    @Override
    public void run(String... args) {
        sysPermissionService.refreshPermissionRolesCache();
    }
}
