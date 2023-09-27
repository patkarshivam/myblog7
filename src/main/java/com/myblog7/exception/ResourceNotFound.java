package com.myblog7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//this obj is created post is not found or record is not found
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String msg){
        super(msg);//when i create a obj of resourcenotfound i supply the mgs to its construtor this super keyword
        // will automatically display that msg in postman response
    }

}
