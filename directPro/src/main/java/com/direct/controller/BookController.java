package com.direct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	// 메인페이지로 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET() {

	}

}
