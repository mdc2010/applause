package com.applause.coding.test.controllers;

import com.applause.coding.test.models.UserExperience;
import com.applause.coding.test.models.UserExperiences;
import com.applause.coding.test.services.GetBugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetBugsController {

    @Autowired
    private GetBugsService service;

    @GetMapping(value = "applause/testers/bugs")
    public List<UserExperience> getUserBugExperience(
            @RequestParam(value = "country", required = false) List<String> countries,
            @RequestParam(value = "device", required = false) List<String> devices
    ) {
        return service.getBugs(countries, devices);
    }

}
