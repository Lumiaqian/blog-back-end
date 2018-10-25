package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CateController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: 管理系统的分类api
 * @date 2018/10/9 下午8:09
 **/
@RestController
@RequestMapping("admin")
@Transactional
public class CateController {
    private final Logger logger= LoggerFactory.getLogger(CateController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping("cates/list")
    public JsonResult list(){
        JsonResult jsonResult=new JsonResult();
        List<Category> categories=categoryService.getCategories();
        List<HashMap<String,Object>> result=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            HashMap<String,Object> res=new HashMap<String, Object>();
            List<HashMap<String,Object>> children=new ArrayList<>();
            if (categories.get(i).getFatherId()==-1){
                res.put("value",String.valueOf(categories.get(i).getCategoryId()));
                res.put("label",categories.get(i).getCategoryName());
                for (int j=0;j<categories.size();j++){
                    if (categories.get(i).getCategoryId()==categories.get(j).getFatherId()){
                        HashMap<String,Object> map=new HashMap<String, Object>();
                        map.put("value",String.valueOf(categories.get(j).getCategoryId()));
                        map.put("label",categories.get(j).getCategoryName());
                        children.add(map);
                    }
                }
                res.put("children",children);
                result.add(res);
            }
        }
        jsonResult.setMessage("获取categories列表成功！");
        jsonResult.setData(result);
        return jsonResult;
    }
}
