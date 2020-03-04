package net.lele.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Product;
import net.lele.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public void save(Product product) {
		Product p = new Product();
		p.setCategory(product.getCategory());
		p.setDetail(product.getDetail());
		p.setPrice(product.getPrice());
		p.setTitle(product.getTitle());
		
		productRepository.save(p);
	}
}
