package com.john.springcloudclientb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "spring-cloud-clientb")
public interface MyFeign {

    @GetMapping("/bclient/gethello")
    String GetHello();

    @PostMapping("/bclient/posthello")
    String PostHello();

}
