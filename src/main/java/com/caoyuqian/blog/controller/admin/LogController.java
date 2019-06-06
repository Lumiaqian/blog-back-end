package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.pojo.Log;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @version V1.0
 * @Title: LogController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: TOTO
 * @date 2019-05-28 10:55
 **/
@RestController("admin/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("list")
    public JsonResult getAllLog(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize){
        JsonResult jsonResult = new JsonResult();
        PageInfo<Log> logPageInfo = logService.selectAllLog(pageNum,pageSize);
        if (logPageInfo.getSize()>0){
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(logPageInfo);
        }else {
            jsonResult = new JsonResult(ResultCode.SYS_ERROR);
        }
        return jsonResult;
    }
}
