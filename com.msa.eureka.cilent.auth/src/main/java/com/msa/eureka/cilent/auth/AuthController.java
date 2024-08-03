package com.msa.eureka.cilent.auth;

import com.msa.eureka.cilent.auth.dto.RequestSignIn;
import com.msa.eureka.cilent.auth.dto.RequestSignUp;
import com.msa.eureka.cilent.auth.dto.ResponseSignIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody RequestSignUp request) {
        authService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> sigIn(@RequestBody RequestSignIn requestSignIn) {
        String token = authService.signIn(requestSignIn);
        return ResponseEntity.ok(new ResponseSignIn(token));
    }
}
