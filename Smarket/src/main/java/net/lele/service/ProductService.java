package net.lele.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Product;
import net.lele.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product findById(int id) {
		return productRepository.findById(id);
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public List<Product> findByCategoryId(int id){
		return productRepository.findByCategoryId(id);
	}
	
	@Transactional
	public int save(Product product) {
		Product p = new Product();
		p.setId(product.getId());
		p.setCategory(product.getCategory());
		p.setDetail(product.getDetail());
		p.setPrice(product.getPrice());
		p.setTitle(product.getTitle());
		p.setUser(product.getUser());
		
		productRepository.save(p);
		
		int i = p.getId();
		
		return i;
	}
}
