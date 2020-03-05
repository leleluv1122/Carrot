package net.lele.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.lele.domain.Product_image;

public interface Product_imageRepository extends JpaRepository<Product_image, Integer> {
	Product_image findByProductid(int id);

}
