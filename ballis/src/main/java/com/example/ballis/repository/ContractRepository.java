package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ballis.DTO.ContractChartDTO;
import com.example.ballis.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

	@Query(value="SELECT "
			+ "new com.example.ballis.DTO.ContractChartDTO"
			+ "(con.id, con.price, con.contractDate, "
			+ "COALESCE(sell.productSize, 0) AS sellSize, "
			+ "COALESCE(buy.productSize, 0) AS buySize) "
			+ "FROM Contract con "
			+ "LEFT JOIN Product prod ON con.product = prod.id "
			+ "LEFT JOIN Selling sell ON con.selling = sell.id "
			+ "LEFT JOIN Buying buy ON con.buying = buy.id " 
			+ "WHERE prod.id = :productid " // con.product = :productid 는 오류는 안나는데 데이터 조회가 안됨
			+ "ORDER BY con.contractDate ASC, con.price DESC",  
			nativeQuery = false)
	List<ContractChartDTO> getContractChart(@Param("productid") Long productid);

}
