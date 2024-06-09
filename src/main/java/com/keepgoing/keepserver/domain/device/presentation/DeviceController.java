package com.keepgoing.keepserver.domain.device.presentation;

import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.service.DeviceService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "기기", description = "기자재 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "기자재 리스트", description = "기자재 리스트를 확인합니다.")
    @GetMapping("/list")
    public BaseResponse allDevices(){
        return deviceService.findAll();
    }

    @Operation(summary = "기자재 생성", description = "기자재를 생성합니다.")
    @PostMapping("/create")
    public BaseResponse deviceCreate(@RequestBody DeviceDto deviceDto){
        return deviceService.deviceCreate(deviceDto);
    }

    @Operation(summary = "선택한 기자재", description = "선택한 기자재의 정보를 확인합니다.")
    @GetMapping("/detail/{id}")
    public BaseResponse deviceRead(@PathVariable("id") Long id){
        return deviceService.deviceRead(id);
    }

    @Operation(summary = "나의 기자재 대여 현황", description = "내가 대여 중인 기자재의 정보를 확인합니다.")
    @GetMapping("/my")
    public BaseResponse myDevices(Authentication authentication){
        return deviceService.myDevices(authentication);
    }

    @Operation(summary = "기자재 삭제" , description = "선택한 기자재를 삭제합니다.")
    @DeleteMapping("/delete/{id}")
    public BaseResponse deleteDevice(@PathVariable Long id, Authentication authentication){
        return deviceService.deleteDevice(id, authentication);
    }

    @Operation(summary = "기자재 대여", description = "기자재를 대여합니다.")
    @PostMapping("/rent")
    public BaseResponse rentDevice(@RequestParam String deviceName, @RequestParam String email) {
        return deviceService.rentDevice(deviceName, email);
    }
}