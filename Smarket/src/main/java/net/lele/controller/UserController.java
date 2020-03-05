package net.lele.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import net.lele.domain.Category;
import net.lele.domain.Product;
import net.lele.domain.Product_image;
import net.lele.domain.User;
import net.lele.repository.ProductRepository;
import net.lele.service.CategoryService;
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

	private String uploadPath = "D:/Carrot/Carrot/Smarket/src/main/resources/static/images/";

	@RequestMapping("user/mypage")
	public String index(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		return "user/mypage";
	}

	@RequestMapping(value = "user/write")
	public String write(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());

		return "user/write";
	}

	@RequestMapping(value = "user/fileinsert")
	public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files, Model model)
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

		String sourceFileName = files.getOriginalFilename();

		String imgUploadPath = uploadPath;
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		fileName = UploadFileUtils.fileUpload(imgUploadPath, sourceFileName, files.getBytes(), ymdPath);

		file.setFilename(fileName);
		file.setProductid(p.getId());
		file.setFileOriName(sourceFileName);
		file.setFileurl(imgUploadPath);

		product_imageService.save(file);

		return "redirect:/shop/index";
	}
}