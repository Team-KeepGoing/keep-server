package com.keepgoing.keepserver.domain.device.repository;

import com.keepgoing.keepserver.domain.device.entity.device.Device;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByDeviceUserNameContaining(String device_name, Sort id);
}
