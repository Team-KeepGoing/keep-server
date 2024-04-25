package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.exception.CustomException;
import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.payload.response.JwtResponse;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.security.jwt.JwtUtils;
import com.keepgoing.keepserver.domain.user.security.service.UserDetailsImpl;
import com.keepgoing.keepserver.domain.user.service.role.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest) throws CustomException {
      if (userRepository.existsByEmail(signupRequest.getEmail())) {
          throw new CustomException("이미 사용중인 이메일 입니다.");
      }
      User user = User.registerUser(
          signupRequest.getEmail(),
          encoder.encode(signupRequest.getPassword()),
          signupRequest.getName(),
          signupRequest.isTeacher(), // 혹은 해당 필드가 없는 경우에는 signupRequest에서 해당 메서드를 제거하세요
          roleService.getDefaultRole()
      );
      userRepository.save(user);
    }


    @Transactional
    public void fixUserData(UserInfoRequest request, String email) {
        Optional<User> user = userRepository.findByEmailEquals(email);

        System.out.println(user);

        user.ifPresent(value -> {
            value.fixUserData(
                    request.getEmail(),
                    request.getName()
            );
            userRepository.save(value);
        });
    }

    /* 인증 및 JWT 토큰 생성 */
    public JwtResponse authenticateAndGenerateJWT(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roleNames = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.setJwtResponse(jwt, (long) userDetails.getId(), userDetails.getEmail(), userDetails.getPassword(), userDetails.isTeacher(), roleNames);
    }
}