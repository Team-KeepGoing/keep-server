package com.keepgoing.keepserver.domain.device.repository;

import com.keepgoing.keepserver.domain.device.entity.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
