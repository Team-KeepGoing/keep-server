package com.keepgoing.keepserver.domain.device.domain.repository;

import com.keepgoing.keepserver.domain.device.domain.entity.Device;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceName(String deviceName);
    List<Device> findByBorrower(User borrower);
}
