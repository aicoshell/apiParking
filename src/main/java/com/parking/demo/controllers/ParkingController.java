package com.parking.demo.controllers;


import com.parking.demo.models.Parking;
import com.parking.demo.services.XMLService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingController {

    private XMLService xmlService;

  

    @GetMapping(value = "/api/parkings")
    public Parking all(XMLService xmlService){
        this.xmlService = xmlService;
        Parking parking = xmlService.parseParking();
        return parking;
    }


}
