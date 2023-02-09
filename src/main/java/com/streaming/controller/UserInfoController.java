package com.streaming.controller;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.streaming.dto.UserInfoDto;
import com.streaming.entity.UserInfo;
import com.streaming.repository.UserInfoRepository;
import com.streaming.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class UserInfoController {
	private final UserInfoService userInfoService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserInfoRepository userInfoRepository;
	
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
	
	//회원수정
	@GetMapping(value = "/update")
	public String updateMember(UserInfoDto userInfoDto, Model model, Principal principal) {
		String email = principal.getName();
		
//		userInfoDto.findByUser(userInfoDto.getEmail());
		
		UserInfoDto userInfo = new UserInfoDto();
		userInfo.setEmail(email);
		
		model.addAttribute("userInfoDto", userInfo);
		
		return "member/update";
	}
	
	
	@PostMapping(value = "/update")
	public String userupdate(@Valid UserInfoDto userInfoDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "member/update";
		}
		
		try {
			userInfoService.updateUser(userInfoDto, passwordEncoder);
			model.addAttribute("errorMessage", "회원정보가 정상적으로 변경되었습니다.");
			
		} catch(Exception e) {
			model.addAttribute("errorMessage", "회원정보 수정 중 에러가 발생하였습니다.");
			return "member/update";
		}
		
		return "/member/login";
	}
	

	//회원탈퇴
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam(value="email") String email, @RequestParam(value="password") String password, Model model) {
		try {
			//회원 정보를 삭제한다
			int result = userInfoService.deleteUser(email, password, passwordEncoder);
			
			//회원 정보 삭제에 성공했을 경우
			if (result > 0) {
				//성공 메시지를 반환한다
				System.out.println("삭제 성공 (" + result + ")");
				model.addAttribute("errorMessage", "회원탈퇴에 성공하였습니다");
			} else {
				//에러 메시지를 반환한다
				System.err.println("삭제 실패 (" + result + ")");
				model.addAttribute("errorMessage", "회원탈퇴에 실패하였습니다 (" + result + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "회원탈퇴에 실패하였습니다 (-999)");
		}
		
		return "member/login";
	}
}
