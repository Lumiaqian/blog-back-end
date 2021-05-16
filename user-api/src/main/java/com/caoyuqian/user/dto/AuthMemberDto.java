package com.caoyuqian.user.dto;

import lombok.Data;

@Data
public class AuthMemberDto {

    private Long id;
    private String username;
    private String password;
    private Integer status;

    private String avatar;
    private String nickname;

}
