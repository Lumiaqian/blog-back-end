package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
  /* @Results({@Result(column = "user_id",property = "userId"),@Result(column = "user_name",property = "userName"),
   @Result(column = "password",property = "password"),@Result(column = "email",property = "email"),
   @Result(column = "role_id",property = "roles",many = @Many(select = "com.caoyuqian.blog.pojo.Role"))})
   @Select("")*/
   SysUser getUserById(@Param("user_id") String userId);

   //@Select("select count(*) from user")
   public int test();
}
