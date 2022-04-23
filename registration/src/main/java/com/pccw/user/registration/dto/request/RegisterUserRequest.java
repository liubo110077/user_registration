package com.pccw.user.registration.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

@Data
@ApiModel(value="register user request",description="register user request")
public class RegisterUserRequest {

    @ApiModelProperty(value = "email, length must not over 100 character and format must follow RFC 822 standards",required = true,example = "jack@sina.cn")
    private String email;

    @ApiModelProperty(value = "password",required = true,example = "password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

}
