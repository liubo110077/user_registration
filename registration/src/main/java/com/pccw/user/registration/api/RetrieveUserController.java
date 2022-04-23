package com.pccw.user.registration.api;


import com.pccw.user.registration.api.exception.ErrorCode;
import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.dto.response.Response;
import com.pccw.user.registration.dto.response.RetrieveUserResponse;
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
public class RetrieveUserController {

    @Autowired
    UserService userService;


    @Value("retrieve.succeed")
    private String retrieveSucceedMessage;

    @ApiOperation("retrieve a user")
    @GetMapping(path = "/v1/user/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.NO_USER_CODE, message = ErrorCode.MESSAGE.NO_USER_MESSAGE)
            }
    )
    public ResponseEntity retrieveUser(@ApiParam(value="user id") @PathVariable("userId") int userId) {

        log.info("Start to retrieve user,userId is: {}", userId);

        User user = userService.findById(userId);

        log.info("Retrieve user succeed,request is: {}", userId);

        final RetrieveUserResponse retrieveUserResponse = new RetrieveUserResponse(user.getId(), user.getEmail().getEmailAddress(),user.isActive());

        return ResponseEntity.ok(Response.ok(retrieveSucceedMessage ,retrieveUserResponse));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e){
        return ResponseEntity.ok(Response.error(ErrorCode.NO_USER.getCode(),e.getMessage()));
    }
}
