package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.aspect.Log;
import com.caoyuqian.blog.pojo.Acate;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.CategoryService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("admin/cates")
@Transactional
public class CateController {
    private final Logger logger = LoggerFactory.getLogger(CateController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult();
        List<Category> categories = categoryService.getCategories();
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            HashMap<String, Object> res = new HashMap<String, Object>();
            List<HashMap<String, Object>> children = new ArrayList<>();
            if (categories.get(i).getFatherId() == -1) {
                res.put("value", String.valueOf(categories.get(i).getCategoryId()));
                res.put("label", categories.get(i).getCategoryName());
                for (int j = 0; j < categories.size(); j++) {
                    if (categories.get(i).getCategoryId() == categories.get(j).getFatherId()) {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("value", String.valueOf(categories.get(j).getCategoryId()));
                        map.put("label", categories.get(j).getCategoryName());
                        children.add(map);
                    }
                }
                res.put("children", children);
                result.add(res);
            }
        }
        jsonResult.setMessage("获取categories列表成功！");
        jsonResult.setData(result);
        return jsonResult;
    }

    @GetMapping("alist")
    public JsonResult getCates(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult;
        PageInfo cates = categoryService.getCates(pageNo, pageSize);
        jsonResult = new JsonResult();
        jsonResult.setMessage("获取category列表成功！");
        jsonResult.setData(cates);
        return jsonResult;
    }
    @GetMapping("flist")
    public JsonResult getFatherCates(){
        JsonResult jsonResult;
        List<Category> categories=categoryService.getFatherCates();
        jsonResult =new JsonResult();
        jsonResult.setData(categories);
        jsonResult.setMessage("获取category列表成功！");
        return jsonResult;
    }
    @PostMapping("cate")
    @Log("添加分类")
    public JsonResult addCate(@RequestBody Acate acate){
        JsonResult jsonResult;
        int flag = categoryService.getCountByName(acate.getCateName());
        //flag =1;
        //logger.info(acate.toString());
        if (flag<1){
            SnowFlake snowFlake = new SnowFlake(2, 3);
            acate.setCateId(snowFlake.nextId());
            if (acate.getFatherId()==0){
                acate.setFatherId(-1);
            }
            categoryService.saveCate(acate);
            jsonResult = new JsonResult();
            jsonResult.setMessage("添加"+acate.getCateName()+"分类成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("该分类已经存在！");
        }
        return jsonResult;
    }
    @PutMapping("cate")
    @Log("更新分类")
    public JsonResult updateCate(@RequestBody Acate acate){
        JsonResult jsonResult;
        logger.info(acate.toString());
        acate.setEditDate(DateUtil.getNow());
        int code =categoryService.updateCate(acate);
        if (code>0){
            jsonResult = new JsonResult();
            jsonResult.setMessage("更新分类成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("更新分类失败！");
        }
        return jsonResult;
    }
    @DeleteMapping("cate/{cateId}")
    @Log("删除分类")
    public JsonResult deleteCate(@PathVariable long cateId){
        JsonResult jsonResult;
        Acate acate = new Acate();
        acate.setCateId(cateId);
        acate.setStatus(1);
        acate.setEditDate(DateUtil.getNow());
        int code =categoryService.updateCate(acate);
        if (code>0){
            jsonResult = new JsonResult();
            jsonResult.setMessage("删除分类成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("删除分类失败！");
        }
        return jsonResult;
    }
    @PutMapping("cate/{cateId}")
    @Log("恢复分类")
    public JsonResult recoveryCate(@PathVariable long cateId){
        JsonResult jsonResult;
        Acate acate = new Acate();
        acate.setCateId(cateId);
        acate.setStatus(0);
        acate.setEditDate(DateUtil.getNow());
        int code =categoryService.updateCate(acate);
        if (code>0){
            jsonResult = new JsonResult();
            jsonResult.setMessage("恢复分类成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("恢复分类失败！");
        }
        return jsonResult;
    }

}
