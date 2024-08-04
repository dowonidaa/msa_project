package com.msa.eureka.cilent.auth;

import com.msa.eureka.cilent.auth.dto.RequestSignIn;
import com.msa.eureka.cilent.auth.dto.RequestSignUp;
import com.msa.eureka.cilent.auth.entity.User;
import com.msa.eureka.cilent.auth.entity.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Value("${spring.application.name}")
    private String issuer;

    private final SecretKey secretKey;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public AuthService(@Value("${service.jwt.secret-key}") String key, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    // 회원 가입
    public User signUp(RequestSignUp request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = request.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        String role = request.getRole();

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
        return user;
    }

    public String signIn(RequestSignIn requestSignIn) {
        String username = requestSignIn.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다."));
        String password = requestSignIn.getPassword();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다.");
        }

        return createAccessToken(user.getUsername(), user.getRole());
    }



    public String createAccessToken(String userId, UserRole role) {
        return Jwts.builder()
                .claim("username", userId)
                .claim("role", role.toString())
                .issuer(issuer)
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey)
                .compact();
    }

    public Boolean validated(String username, String role) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("유저 아이디를 찾을 수 없습니다."));
        return user.getRole().toString().equals(role);
    }
}
