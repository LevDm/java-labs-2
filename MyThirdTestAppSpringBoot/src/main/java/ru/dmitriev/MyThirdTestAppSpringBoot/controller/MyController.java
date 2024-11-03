package ru.dmitriev.MyThirdTestAppSpringBoot.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.dmitriev.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.dmitriev.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import ru.dmitriev.MyThirdTestAppSpringBoot.model.*;
import ru.dmitriev.MyThirdTestAppSpringBoot.service.ModifyResponseService;
import ru.dmitriev.MyThirdTestAppSpringBoot.service.ValidationService;
import ru.dmitriev.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("Request: {}", request);

        String systemTimeNow = DateTimeUtil.getCustomFormat().format(new Date());
        try {
            log.info("Время от отправки запроса из Postman до вывода в лог сервиса 2: {} мс", DateTimeUtil.getPassedTime(request.getSystemTime(), systemTimeNow));
        } catch (ParseException e) {
            log.error("Parse exception {} при запросе {}", e.getMessage(), request);
        }

        Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                    .code(Codes.SUCCESS)
                    .errorCode(ErrorCodes.EMPTY)
                    .errorMessage(ErrorMessages.EMPTY)
                    .build();

        log.info("Init-response: {}", response);

        try {
            validationService.isValid(bindingResult);
            log.info("Validation passed: {}", request);

        } catch (ValidationFailedException | UnsupportedCodeException e) {
            bindingResult.getFieldErrors().forEach(error -> {
                log.info("Validation-exception: \"{}: {}\"", error.getField(), error.getDefaultMessage());
            });
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Unknown-exception: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        modifyResponseService.modify(response);

        log.info("Fine-response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
