package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.dto.CreateResourceRequest;
import com.caoyuqian.user.dto.UpdateResourceRequest;
import com.caoyuqian.user.entity.Resource;
import com.caoyuqian.user.entity.Role;
import com.caoyuqian.user.mapper.ResourceMapper;
import com.caoyuqian.user.service.ResourceService;
import com.caoyuqian.user.service.RoleResourceService;
import com.caoyuqian.user.service.RoleService;
import com.caoyuqian.user.vo.ResourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: ResourceServiceImpl
 * @Package: com.caoyuqian.user.service.impl
 * @Description: ResourceServiceImpl
 * @date 2020/10/21 2:31 下午
 **/
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper,Resource> implements ResourceService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Override
    public List<ResourceVo> getByUserId(Long userId) {
        //校验参数
        if (userId == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        //先根据userId查询出用户拥有的角色
        List<Role> roles = roleService.getByUserId(userId);
        if (CollectionUtils.isEmpty(roles)){
            throw new ServiceException(Status.NOT_ACTIVE);
        }
        //根据roleId查询该角色拥有的资源Id集合
        Set<Long> resourceIdList = new HashSet<>();

        roles.forEach(role -> {
            Set<Long> resourceIds = roleResourceService.getByRoleId(role.getRoleId());
            resourceIdList.addAll(resourceIds);
        });

        if (CollectionUtils.isEmpty(resourceIdList)){
            throw new ServiceException(Status.NOT_ACTIVE);
        }

        //根据资源id集合查询资源集合
        List<Resource> resources = this.listByIds(resourceIdList);


        return resources.stream().map(resource -> {
            ResourceVo resourceVo = new ResourceVo();
            BeanUtils.copyProperties(resource,resourceVo);
            return resourceVo;
        }).collect(Collectors.toList());
    }

    @Override
    public void saveResource(CreateResourceRequest createResourceRequest) {
        if (createResourceRequest == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Resource resource = new Resource();
        BeanUtils.copyProperties(createResourceRequest,resource);
        this.save(resource);
    }

    @Override
    public void updateResourceById(UpdateResourceRequest updateResourceRequest) {
        if (updateResourceRequest == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(updateResourceRequest,resource);

        this.updateById(resource);

    }

    @Override
    public void deleteResourceById(Long resourceId) {
        if (resourceId == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        this.removeById(resourceId);
    }
}
