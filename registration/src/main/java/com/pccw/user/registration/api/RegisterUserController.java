package com.pccw.user.registration.api;


import com.pccw.user.registration.api.exception.ErrorCode;
import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.dto.request.RegisterUserRequest;
import com.pccw.user.registration.dto.response.RegisterUserResponse;
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
public class RegisterUserController {

    @Autowired
    UserService userService;

    @Value("${register.succeed}")
    private String registerSucceedMessage;

    @ApiOperation("register user")
    @PostMapping(path = "/v1/user" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.INVALID_EMAIL_CODE, message = ErrorCode.MESSAGE.INVALID_EMAIL_MESSAGE)
            }
    )
    public ResponseEntity registerUser(@ApiParam(value="request body") @RequestBody RegisterUserRequest userRegistrationRequest) {

        log.info("Start to register user,request is: {}", userRegistrationRequest);

        User user = userService.register(userRegistrationRequest.getEmail(),userRegistrationRequest.getPassword());

        RegisterUserResponse registerUserResponse = new RegisterUserResponse(user.getId(),user.getEmail().getEmailAddress());

        log.info("Register user succeed,request is: {}", userRegistrationRequest);

        return ResponseEntity.ok(Response.ok(registerSucceedMessage,registerUserResponse));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        return ResponseEntity.ok(Response.error(ErrorCode.INVALID_EMAIL.getCode(),e.getMessage()));
    }
}
