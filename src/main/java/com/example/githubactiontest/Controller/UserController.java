package com.example.githubactiontest.Controller;

import com.example.githubactiontest.dto.KakaoUserInfoDto;
import com.example.githubactiontest.dto.SignupRequestDto;
import com.example.githubactiontest.service.KakaoUserService;
import com.example.githubactiontest.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public @ResponseBody KakaoUserInfoDto
    kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드

        // 크롬익스텐션 용 엑세스 토큰
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        cookie.setMaxAge(1 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
        return kakaoUserService.kakaoLogin(code);
    }
}
