package com.mesago.mesago.controller;

import com.mesago.mesago.dto.login.LoginRequestDto;
import com.mesago.mesago.dto.login.LoginResponseDto;
import com.mesago.mesago.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto responseDto = authService.login(request);
        return ResponseEntity.ok(responseDto);
    }

}
