package com.pccw.user.registration.api;


import com.pccw.user.registration.api.exception.ErrorCode;
import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.dto.response.Response;
import com.pccw.user.registration.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "user")
public class DeleteUserController {

    @Autowired
    UserService userService;


    @Value("${delete.succeed}")
    private String deleteSucceedMessage;


    @Value("${batch.delete.succeed}")
    private String batchDeleteSucceedMessage;

    @ApiOperation("delete a user")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.NO_USER_CODE, message = ErrorCode.MESSAGE.NO_USER_MESSAGE),
            }
    )
    @DeleteMapping(path = "/v1/user/{userId}")
    public ResponseEntity deleteUser(@ApiParam(value="user id", example = "1") @PathVariable("userId") int userId) {

        log.info("Start to delete user,userId is: {}", userId);

        userService.delete(userId);

        log.info("Delete user succeed,request is: {}", userId);

        return ResponseEntity.ok(Response.ok(deleteSucceedMessage));
    }

    @ApiOperation("delete multiple users")
    @DeleteMapping(path = "/v1/user/{userIds}/batch")

    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "success", response = Response.class),
                    @ApiResponse(code = ErrorCode.CODE.NO_USER_CODE, message = ErrorCode.MESSAGE.NO_USER_MESSAGE),
            }
    )
    public ResponseEntity deleteUsers( @ApiParam(value="multi user ids,connected with ','", example = "1,2,3") @PathVariable("userIds") int[] userIds) {

        log.info("Start to delete user,userId is: {}", userIds);

        userService.batchDelete(userIds);

        log.info("Delete user succeed,request is: {}", userIds);

        return ResponseEntity.ok(Response.ok(batchDeleteSucceedMessage));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        return ResponseEntity.ok(Response.error(ErrorCode.NO_USER.getCode(),e.getMessage()));
    }
}
