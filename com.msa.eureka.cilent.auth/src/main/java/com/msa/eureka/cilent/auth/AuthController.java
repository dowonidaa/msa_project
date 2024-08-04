package com.msa.eureka.cilent.auth;

import com.msa.eureka.cilent.auth.dto.RequestSignIn;
import com.msa.eureka.cilent.auth.dto.RequestSignUp;
import com.msa.eureka.cilent.auth.dto.ResponseSignIn;
import com.msa.eureka.cilent.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody RequestSignUp request) {
        User user = authService.signUp(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> sigIn(@RequestBody RequestSignIn requestSignIn) {
        String token = authService.signIn(requestSignIn);
        return ResponseEntity.ok(new ResponseSignIn(token));
    }

    @GetMapping("/validated")
    public Boolean validated(@RequestParam String username, @RequestParam String role) {
        log.info("username = {}, role = {}", username, role);
        return authService.validated(username, role);
    }
}
