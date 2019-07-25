package com.applause.coding.test.services;

import com.applause.coding.test.models.*;
import com.applause.coding.test.repositories.BugsRepository;
import com.applause.coding.test.repositories.DeviceRepository;
import com.applause.coding.test.repositories.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GetBugsService {

    @Autowired
    private BugsRepository bugsRepository;

    @Autowired
    private TesterRepository testerRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<UserExperience> getBugs(List<String> countries, List<String> devices) {

        Map<Integer,Integer> userExperienceMap = null;
        if(CollectionUtils.isEmpty(countries) && CollectionUtils.isEmpty(devices)){
            userExperienceMap = noQueryParams();
        } else if(!CollectionUtils.isEmpty(countries) && CollectionUtils.isEmpty(devices)) {
            userExperienceMap = queryOnCountry(countries);
        } else if(CollectionUtils.isEmpty(countries) && !CollectionUtils.isEmpty(devices)) {
            userExperienceMap = queryOnDevices(devices);
        } else {
            userExperienceMap = queryOnDevicesAndCountries(countries,devices);
        }

         return userExperienceMap.entrySet().stream()
                .map(x -> userExperiencesBuilder(x.getKey(),x.getValue()))
                .sorted(Comparator.comparingInt(UserExperience::getTesterExperience)
                        .reversed()).collect(Collectors.toList());
    }

    private Map noQueryParams() {
        List<Bugs> bugs = bugsRepository.findAll();
        return mapUserBugCountForAll(bugs);
    }

    private Map queryOnCountry(List<String> countries) {
        List<Testers> testers = testerRepository.findAllByCountryIsIn(countries);
        return mapUserBugCountByTester(testers);
    }

    private Map queryOnDevices(List<String> devices) {
        List<Devices> deviceObjects = deviceRepository.findAllByDescriptionIn(devices);
        List<Testers> testers = deviceObjects.stream().flatMap(deviceObject -> deviceObject.getTesters().stream()).collect(Collectors.toList());
        List<Integer> deviceIds = deviceObjects.stream().map(deviceObject -> deviceObject.getId()).collect(Collectors.toList());
        List<Bugs> bugs = bugsRepository.findByIdDeviceIdInAndTestersIn(deviceIds,testers);
        return mapUserBugCountByTesterAndDevice(testers,bugs);
    }

    private Map queryOnDevicesAndCountries(List<String> countries ,List<String> devices) {
        List<Devices> deviceObjects = deviceRepository.findAllByDescriptionIn(devices);
        List<Testers> testerByCountryAndDevice = testerRepository.findAllByCountryIsInAndDevicesIsIn(countries,deviceObjects);
        List<Integer> deviceIds = deviceObjects.stream().map(deviceObject -> deviceObject.getId()).collect(Collectors.toList());
        List<Bugs> bugs = bugsRepository.findByIdDeviceIdInAndTestersIn(deviceIds,testerByCountryAndDevice);

        return mapUserBugCountByTesterAndDevice(testerByCountryAndDevice,bugs);

    }

    private UserExperience userExperiencesBuilder(int testerId, int testerExperience) {
        return new UserExperience(testerId,testerExperience);
    }

    private Map<Integer, Integer> mapUserBugCountForAll(List<Bugs> bugs) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(Bugs bug : bugs) {
            int testerId = bug.getId().getTesterId();
            map.put(testerId, 1 + map.getOrDefault(testerId,0));
        }
        return map;
    }

    private Map<Integer, Integer> mapUserBugCountByTester(List<Testers> testers) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(Testers tester : testers) {
            int bugCount = tester.getBugs().size();
            map.put(tester.getId(), bugCount);
        }
        return map;
    }

    private Map<Integer, Integer> mapUserBugCountByTesterAndDevice(List<Testers> testers, List<Bugs> bugs) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(Testers tester : testers) {
            map.put(tester.getId(), 0);
        }

        for(Bugs bug: bugs) {
            int testerId = bug.getId().getTesterId();
            map.put(testerId, 1 + map.get(testerId));
        }

        return map;
    }
}
