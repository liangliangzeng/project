package com.john.springcloudclientb;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController implements MyFeign {

    public String GetHello(){
        return "B Get Hello World!!!";
    }

    public String PostHello(){
        return "B Post Hello World!!!";
    }

}
