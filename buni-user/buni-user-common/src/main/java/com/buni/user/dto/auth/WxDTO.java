package com.buni.user.dto.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxDTO implements Serializable {

    private String openid;

    private String session_key;

}
