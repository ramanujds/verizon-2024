package com.vz.app.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefalutAppController {

    @GetMapping
    public String redirectToSwaggerUI() {
        return "swagger-ui.html";
    }
}
