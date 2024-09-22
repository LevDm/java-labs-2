package ru.dmitriev.MySecondTestAppSpringBoot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.dmitriev.MySecondTestAppSpringBoot.model.Request;
import ru.dmitriev.MySecondTestAppSpringBoot.model.Response;

@RestController
public class MyController {
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {

        Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(request.getSystemTime())
                    .code("success")
                    .errorCode("")
                    .errorMessage("")
                    .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
