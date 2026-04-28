package com.test.notemate.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.notemate.domain.user.dto.SignupRequest;
import com.test.notemate.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("signupRequest") SignupRequest signupRequest) {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signupRequest") SignupRequest signupRequest,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/signup";
        }

        try {
            userService.signup(signupRequest);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "duplicate", e.getMessage());
            return "user/signup";
        }
/* POST 요청 처리 후 바로 뷰를 반환하면 새로고침 시 같은 POST가 다시 전송될 수 있으므로, redirect GET 요청으로 바꿔 중복 제출을 방지 */
        
        // POST 요청 처리 후 바로 뷰를 반환하면 새로고침 시 같은 POST가 다시 전송될 수 있으므로 redirect로 중복 제출을 방지
        return "redirect:/login";
    }
}
