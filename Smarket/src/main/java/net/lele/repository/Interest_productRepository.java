package net.lele.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import net.lele.domain.Interest_product;

public interface Interest_productRepository extends JpaRepository<Interest_product, Integer> {
	int countByUserUserIdAndProductId(String uid, int pid);
	
	@Modifying
	@Transactional
	void deleteByProductIdAndUserUserId(int pid, String uid);
	
	List<Interest_product> findByUserUserId(String uid);
	
	int countByUserUserId(String uid);
}
