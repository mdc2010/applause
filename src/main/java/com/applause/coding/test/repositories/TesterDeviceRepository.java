package com.applause.coding.test.repositories;

import com.applause.coding.test.models.TesterDevice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TesterDeviceRepository extends CrudRepository<TesterDevice,Integer> {
    List<TesterDevice> findAllByIdDeviceIdIn(List<Integer> deviceId);
}
