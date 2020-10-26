package com.caoyuqian.user.rest;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.dto.CreateMenuRequest;
import com.caoyuqian.user.dto.UpdateMenuRequest;
import com.caoyuqian.user.service.MenuService;
import com.caoyuqian.user.vo.MenuTree;
import com.caoyuqian.user.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuController
 * @Package: com.caoyuqian.user.rest
 * @Description: MenuController
 * @date 2020/7/23 7:53 下午
 **/
@RestController
@RequestMapping("/v1/user")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("menu")
    //@PreAuthorize("hasAuthority('menu:view')")
    public Result findMenus(){
        return Result.success(menuService.findMenus());
    }

    @PostMapping("menu")
    //@PreAuthorize("hasAuthority('menu:add')")
    public Result create(@Valid @RequestBody CreateMenuRequest request){
        menuService.add(request);
        return Result.success();
    }

    @DeleteMapping("menu")
   // @PreAuthorize("hasAuthority('menu:delete')")
    public Result delete(List<Long> ids){
        menuService.deleteMenuList(ids);
        return Result.success();
    }

    @PutMapping("menu")
  //  @PreAuthorize("hasAuthority('menu:update')")
    public Result update(@Valid @RequestBody UpdateMenuRequest request){
        menuService.updateMenu(request);
        return Result.success();
    }
}
