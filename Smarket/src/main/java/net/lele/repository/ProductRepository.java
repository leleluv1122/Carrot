package net.lele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.lele.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByCategoryId(int id);
	
	Product findById(int id);
}
