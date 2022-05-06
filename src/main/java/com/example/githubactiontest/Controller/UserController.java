package com.example.githubactiontest.Controller;

import com.example.githubactiontest.dto.KakaoUserInfoDto;
import com.example.githubactiontest.dto.SignupRequestDto;
import com.example.githubactiontest.service.KakaoUserService;
import com.example.githubactiontest.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    // 카카오 인가 처리
    @GetMapping("/user/kakao/callback")
    public @ResponseBody
    KakaoUserInfoDto
    kakaoLogin(@RequestParam String code) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드


        return kakaoUserService.kakaoLogin(code);
    }
}