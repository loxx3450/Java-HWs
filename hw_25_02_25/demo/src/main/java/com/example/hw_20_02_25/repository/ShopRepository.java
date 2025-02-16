package com.example.hw_20_02_25.repository;

import com.example.hw_20_02_25.entity.Shop;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
	@Query("""
		SELECT * FROM shops 
		WHERE LOWER(name) LIKE '%' || LOWER(:namePattern) || '%'
			AND LOWER(address) LIKE '%' || LOWER(:addressPattern) || '%'
			AND LOWER(type::text) LIKE '%' || LOWER(:categoryPattern) || '%'
	""")
	List<Shop> findByFilter(
		@Param("namePattern") String namePattern,
		@Param("addressPattern") String addressPattern,
		@Param("categoryPattern") String categoryPattern);

}
