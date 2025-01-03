package ru.dmitriev.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dmitriev.MySecondTestAppSpringBoot.model.Request;
import ru.dmitriev.MySecondTestAppSpringBoot.model.Systems;
import ru.dmitriev.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;


@Service
@Qualifier("ModifyDataRequestService")
public class ModifyDataRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.SERVICE_1);
        request.setSource("System 1");
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}