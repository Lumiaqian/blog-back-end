package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.dto.UpdateUserRequest;
import com.caoyuqian.user.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author lumiaqian
 */
public interface UserMapper extends BaseMapper<User> {

   /* @Select("select user.* , user_role_relation.role_id from user left join user_role_relation on (user.id = user_role_relation.user_id) ${ew.customSqlSegment}")
    User getByMobile(@Param(Constants.WRAPPER) Wrapper wrapper);*/

    /**
     * @param user
     * @return java.lang.Integer
     * @Description: 更新用户
     * @version 0.1.0
     * @author qian
     * @date 2020/7/27 9:30 下午
     * @since 0.1.0
     */
    Integer updated(User user);
}
