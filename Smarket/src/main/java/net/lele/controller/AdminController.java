package net.lele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.lele.domain.Notice;
import net.lele.service.CategoryService;

@Controller
public class AdminController {
	@Autowired
	CategoryService categoryService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin/notice")
	public String upload(Model model, Notice notice) {
		model.addAttribute("category", categoryService.findAll());
		return "admin/notice";
	}
}
