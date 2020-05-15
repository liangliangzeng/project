package com.john.springcloudclient;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyController implements MyFeign{

    public String GetHello(){
        return "A Get Hello World!!!";
    }

    public String PostHello(){
        return "A Post Hello World!!!";
    }

}
