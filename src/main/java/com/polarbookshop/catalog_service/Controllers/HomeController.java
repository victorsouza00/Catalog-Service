package com.polarbookshop.catalog_service.Controllers;


import com.polarbookshop.catalog_service.Configuration.Properties.PolarPorperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final PolarPorperties polarPorperties;

    public HomeController(PolarPorperties polarPorperties) {
        this.polarPorperties = polarPorperties;
    }

    @GetMapping("/")
    public String getGreeting(){
        return polarPorperties.getGreeting();
    }

}
