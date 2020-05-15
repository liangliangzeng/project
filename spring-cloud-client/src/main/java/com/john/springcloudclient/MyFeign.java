package com.john.springcloudclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "spring-cloud-client")
public interface MyFeign {

    @GetMapping("/aclient/gethello")
    String GetHello();

    @PostMapping("/aclient/posthello")
    String PostHello();

}
