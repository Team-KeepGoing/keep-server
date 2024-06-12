package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookResponseDto;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.service.BookServiceImpl;
import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.mapper.DeviceMapper;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.service.DeviceServiceImpl;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserProfileDto;
import com.keepgoing.keepserver.domain.user.payload.response.JwtResponse;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.security.jwt.JwtUtils;
import com.keepgoing.keepserver.domain.user.security.service.UserDetailsImpl;
import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import com.keepgoing.keepserver.global.exception.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void registerUser(SignupRequest signupRequest) throws BusinessException {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_BAD_REQUEST);
        }
        User user = User.registerUser(
                signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()),
                signupRequest.getName(), signupRequest.isTeacher()
        );
        userRepository.save(user);
    }

    @Transactional
    public void updateUserData(UserInfoRequest request, String email) {
        User user = userRepository.findByEmailEquals(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        user.fixUserData(
                request.getEmail(),
                request.getName()
        );
    }

    @Override
    public UserProfileDto provideUserInfo(String userEmail) {
        User user = userRepository.findByEmailEquals(userEmail).orElseThrow(DeviceException::userNotFound);
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
}
