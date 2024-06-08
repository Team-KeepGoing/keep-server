package com.keepgoing.keepserver.domain.device.repository;

import com.keepgoing.keepserver.domain.device.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceName(String deviceName);
}
