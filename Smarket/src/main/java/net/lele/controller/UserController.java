package net.lele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.lele.service.CategoryService;

@Controller
public class UserController {
	@Autowired
	CategoryService categoryService;
	
    @RequestMapping("user/mypage")
    public String index(Model model) {
    	model.addAttribute("category", categoryService.findAll());
        return "user/mypage";
    }
}