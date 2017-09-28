package com.mex.adx.mgr.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String SSP = "ROLE_SSP"; //ssp

    public static final String ADX = "ROLE_ADX"; //adx

//    public static final String DSP = "ROLE_DSP";

    public static final String AD_SOURCE = "ROLE_AD_SOURCE";//广告源

    public static final String OPERATOR = "ROLE_OPERATOR"; //运营

    private AuthoritiesConstants() {
    }
}
