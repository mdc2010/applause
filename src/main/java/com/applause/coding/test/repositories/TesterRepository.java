package com.applause.coding.test.repositories;

import com.applause.coding.test.models.Devices;
import com.applause.coding.test.models.Testers;
import com.applause.coding.test.models.Bugs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TesterRepository extends CrudRepository<Testers,Integer> {
    List<Testers> findAllByCountryIsInAndDevicesIsIn(List<String> country, List<Devices> devices);
    List<Testers> findAllByCountryIsIn(List<String> countries);
}
