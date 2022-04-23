package com.pccw.user.registration.api;


import com.pccw.user.registration.api.exception.ErrorCode;
import com.pccw.user.registration.api.exception.IncorrectPasswordException;
import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.dto.request.ChangeUserPasswordRequest;
import com.pccw.user.registration.dto.response.Response;
import com.pccw.user.registration.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "user")
public class ChangeUserPasswordController {

    @Autowired
    UserService userService;

    @Value("${password.change.succeed}")
    private String passwordChangedSucceed;

    @ApiOperation("change password")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.NO_USER_CODE, message = ErrorCode.MESSAGE.NO_USER_MESSAGE),
                    @ApiResponse(code = ErrorCode.CODE.INCORRECT_PASSWORD_CODE, message = ErrorCode.MESSAGE.INCORRECT_PASSWORD_MESSAGE )
            }
    )
    @PutMapping(path = "/v1/user/password" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePassword(@ApiParam(value="request body")  @RequestBody ChangeUserPasswordRequest changeUserPasswordRequest) {

        log.info("Start to change user's password,user id is: {}", changeUserPasswordRequest.getUserId());

        final User user = userService.changePassword(changeUserPasswordRequest.getUserId(),changeUserPasswordRequest.getOldPassword(),changeUserPasswordRequest.getNewPassword());
        log.info("Change {}'s password succeed.", changeUserPasswordRequest.getUserId());

        return ResponseEntity.ok(Response.ok(passwordChangedSucceed));
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e){
        return ResponseEntity.ok(Response.error(ErrorCode.NO_USER.getCode(),e.getMessage()));
    }
    @ExceptionHandler(value = {IncorrectPasswordException.class})
    @ResponseBody
    public ResponseEntity handleIncorrectPasswordException(Exception e){
        return ResponseEntity.ok(Response.error(ErrorCode.INCORRECT_PASSWORD.getCode(),e.getMessage()));
    }
}
