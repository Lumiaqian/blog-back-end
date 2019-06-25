package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.pojo.Device;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qian
 * @version V1.0
 * @Title: CountController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: TOTO
 * @date 2018/9/28 下午8:17
 **/
@RestController
@Transactional
@RequestMapping("admin")
public class CountController {
    private final Logger logger= LoggerFactory.getLogger(CountController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DeviceService deviceService;


    @GetMapping("count")
    public JsonResult getCount(){
        JsonResult jsonResult=new JsonResult();
        int postCount=postService.getCount();
        int postAllCount=postService.getAllCount();
        int tagCount=tagService.getCount();
        int cateCount=categoryService.getCount();
        int commentCount=commentService.getCount();
        Map<String,Integer> map=new HashMap<>(16);
        map.put("postCount",postCount);
        map.put("tagCount",tagCount);
        map.put("cateCount",cateCount);
        map.put("postAllCount",postAllCount);
        map.put("commentCount",commentCount);
        jsonResult.setData(map);
        return jsonResult;
    }
    @GetMapping("device/count")
    public JsonResult count(){
        JsonResult jsonResult = new JsonResult();
        List<HashMap<String, Object>> city = deviceService.getCityCount();
        List<HashMap<String, Object>> OS = deviceService.getOSCount();
        HashMap<String, Object> data = new HashMap<>(16);
        List<HashMap<String, Object>> Os = new LinkedList<>();
        AtomicInteger android = new AtomicInteger();
        AtomicInteger ios = new AtomicInteger();
        OS.forEach(os -> {
            String name = String.valueOf(os.get("os"));
            Number number = (Number) os.get("num");
            if (StringUtils.startsWith(name, "Android")) {

                android.addAndGet(number.intValue());
            } else if (StringUtils.endsWith(name, "(iPhone)")) {
                ios.addAndGet(number.intValue());
            } else {
                Os.add(os);
            }
        });
        HashMap<String, Object> Android = new HashMap<>(16);
        HashMap<String, Object> iOS = new HashMap<>(16);
        Android.put("os","Android");
        Android.put("num",android.intValue());
        iOS.put("os","iOS");
        iOS.put("num", ios.intValue());
        Os.add(Android);
        Os.add(iOS);
        data.put("os",Os);
        data.put("city",city);
        logger.debug("data: {}",data.toString());
        logger.debug("city: {}", city);
        logger.debug("OS: {}", Os);
        jsonResult.setData(data);
        return jsonResult;
    }
}
