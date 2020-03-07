package net.lele.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Interest_product;
import net.lele.repository.Interest_productRepository;

@Service
public class Interest_productService {
	@Autowired
	Interest_productRepository ipr;
	
	public List<Interest_product> findAll(){
		return ipr.findAll();
	}
	
	public int countByUserUserIdAndProductId(String uid, int pid) {
		return ipr.countByUserUserIdAndProductId(uid, pid);
	}
	
	public int countByUserUserId(String uid) {
		return ipr.countByUserUserId(uid);
	}
	
	public void save(Interest_product i) {
		Interest_product ii = new Interest_product();
		ii.setProduct(i.getProduct());
		ii.setUser(i.getUser());
		ipr.save(ii);
	}
	
	public List<Interest_product> findByUserUserId(String uid){
		return ipr.findByUserUserId(uid);
	}
	
	public void delete(int pid, String uid) {
		ipr.deleteByProductIdAndUserUserId(pid, uid);
	}
}
