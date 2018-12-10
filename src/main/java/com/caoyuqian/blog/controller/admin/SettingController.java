package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.pojo.Setting;
import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.SettingService;
import com.caoyuqian.blog.service.UserService;
import com.caoyuqian.blog.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author qian
 * @version V1.0
 * @Title: SettingController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: TOTO
 * @date 2018-12-10 13:57
 **/
@RestController
@Transactional
@RequestMapping("admin")
public class SettingController {
    @Autowired
    private SettingService settingService;

    private final Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private UserService userService;

    @PostMapping("setting")
    public ResponseEntity<JsonResult> updateSetting(@RequestBody HashMap map) throws Exception {
        Setting setting = JSONUtil.mapToObj(map,Setting.class);
        String userId = (String) map.get("userId");
        SysUser user = userService.getUserById(userId);
        setting.setId(user.getSettingId());
        logger.info(setting.toString());
        settingService.updateSetting(setting);
        JsonResult jsonResult = new JsonResult();
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }
}
