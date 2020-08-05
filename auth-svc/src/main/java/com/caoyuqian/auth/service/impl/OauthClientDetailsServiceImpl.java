package com.caoyuqian.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.dto.CreateOauthClientDetailsDto;
import com.caoyuqian.auth.entity.OauthClientDetails;
import com.caoyuqian.auth.mapper.OauthClientDetailsMapper;
import com.caoyuqian.auth.service.OauthClientDetailsService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: OauthClientDetailsServiceImpl
 * @Package: com.caoyuqian.auth.service.impl
 * @Description: 客户端service
 * @date 2020/8/3 8:07 下午
 **/
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createOauthClientDetails(CreateOauthClientDetailsDto dto) {
        if (dto == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        OauthClientDetails byId = new OauthClientDetails();
        byId = this.getById(dto.getClientId());
        if (byId != null) {
            throw new ServiceException("改client已经存在");
        }
        BeanUtils.copyProperties(dto, byId);
        byId.setClientSecret(passwordEncoder.encode(byId.getClientSecret()));
        this.save(byId);
    }
}
