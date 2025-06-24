package com.example.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        Instant now = Instant.now();
        ZonedDateTime local = ZonedDateTime.ofInstant(now,ZoneId.systemDefault());

        JSONObject greeting = new JSONObject();
        greeting.put("Message", "Hello World !!");
        greeting.put("DateTimeUtc", now.toString());
        greeting.put("DateTimeLocal", local.toString());

        return greeting.toString();
    }

}
