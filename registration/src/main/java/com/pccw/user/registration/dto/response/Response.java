package com.pccw.user.registration.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class Response {

  public Response(Integer code, String message,Object data){
    this.code=code;
    this.message=message;
    this.data = data;
  }

  public Response(Integer code, String message){
    this.code=code;
    this.message=message;
  }

  public  static Response ok(String message, Object data){
    return new Response(HttpStatus.OK.value(),message,data);
  }

  public  static Response ok(String message){
    return new Response(HttpStatus.OK.value(),message);
  }

  public  static Response error404(String message){
    return new Response(HttpStatus.NOT_FOUND.value(),message);
  }

  public  static Response error500(String message){
    return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
  }
  public  static Response error(int errorCode ,String message){
    return new Response(errorCode,message);
  }

  @ApiModelProperty(value = "code" ,notes = "status code,please refer to README.md to get details of each status code",example = "200")
  private Integer code ;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  @ApiModelProperty(value = "data" ,notes = "content of response",example = "{'email':'jack@sina.cn'}")
  private Object data ;

  @ApiModelProperty(value = "message" ,notes = "description of status",example = "new user registered")
  private String message ;

  public Response code(Integer code) {
    this.code = code;
    return this;
  }




  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }





  public Response message(String message) {
    this.message = message;
    return this;
  }



  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}