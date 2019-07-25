package com.applause.coding.test.controllers;

import com.applause.coding.test.models.UserExperience;
import com.applause.coding.test.services.GetBugsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetBugsControllerTest {

    @Mock
    private GetBugsService service;

    @InjectMocks
    private GetBugsController getBugsController;

    private List<String> countries;
    private List<String> devices;
    private List<UserExperience> userExperiences;
    private UserExperience userExperience;
    private int testerId = 123;
    private int deviceId = 456;

    @Before
    public void setup() {
        countries = new ArrayList<String>();
        devices = new ArrayList<String>();
        userExperience = new UserExperience(testerId,deviceId);
        userExperiences = new ArrayList<UserExperience>();
        userExperiences.add(userExperience);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getBugs() {
        when(getBugsController.getUserBugExperience(countries,devices)).thenReturn(userExperiences);

        List<UserExperience> response = getBugsController.getUserBugExperience(countries,devices);

        Assert.assertEquals(userExperiences,response);
        verify(service).getBugs(countries,devices);
    }
}
