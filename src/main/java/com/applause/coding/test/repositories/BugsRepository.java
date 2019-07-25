package com.applause.coding.test.repositories;

import com.applause.coding.test.models.Bugs;
import com.applause.coding.test.models.BugsId;
import com.applause.coding.test.models.Testers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BugsRepository extends CrudRepository<Bugs, Integer> {
    List<Bugs> findAll();
    List<Bugs> findByIdDeviceIdIn(List<Integer> deviceIds);
    List<Bugs> findByIdDeviceIdInAndTestersIn(List<Integer> deviceIds, List<Testers> testers);
}
