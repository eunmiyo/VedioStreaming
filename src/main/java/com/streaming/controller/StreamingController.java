package com.streaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StreamingController {
//	@GetMapping(value="/streaming")
	@RequestMapping(value="/streaming")
	public String streaming() {
		return "movie/streaming";
	}
}
