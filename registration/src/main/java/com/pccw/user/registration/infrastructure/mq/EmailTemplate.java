package com.pccw.user.registration.infrastructure.mq;

public interface EmailTemplate {
    public static final String DEFAULT_FROM ="asdfasf1@sdfsdaf.com";
    public static final  String DEFAULT_TITLE ="Welcome to PCCW";
    public  static final  String DEFAULT_CONTENT ="Hello,\n" +
            "\n" +
            "Welcome to PCCW, we are glad to have you on board! ";
}
