package net.lele.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Product_image;
import net.lele.repository.Product_imageRepository;

@Service
public class Product_imageService {
	@Autowired
	Product_imageRepository product_imageRepository;
	
	public void save(Product_image pi) {
		Product_image p = new Product_image();
		p.setFilename(pi.getFilename());
		p.setFileOriName(pi.getFileOriName());
		p.setFileurl(pi.getFileurl());
		p.setProduct(pi.getProduct());
		
		product_imageRepository.save(p);
	}
}
