package com.pccw.user.registration.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;

@Data
@ApiModel(value="change password request",description="change password request")
public class ChangeUserPasswordRequest {

    @ApiModelProperty(value = "userId",required = true,example = "1")
    private int userId;
    @ApiModelProperty(value = "oldPassword",required = true,example = "password")
    private String oldPassword;
    @ApiModelProperty(value = "newPassword",required = true,example = "newPassword")
    private String newPassword;


}
