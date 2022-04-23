package com.pccw.user.registration.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {



    INVALID_EMAIL( CODE.INVALID_EMAIL_CODE,MESSAGE.INVALID_EMAIL_MESSAGE),
    NO_USER( CODE.NO_USER_CODE,MESSAGE.NO_USER_MESSAGE),
    INCORRECT_PASSWORD( CODE.INCORRECT_PASSWORD_CODE,MESSAGE.INCORRECT_PASSWORD_MESSAGE);

    private int code;
    private String message;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class CODE {
        public static final int INVALID_EMAIL_CODE = 1001;
        public static final int NO_USER_CODE = 1002;
        public static final int INCORRECT_PASSWORD_CODE = 1003;
    }

    public static class MESSAGE {
        public static final String INVALID_EMAIL_MESSAGE = "invalid email";
        public static final String NO_USER_MESSAGE = "user not found";
        public static final String INCORRECT_PASSWORD_MESSAGE = "incorrect password";
    }
}
