package com.keepgoing.keepserver.domain.device.repository;

import com.keepgoing.keepserver.domain.device.entity.Device;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByDeviceNameContaining(String deviceName, Sort id);
}
