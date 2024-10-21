package com.keepgoing.keepserver.domain.user.controller;

import com.keepgoing.keepserver.domain.user.dto.request.LoginRequest;
import com.keepgoing.keepserver.domain.user.dto.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.dto.request.StatusRequest;
import com.keepgoing.keepserver.domain.user.dto.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.service.user.UserService;
import com.keepgoing.keepserver.global.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(userService.authenticateAndGenerateJWT(loginRequest.email(), loginRequest.password()));
    }

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAndAuthenticateUser(@RequestBody SignupRequest signupRequest) throws BusinessException {
        return ResponseEntity.ok().body(userService.registerUser(signupRequest));
    }

    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 진행합니다.")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> withdrawMember(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.deleteUser(userId));
    }

    @Operation(summary = "프로필", description = "토큰을 이용하여 유저 정보와 대여한 기자재 및 도서 목록을 조회합니다.")
    @GetMapping("/userinfo")
    public ResponseEntity<?> provideUserInfo(Authentication authentication) {
        return ResponseEntity.ok().body(userService.provideUserInfo(authentication));
    }

    @Operation(summary = "프로필 수정", description = "유저 정보를 수정합니다.")
    @PutMapping("/userfix")
    public ResponseEntity<String> updateUserData(@RequestBody UserInfoRequest request, Authentication authentication) {
        return userService.updateUserData(request, authentication);
    }
    @Operation(summary = "학생이 받은 공지 불러오기", description = "본인의 공지를 불러옵니다.")
    @GetMapping("/notices")
    public ResponseEntity<?> getNoticeByUser(Authentication authentication){
        return ResponseEntity.ok().body(userService.getNoticeByUser(authentication));
    }

    @Operation(summary = "학생의 상태 전송하기", description = "학생 상태를 전송합니다.")
    @PutMapping("/status")
    public ResponseEntity<?> updateUserStatus(@Valid @RequestBody StatusRequest statusRequest, Authentication authentication){
        return ResponseEntity.ok().body(userService.updateUserStatus(statusRequest,authentication));
    }
}
