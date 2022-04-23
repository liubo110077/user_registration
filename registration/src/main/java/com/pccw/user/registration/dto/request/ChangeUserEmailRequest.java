package com.pccw.user.registration.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="change user email request",description="change user email request")
public class ChangeUserEmailRequest {

    @ApiModelProperty(value = "user id",required = true,example = "1")
    private int userId;
    @ApiModelProperty(value = "email, length must not over 100 character and format must follow RFC 822 standards",required = true,example = "jack@sina.cn")
    private String email;
}
