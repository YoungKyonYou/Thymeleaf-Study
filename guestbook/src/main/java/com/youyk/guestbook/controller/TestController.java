package com.youyk.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @GetMapping(value="/test_ajax")
    public String sendHtml(Model map) {
        //map.addAttribute("foo", "bar");
        return "testajax";
    }
    @PostMapping(value="/test_ajax_frag")
    public String sendHtmlFragment(Model map) {
        //map.addAttribute("foo", "bar");
        return "testajaxfragment :: test_frag";
    }
}
