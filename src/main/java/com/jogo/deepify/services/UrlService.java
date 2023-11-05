package com.jogo.deepify.services;

import com.jogo.deepify.entities.urlEntity;
import com.jogo.deepify.exceptions.NotFoundException;
import com.jogo.deepify.repositories.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UrlService {
    @Autowired
    private UrlRepo urlRepo;
    private final RandomString randomString = new RandomString(5);

    public String useLink(String destination, Model model){
        if(!destination.startsWith("https://") && !destination.startsWith("http://")){
            destination="https://"+destination;
        }
        urlEntity url = urlRepo.findByDestination(destination);
        if(url!=null){
            model.addAttribute("url", destination);
            model.addAttribute("shortenurl", "http://localhost:8080/"+url.getUrl());
            return "result";
        }else{
            urlEntity newUrl = new urlEntity(destination, randomString.nextString());
            urlRepo.save(newUrl);
            model.addAttribute("url", destination);
            model.addAttribute("shortenurl", "http://localhost:8080/"+newUrl.getUrl());
            return "result";
        }

    }

    public String openLink(String shortUrl, Model model) throws NotFoundException {
        urlEntity url = urlRepo.findByUrl(shortUrl);
        if(url!=null){
            return "redirect:"+url.getDestination();
        }else {
            throw new NotFoundException("404! Url Not Found.");
        }
    }



}
