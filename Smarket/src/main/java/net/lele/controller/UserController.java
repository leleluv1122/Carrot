package net.lele.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.lele.domain.Ask;
import net.lele.domain.Category;
import net.lele.domain.Product;
import net.lele.domain.Product_image;
import net.lele.domain.User;
import net.lele.repository.ProductRepository;
import net.lele.service.AskService;
import net.lele.service.CategoryService;
import net.lele.service.Interest_productService;
import net.lele.service.ProductService;
import net.lele.service.Product_imageService;
import net.lele.utils.UploadFileUtils;

@Controller
public class UserController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	Product_imageService product_imageService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productrepository;
	@Autowired
	Interest_productService ips;
	@Autowired
	AskService askService;

	private String uploadPath = "D:/Carrot/Carrot/Smarket/src/main/resources/static/images/";

	@RequestMapping(value="user/location")
	public String location(Model model) {
		model.addAttribute("category", categoryService.findAll());
		return "user/location";
	}
	
	@RequestMapping(value = "shop/location", method = RequestMethod.POST)
	public String location(Model model, BindingResult bindingResult) {
		
		return "redirect:/shop/index";
	}
	
	@RequestMapping(value="user/interest")
	public String interest(Model model) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("count", ips.countByUserUserId(userId));
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("interest", ips.findByUserUserId(userId));
		model.addAttribute("product_image", product_imageService.findByProductidgroup());
		return "user/interest";
	}
	
	@RequestMapping(value = "user/write")
	public String write(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());

		return "user/write";
	}

	@RequestMapping(value = "user/fileinsert")
	public String fileinsert(HttpServletRequest request, MultipartHttpServletRequest mtfRequest, Model model)
			throws Exception {
		Product p = new Product();
		Product_image file = new Product_image();

		String ca = request.getParameter("category");
		int category = Integer.parseInt(ca);
		String us = request.getParameter("userid");
		int use = Integer.parseInt(us);

		Category cate = new Category();
		cate.setId(category);

		User u = new User();
		u.setId(use);

		p.setUser(u);
		p.setCategory(cate);
		p.setDetail(request.getParameter("detail"));
		p.setPrice(Integer.parseInt(request.getParameter("price")));
		p.setTitle(request.getParameter("title"));

		p.setId(productService.save(p));
		productrepository.flush();

		List<MultipartFile> fileList = mtfRequest.getFiles("files");

		String imgUploadPath = uploadPath; 
		
		for (MultipartFile fi : fileList) {
			String sourceFileName = fi.getOriginalFilename();
			String fileName = null;
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath); 
			fileName = UploadFileUtils.fileUpload(imgUploadPath, sourceFileName, fi.getBytes(), ymdPath);
		
			file.setFilename(fileName); 
			file.setProductid(p.getId());
			file.setFileOriName(sourceFileName); 
			file.setFileurl(imgUploadPath);
			
			product_imageService.save(file);
		}

		/*
		 * String sourceFileName = files.getOriginalFilename(); 
		 * String imgUploadPath = uploadPath; 
		 * String ymdPath = UploadFileUtils.calcPath(imgUploadPath); 
		 * String fileName = null;
		 * 
		 * fileName = UploadFileUtils.fileUpload(imgUploadPath, sourceFileName, files.getBytes(), ymdPath);
		 * 
		 * file.setFilename(fileName); 
		 * file.setProductid(p.getId());
		 * file.setFileOriName(sourceFileName); 
		 * file.setFileurl(imgUploadPath);
		 * 
		 * product_imageService.save(file);
		 */

		return "redirect:/shop/index";
	}
	
	@RequestMapping("user/asklist")
	public String asklist(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("asklist", askService.findByUserUserIdOrderByIdDesc(userId));
		model.addAttribute("acnt", askService.countByUserUserId(userId));
		return "user/asklist";
	}
	
	@RequestMapping("user/ask")
	public String ask(Model model, Ask ask) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		return "user/ask";
	}
	
	@RequestMapping(value = "user/ask", method = RequestMethod.POST)
	public String ask(Model model, Ask ask, BindingResult bindingResult) throws Exception {
		askService.save(ask);
		return "redirect:/user/asklist";
	}
	
	@RequestMapping("user/askdetail/{id}")
	public String askdetail(@PathVariable("id") int id, Model model) throws Exception{
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("ask", askService.findById(id));
		return "user/askdetail";
	}

}