package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.book.domain.entity.Book;
import com.keepgoing.keepserver.domain.book.payload.response.BookResponseDto;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.service.BookServiceImpl;
import com.keepgoing.keepserver.domain.device.domain.entity.Device;
import com.keepgoing.keepserver.domain.device.mapper.DeviceMapper;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.service.DeviceServiceImpl;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserProfileDto;
import com.keepgoing.keepserver.domain.user.payload.response.ApiResponse;
import com.keepgoing.keepserver.domain.user.payload.response.JwtResponse;
import com.keepgoing.keepserver.domain.user.domain.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.security.jwt.JwtUtils;
import com.keepgoing.keepserver.domain.user.security.service.UserDetailsImpl;
import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import com.keepgoing.keepserver.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final DeviceServiceImpl deviceService;
    private final DeviceMapper deviceMapper;
    private final BookServiceImpl bookService;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public ApiResponse<JwtResponse> registerUser(SignupRequest signupRequest) throws BusinessException {
        validateEmail(signupRequest.getEmail());
        User user = createUser(signupRequest);
        userRepository.save(user);
        JwtResponse jwtResponse = authenticateAndGenerateJWT(signupRequest.getEmail(), signupRequest.getPassword());
        return ApiResponse.setApiResponse(true, "회원 가입이 완료 되었습니다!", jwtResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateUserData(UserInfoRequest request, Authentication authentication) {
        String userEmail = getEmailFromAuthentication(authentication);
        User user = findUserByEmail(userEmail);
        updateUser(user, request);
        return ResponseEntity.ok().body("");
    }

    @Override
    public UserProfileDto provideUserInfo(Authentication authentication) {
        String userEmail = getNameByAuthentication(authentication);
        User user = findUserByEmail(userEmail);
        List<DeviceResponseDto> borrowedDevicesDto = getBorrowedDevicesForUser(user);
        List<BookResponseDto> borrowedBooksDto = getBorrowedBooksForUser(user);
        hideUserPassword(user);
        return new UserProfileDto(user, borrowedDevicesDto, borrowedBooksDto);
    }

    /* 인증 및 JWT 토큰 생성 */
    public JwtResponse authenticateAndGenerateJWT(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return JwtResponse.setJwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getName(), userDetails.isTeacher());
    }

    private List<DeviceResponseDto> getBorrowedDevicesForUser(User user) {
        List<Device> borrowedDevices = deviceService.findDevicesBorrowedByUser(user);
        return deviceMapper.convertDevicesToDtos(borrowedDevices);
    }

    private List<BookResponseDto> getBorrowedBooksForUser(User user) {
        List<Book> borrowedBooks = bookService.findBooksBorrowedByUser(user);
        return bookMapper.convertBooksToDtos(borrowedBooks);
    }

    private void hideUserPassword(User user) {
        user.hidePassword("");
    }

    private String getNameByAuthentication(Authentication authentication) {
        return authentication.getName();
    }

    private void validateEmail(String email) throws BusinessException {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.EMAIL_BAD_REQUEST);
        }
    }

    private User createUser(SignupRequest signupRequest) {
        return User.registerUser(
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getName(),
                signupRequest.isTeacher()
        );
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        return getNameByAuthentication(authentication);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmailEquals(email)
                .orElseThrow(DeviceException::userNotFound);
    }

    private void updateUser(User user, UserInfoRequest request) {
        user.fixUserData(request.getEmail(), request.getName());
    }
}
