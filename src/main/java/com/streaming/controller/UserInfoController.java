package com.streaming.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.streaming.dto.UserInfoDto;
import com.streaming.entity.UserInfo;
import com.streaming.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class UserInfoController {
	private final UserInfoService userInfoService;
	private final PasswordEncoder passwordEncoder;
	
	//Get방식, 회원가입 화면
		@GetMapping(value = "/signup")
		public String signup(Model model) {
			model.addAttribute("userInfoDto", new UserInfoDto());
			return "member/signup";
		}
		
		//Post방식, 회원가입 버튼을 눌렀을 떄 실행되는 메소드
		@PostMapping(value = "/signup")
		//@Valid: 유효성을 검증하려고 하는 객체 앞에 붙여줌!
		//BindingResult: 유효성 검증 후에 결과를 넣어준다.
		//Model model -> 클라이언트가 넘겨준 값들
		public String signup(@Valid UserInfoDto userInfoDto, BindingResult bindingResult, Model model) {
			
			//에러가 있다면 회원가입 페이지로 이동
			if(bindingResult.hasErrors()) {
				return "member/signup";
			}
			
			try {
				UserInfo userInfo = UserInfo.createUserInfo(userInfoDto, passwordEncoder);
				userInfoService.saveUser(userInfo);
				
			} catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
				return "member/signup";
			}	
			
			return "redirect:/"; // /-> localhost
		}
		
		//로그인 화면
		@GetMapping(value = "/login")
	public String loginMember() {
			return "member/login";
		}
		
		private final SessionManager sessionManager;
		
		//로그인을 실패했을 때
		@GetMapping(value = "/login/error")
		public String loginError(Model model) {
			model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
			return "member/login";
		}
		
		/*
		 * //탈퇴 화면
		 * 
		 * @GetMapping(value = "/delete{id}") public String deleteMember(@PathVariable
		 * Long id) { userInfoService.delete(id); return "member/delete"; }
		 * 
		 * //회원탈퇴
		 * 
		 * @DeleteMapping(value = "/delete{id}")
		 * 
		 * @ResponseBody public String deleteMember1(@PathVariable Long id) {
		 * userInfoService.delete(id); return "member/login"; }
		 */
}
