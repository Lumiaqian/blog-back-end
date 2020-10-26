package com.caoyuqian.gatewaysvc.component;

import com.caoyuqian.common.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author qian
 * @version V1.0
 * @Title: AuthorizationManager
 * @Package: com.caoyuqian.gatewaysvc.component
 * @Description: 资源管理器
 * @date 2020/10/26 2:45 下午
 **/
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();


        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        //对于token为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        //对于非管理端路径无需鉴权直接放行
        if (!antPathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, path)) {
            return Mono.just(new AuthorizationDecision(true));
        }


        return mono.map(authentication -> new AuthorizationDecision(checkAuthorities(authentication,path)))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * @param auth
     * @param requestPath
     * @return boolean
     * @Description: 权限校验
     * @version 0.1.0
     * @author qian
     * @date 2020/10/26 3:27 下午
     * @since 0.1.0
     */
    private boolean checkAuthorities(Authentication auth, String requestPath) {

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();


        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(item -> !item.startsWith(AuthConstants.AUTHORITY_PREFIX))
                .anyMatch(permission -> antPathMatcher.match(permission,requestPath));
    }

}
