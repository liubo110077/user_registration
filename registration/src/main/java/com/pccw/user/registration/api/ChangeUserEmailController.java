package com.pccw.user.registration.api;


import com.pccw.user.registration.api.exception.ErrorCode;
import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.dto.request.ChangeUserEmailRequest;
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
public class ChangeUserEmailController {

    @Autowired
    UserService userService;

    @Value("${email.change.succeed}")
    private String editSucceedMessage;

    @ApiOperation("change email")
    @PutMapping(path = "/v1/user" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.NO_USER_CODE, message = ErrorCode.MESSAGE.NO_USER_MESSAGE)
            }
    )
    public ResponseEntity changeEmail(@ApiParam(value="request body") @RequestBody ChangeUserEmailRequest changeUserEmailRequest) {

        log.info("Start to edit user,request is: {}", changeUserEmailRequest);

        final User user = userService.changeEmail(changeUserEmailRequest.getUserId(), changeUserEmailRequest.getEmail());

        log.info("Edit user succeed,request is: {}", changeUserEmailRequest);

        return ResponseEntity.ok(Response.ok(editSucceedMessage,changeUserEmailRequest));
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e){
        return ResponseEntity.ok(Response.error(ErrorCode.NO_USER.getCode(),e.getMessage()));
    }

}
