package com.applause.coding.test.repositories;

import com.applause.coding.test.models.Devices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Devices,Integer> {
    List<Devices> findAllByDescriptionIn(List<String> devices);
}
