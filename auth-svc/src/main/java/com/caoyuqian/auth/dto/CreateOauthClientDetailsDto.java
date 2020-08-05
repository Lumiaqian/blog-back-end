package com.caoyuqian.auth.dto;




import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 客户端信息
 * @author lumiaqian
 * @date 2020/08/03 20:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOauthClientDetailsDto implements Serializable {

	private static final long serialVersionUID =  4650243107906904668L;

	/**
	 * 客户端ID
	 */
	@NotBlank(message = "clientId不能为空")
    private String clientId;


	/**
	 * 资源ID集合,多个资源时用逗号(,)分隔
	 */
    private String resourceIds;


	/**
	 * 客户端密匙
	 */
	@NotBlank(message = "clientSecret不能为空")
    private String clientSecret;


	/**
	 * 客户端申请的权限范围
	 */
    private String scope;


	/**
	 * 客户端支持的grant_type
	 */
    private String authorizedGrantTypes;


	/**
	 * 重定向URI
	 */
    private String webServerRedirectUri;


	/**
	 * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
	 */
    private String authorities;


	/**
	 * 访问令牌有效时间值(单位:秒)
	 */
    private Integer accessTokenValidity;


	/**
	 * 更新令牌有效时间值(单位:秒)
	 */
    private Integer refreshTokenValidity;


	/**
	 * 预留字段
	 */
    private String additionalInformation;


	/**
	 * 用户是否自动Approval操作
	 */
    private String autoapprove;


}
