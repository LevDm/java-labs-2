package ru.dmitriev.MySecondTestAppSpringBoot.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.dmitriev.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.dmitriev.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.dmitriev.MySecondTestAppSpringBoot.model.*;
import ru.dmitriev.MySecondTestAppSpringBoot.service.*;
import ru.dmitriev.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyOperationUidResponseService modifyOperationUidResponseService,
                        ModifyDataRequestService modifyDataRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyOperationUidResponseService;
        this.modifyRequestService = modifyDataRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("Request: {}", request);

        Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                    .annualBonus(new AnnualBonusServiceImp().calculate(
                        request.getPosition(),
                        request.getSalary(),
                        request.getBonus(),
                        request.getWorkDays()))
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

        response = modifyResponseService.modify(response);
        modifyRequestService.modify(request);
        log.info("Fine-response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
