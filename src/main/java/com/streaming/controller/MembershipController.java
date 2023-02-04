package com.streaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembershipController {
	@GetMapping(value = "/membership")
	public String membership() {
		return "member/membership";
	}
}
