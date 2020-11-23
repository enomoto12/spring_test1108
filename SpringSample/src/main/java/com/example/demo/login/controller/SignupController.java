package com.example.demo.login.controller;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.SignupForm;

@Controller
public class SignupController {

	private Map<String,String> redioMarriage;

	private Map<String,String> initRedioMarriage(){

		Map<String,String> radio = new LinkedHashMap<>();

		radio.put("既婚", "true");
		radio.put("未婚", "false");

		return radio;

	}

	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {

		redioMarriage = initRedioMarriage();

		model.addAttribute("redioMarriage", redioMarriage);

		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return getSignUp(form, model);
		}

		return "redirect:/login";
	}


}
