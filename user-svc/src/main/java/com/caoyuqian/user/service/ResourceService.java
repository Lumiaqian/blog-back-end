package com.caoyuqian.user.service;

import com.caoyuqian.user.dto.CreateResourceRequest;
import com.caoyuqian.user.dto.UpdateResourceRequest;
import com.caoyuqian.user.vo.ResourceVo;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface ResourceService {

    /**
     * @param userId
     * @return java.util.List<com.caoyuqian.user.vo.ResourceVo>
     * @Description: 根据用户ID 获取资源信息
     * @version 0.1.0
     * @author qian
     * @date 2020/10/23 4:47 下午
     * @since 0.1.0
     */
    List<ResourceVo> getByUserId(Long userId);

    /**
     * @param createResourceRequest
     * @return void
     * @Description: 添加资源
     * @version 0.1.0
     * @author qian
     * @date 2020/10/23 4:48 下午
     * @since 0.1.0
     */
    void saveResource(CreateResourceRequest createResourceRequest);

    /**
     * @param updateResourceRequest
     * @return void
     * @Description: 更新
     * @version 0.1.0
     * @author qian
     * @date 2020/10/23 4:54 下午
     * @since 0.1.0
     */
    void updateResourceById(UpdateResourceRequest updateResourceRequest);

    /**
     * @param resourceId
     * @return void
     * @Description: 删除
     * @version 0.1.0
     * @author qian
     * @date 2020/10/23 4:55 下午
     * @since 0.1.0
     */
    void deleteResourceById(Long resourceId);

}
