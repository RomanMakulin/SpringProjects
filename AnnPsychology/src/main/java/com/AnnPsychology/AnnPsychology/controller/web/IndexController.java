package com.AnnPsychology.AnnPsychology.controller.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class IndexController {

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/main.css";
    }
    @GetMapping
    public String getIndex(){
        return "main/index.html";
    }
    @GetMapping("/about")
    public String getAbout(){
        return "main/about.html";
    }
}
