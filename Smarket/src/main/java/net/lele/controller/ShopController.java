package net.lele.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.lele.domain.Interest_product;
import net.lele.domain.Search;
import net.lele.model.UserRegistrationModel;
import net.lele.service.CategoryService;
import net.lele.service.Interest_productService;
import net.lele.service.ProductService;
import net.lele.service.Product_imageService;
import net.lele.service.SearchService;
import net.lele.service.UserService;

@Controller
public class ShopController {
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	Product_imageService product_imageService;
	@Autowired
	SearchService searchService;
	@Autowired
	Interest_productService ips;

	@RequestMapping({ "/", "shop/index" })
	public String index(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("product", productService.findByProductlimit());
		model.addAttribute("product_image", product_imageService.findByProductidgroup());

		/* model.addAttribute("cnt", ips.countByproductidgroup()); */

		/*
		 * List<Object[]> results = ipr.countByproductidgroup(); for (Object[] result :
		 * results) { int id = ((Number) result[1]).intValue(); int count = ((Number)
		 * result[1]).intValue(); model.addAttribute("cnt", result); }
		 * 
		 * model.addAttribute("cnt", ipr.countByproductidgroup());
		 */

		return "shop/index";
	}

	@RequestMapping("shop/login")
	public String login(Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		return "shop/login";
	}

	@RequestMapping(value = "shop/register", method = RequestMethod.GET)
	public String register(UserRegistrationModel userModel, Model model) throws Exception {
		model.addAttribute("category", categoryService.findAll());
		return "shop/register";
	}

	@RequestMapping(value = "shop/register", method = RequestMethod.POST)
	public String register(@Valid UserRegistrationModel userModel, BindingResult bindingResult, Model model)
			throws Exception {
		if (userService.hasErrors(userModel, bindingResult)) {
			model.addAttribute("category", categoryService.findAll());
			return "shop/register";
		}
		userService.save(userModel);
		return "redirect:index";
	}

	@RequestMapping("shop/category/{id}")
	public String category(@PathVariable("id") int id, Model model) throws Exception {
		model.addAttribute("idd", id);
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("product", productService.findByCategoryIdOrderByIdDesc(id));
		model.addAttribute("product_image", product_imageService.findByProductidgroup());
		return "shop/category";
	}

	@RequestMapping("shop/product/{id}")
	public String product(@PathVariable("id") int id, Interest_product i, Model model) throws Exception {
		productService.clickupdate(id);
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("interest", ips.countByUserUserIdAndProductId(userId, id));
		model.addAttribute("pcnt", ips.countByProductId(id));
		model.addAttribute("idd", id);
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("p", productService.findById(id));
		model.addAttribute("pi", product_imageService.findByProductid(id));
		return "shop/product";
	}

	@RequestMapping("shop/users/{id}")
	public String users(@PathVariable("id") int id, Model model) throws Exception {
		model.addAttribute("idd", id);
		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("product", productService.findByUserIdOrderByIdDesc(id));
		model.addAttribute("pcount", productService.countByUserId(id));
		model.addAttribute("product_image", product_imageService.findByProductidgroup());
		return "shop/users";
	}

	@RequestMapping(value = "shop/search/{word}")
	public String search(@PathVariable("word") String word, Model model) throws Exception {
		Search s = new Search();
		s.setName(word);
		searchService.save(s);

		model.addAttribute("category", categoryService.findAll());
		model.addAttribute("list", productService.findByTitleContains(word));
		model.addAttribute("product_image", product_imageService.findByProductidgroup());
		return "shop/search";
	}

	@RequestMapping(value = "shop/product/{id}", method = RequestMethod.POST)
	public String product(@PathVariable("id") int id, Model model, Interest_product i, BindingResult bindingResult)
			throws Exception {
		ips.save(i);
		return "redirect:/shop/product/{id}";
	}

	@RequestMapping(value = "shop/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) throws Exception {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		ips.delete(id, userId);
		model.addAttribute("category", categoryService.findAll());
		return "redirect:/shop/product/{id}";
	}

}