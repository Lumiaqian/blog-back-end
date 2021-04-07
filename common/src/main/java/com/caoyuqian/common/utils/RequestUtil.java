package com.caoyuqian.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.caoyuqian.common.constant.AuthConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: RequestUtil
 * @Package: com.caoyuqian.common.utils
 * @Description: TOTO
 * @date 2021/4/2 5:58 下午
 **/
@Slf4j
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static JSONObject getJwtPayload() {
        String jwtPayload = getRequest().getHeader(AuthConstants.JWT_PAYLOAD_KEY);
        return JSONUtil.parseObj(jwtPayload);
    }

    public static Long getUserId() {
        return getJwtPayload().getLong(AuthConstants.USER_ID_KEY);
    }


    public static String getUsername() {
        return getJwtPayload().getStr(AuthConstants.USER_NAME_KEY);
    }

    /**
     * 获取JWT的载体中的clientId
     *
     * @return
     */
    public static String getClientId() {
        return getJwtPayload().getStr(AuthConstants.CLIENT_ID_KEY);
    }

    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return
     */
    @SneakyThrows
    public static String getAuthClientId() {
        String clientId;

        HttpServletRequest request = getRequest();

        // 从请求路径中获取
        clientId = request.getParameter(AuthConstants.CLIENT_ID_KEY);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic;
        basic = request.getHeader(AuthConstants.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AuthConstants.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstants.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic), StandardCharsets.UTF_8);
            //client:secret
            clientId = basicPlainText.split(":")[0];
        }
        return clientId;
    }


    public static List<Long> getRoleIds() {
        List<String> list = getJwtPayload().get(AuthConstants.AUTHORITY_CLAIM_NAME, List.class);
        return list.stream().map(Long::valueOf).collect(Collectors.toList());
    }
}
