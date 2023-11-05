package com.jogo.deepify.controllers;

import com.jogo.deepify.exceptions.NotFoundException;
import com.jogo.deepify.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private UrlService urlService;

    @GetMapping
    public String MainPage(Model model){
        return "index";
    }

    @GetMapping(path="about")
    public String AboutPage(Model model){
        return "about";
    }

    @GetMapping(path="faq")
    public String FaqPage(Model model){
        return "faq";
    }

    @GetMapping(params = "destination")
    public String ResultPage(@RequestParam String destination, Model model){
        return urlService.useLink(destination, model);
    }

    @GetMapping("/{url}")
    public String RedirectRequest(@PathVariable String url, Model model){
        try{
            return urlService.openLink(url, model);
        }catch (NotFoundException e){
            return "404";
        }
    }
}
