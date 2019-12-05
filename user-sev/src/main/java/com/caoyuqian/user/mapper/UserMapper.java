package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.model.po.User;

public interface UserMapper extends BaseMapper<User> {

   /* @Select("select user.* , user_role_relation.role_id from user left join user_role_relation on (user.id = user_role_relation.user_id) ${ew.customSqlSegment}")
    User getByMobile(@Param(Constants.WRAPPER) Wrapper wrapper);*/
}
