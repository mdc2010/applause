package com.applause.coding.test.services;

import com.applause.coding.test.models.*;
import com.applause.coding.test.repositories.BugsRepository;
import com.applause.coding.test.repositories.DeviceRepository;
import com.applause.coding.test.repositories.TesterRepository;
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

public class GetBugsServiceTest {

    @Mock
    private BugsRepository bugsRepository;

    @Mock
    private TesterRepository testerRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private GetBugsService service;

    private List<String> countries;
    private List<String> devices;
    private List<Bugs> bugs;
    private List<Testers> testers;
    private List<Devices> deviceObjects;
    private List<Integer> deviceIds;

    private UserExperience userExperience;
    private List<UserExperience> userExperiences;
    private int bugId = 1;
    private int deviceId = 2;
    private int testerId = 3;
    private String country = "US";
    private String deviceVal = "Iphone";

    @Before
    public void setup() {
        userExperience = new UserExperience(testerId,1);
        userExperiences = new ArrayList<UserExperience>();
        deviceObjects = new ArrayList<Devices>();
        deviceIds = new ArrayList<Integer>();
        deviceIds.add(deviceId);
        BugsId bugsId = new BugsId();
        bugsId.setBugId(bugId);
        bugsId.setDeviceId(deviceId);
        bugsId.setTesterId(testerId);
        Bugs bug = new Bugs();
        bug.setId(bugsId);
        Testers tester = new Testers();
        testers = new ArrayList<Testers>();
        bug.setTesters(tester);
        countries = new ArrayList<String>();
        devices = new ArrayList<String>();
        bugs = new ArrayList<Bugs>();
        bugs.add(bug);
        tester.setId(testerId);
        tester.setBugs(bugs);
        tester.setCountry(country);
        testers.add(tester);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryByCountry() {
        userExperiences.add(userExperience);
        countries.add(country);
        when(testerRepository.findAllByCountryIsIn(countries)).thenReturn(testers);

        List<UserExperience> response = service.getBugs(countries,devices);

        Assert.assertEquals(userExperiences.get(0).getTesterExperience(), response.get(0).getTesterExperience());
        verify(testerRepository).findAllByCountryIsIn(countries);
    }

    @Test
    public void queryByDevice() {
        userExperiences.add(userExperience);
        Devices devicesObject = new Devices();
        devicesObject.setId(deviceId);
        devicesObject.setTesters(testers);
        devices.add(deviceVal);
        deviceObjects.add(devicesObject);

        when(deviceRepository.findAllByDescriptionIn(devices)).thenReturn(deviceObjects);
        when(bugsRepository.findByIdDeviceIdInAndTestersIn(deviceIds,testers)).thenReturn(bugs);

        List<UserExperience> response = service.getBugs(countries,devices);

        Assert.assertEquals(userExperiences.get(0).getTesterExperience(), response.get(0).getTesterExperience());
        verify(deviceRepository).findAllByDescriptionIn(devices);
    }

    @Test
    public void queryByDeviceAndCountry() {
        userExperiences.add(userExperience);
        Devices devicesObject = new Devices();
        devicesObject.setId(deviceId);
        devicesObject.setTesters(testers);
        devices.add(deviceVal);
        countries.add(country);
        deviceObjects.add(devicesObject);

        when(deviceRepository.findAllByDescriptionIn(devices)).thenReturn(deviceObjects);
        when(testerRepository.findAllByCountryIsInAndDevicesIsIn(countries,deviceObjects)).thenReturn(testers);
        when(bugsRepository.findByIdDeviceIdInAndTestersIn(deviceIds,testers)).thenReturn(bugs);

        List<UserExperience> response = service.getBugs(countries,devices);

        Assert.assertEquals(userExperiences.get(0).getTesterExperience(), response.get(0).getTesterExperience());
        verify(deviceRepository).findAllByDescriptionIn(devices);
    }

    @Test
    public void noQueryParamTest() {
        userExperiences.add(userExperience);
        when(bugsRepository.findAll()).thenReturn(bugs);

        List<UserExperience> response = service.getBugs(countries,devices);

        Assert.assertEquals(userExperiences.get(0).getTesterExperience(), response.get(0).getTesterExperience());
        verify(bugsRepository).findAll();
    }
}
