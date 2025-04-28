package com.mesago.mesago.service;

import com.mesago.mesago.dto.login.LoginRequestDto;
import com.mesago.mesago.dto.login.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);
}
