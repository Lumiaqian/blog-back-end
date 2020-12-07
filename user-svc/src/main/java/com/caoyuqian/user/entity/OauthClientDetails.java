package com.caoyuqian.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端信息
 * @author lumiaqian
 * @date 2020/08/03 20:04
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

	private static final long serialVersionUID =  4650243107906904668L;

	/**
	 * 客户端ID
	 */
    @TableId(value = "client_id",type = IdType.ASSIGN_ID)
    private String clientId;


	/**
	 * 资源ID集合,多个资源时用逗号(,)分隔
	 */
    @TableField("resource_ids")
    private String resourceIds;


	/**
	 * 客户端密匙
	 */
    @TableField("client_secret")
    private String clientSecret;


	/**
	 * 客户端申请的权限范围
	 */
    @TableField("scope")
    private String scope;


	/**
	 * 客户端支持的grant_type
	 */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;


	/**
	 * 重定向URI
	 */
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;


	/**
	 * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
	 */
    @TableField("authorities")
    private String authorities;


	/**
	 * 访问令牌有效时间值(单位:秒)
	 */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;


	/**
	 * 更新令牌有效时间值(单位:秒)
	 */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;


	/**
	 * 预留字段
	 */
    @TableField("additional_information")
    private String additionalInformation;


	/**
	 * 用户是否自动Approval操作
	 */
    @TableField("autoapprove")
    private String autoapprove;


}
