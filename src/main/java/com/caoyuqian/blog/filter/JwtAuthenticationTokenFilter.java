package com.caoyuqian.blog.filter;

import com.caoyuqian.blog.config.AuthUserDetailsService;
import com.caoyuqian.blog.pojo.AuthUser;
import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: JwtAuthenticationTokenFilter
 * @Package: com.caoyuqian.blog.filter
 * @Description: Token过滤器
 * @date 2018/8/10 下午2:34
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token =request.getHeader("Authorization");
        //logger.info("token："+token);
        if (token!=null && token.startsWith("Bearer ")){
            final String authToken=token.substring("Bearer ".length());
            //logger.info("authToken: "+authToken);
            String userId= JwtTokenUtil.getUserIdFromToken(authToken);
            if (userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails =userDetailsService.loadUserByUsername(userId);
                if (userDetails!=null){
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
