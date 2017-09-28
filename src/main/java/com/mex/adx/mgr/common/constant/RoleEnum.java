package com.mex.adx.mgr.common.constant;

import com.mex.adx.mgr.security.AuthoritiesConstants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleEnum {
    NONE("", ""),
    ADMIN(AuthoritiesConstants.ADMIN, "管理员"),
    USER(AuthoritiesConstants.USER, "用户"),
    ANONYMOUS(AuthoritiesConstants.ANONYMOUS, "匿名用户"),
    SSP(AuthoritiesConstants.SSP, "SSP"),
    ADX(AuthoritiesConstants.ADX, "ADX"),
    AD_SOURCE(AuthoritiesConstants.AD_SOURCE, "广告源"),
    OPERATOR(AuthoritiesConstants.OPERATOR, "运营");

    public static Map<String, RoleEnum> lookupMap;

    static {
        lookupMap = Stream.of(values()).collect(Collectors.toMap(RoleEnum::getValue, e -> e));
    }

    public static RoleEnum lookup(String role) {
        return lookupMap.getOrDefault(role, NONE);
    }

    private String value;
    private String name;


    RoleEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
