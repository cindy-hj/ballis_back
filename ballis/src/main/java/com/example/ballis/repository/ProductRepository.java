package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ballis.DTO.ProductAllDTO;
import com.example.ballis.DTO.ProductNewDTO;
import com.example.ballis.DTO.ProductOneDTO;
import com.example.ballis.DTO.ProductPopDTO;
import com.example.ballis.DTO.ProductBuyDTO;
import com.example.ballis.DTO.ProductBuyMethodDTO;
import com.example.ballis.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductNewDTO"
	          + "(prod.id, prod.productEngName, img.imagePath, bd.brandName, sell.wishPrice, sell.inventoryDiv) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "INNER JOIN Brand bd ON prod.brand = bd.brandId "
	            + "INNER JOIN Selling sell ON prod.id = sell.product "
	            + "WHERE img.pageDiv = 1 AND img.mainImageDiv = 1 AND sell.sellingStatus = 1 "
	            + "AND sell.wishPrice = "
	            + "(SELECT MIN(wishPrice) "
	            + "FROM Selling "
	            + "WHERE product = prod.id) "
	            + "AND (sell.inventoryDiv = 1 OR "
	            + "(sell.inventoryDiv = 2 AND NOT EXISTS "
	            + "(SELECT 1 FROM Selling subSell WHERE prod.id = subSell.product "
	            + "AND sell.wishPrice = subSell.wishPrice "
	            + "AND subSell.inventoryDiv = 1)))"
	            + "ORDER BY prod.launchingDate DESC "
	            + "LIMIT 12",
	            nativeQuery = false)
	List<ProductNewDTO> getProductNew();
	

	@Query(value="SELECT "
			+ "new com.example.ballis.DTO.ProductPopDTO"
			+ "(prod.id, prod.productEngName, MAX(img.imagePath), bd.brandName, sell.wishPrice, sell.inventoryDiv, COUNT(cont.id)) "
			+ "FROM Product prod "
            + "INNER JOIN Image img ON prod.id = img.targetId "
            + "INNER JOIN Brand bd ON prod.brand = bd.brandId "
            + "INNER JOIN Selling sell ON prod.id = sell.product "
            + "LEFT JOIN Contract cont ON prod.id = cont.product "
            + "WHERE img.pageDiv = 1 AND img.mainImageDiv = 1 AND sell.sellingStatus = 1 "
            + "AND sell.wishPrice = "
            + "(SELECT MIN(wishPrice) "
            + "FROM Selling "
            + "WHERE product = prod.id) "
            + "AND (sell.inventoryDiv = 1 OR "
            + "(sell.inventoryDiv = 2 AND NOT EXISTS "
            + "(SELECT 1 FROM Selling subSell WHERE prod.id = subSell.product "
            + "AND sell.wishPrice = subSell.wishPrice "
            + "AND subSell.inventoryDiv = 1)))"
            + "GROUP BY prod.id, prod.productEngName, bd.brandName, sell.wishPrice, sell.inventoryDiv "
            + "ORDER BY COUNT(cont.id) DESC "
            + "LIMIT 12",
			nativeQuery = false)
	List<ProductPopDTO> getProductPop();
	
	
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductOneDTO"
	          + "(prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.color, prod.launchingDate, prod.launchingPrice, "
	          + "img.imagePath, bd.brandName, buy.wishPrice, sell.wishPrice, sell.inventoryDiv, con.price) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "INNER JOIN Brand bd ON prod.brand = bd.brandId "
	            + "LEFT JOIN Buying buy ON prod.id = buy.product "
	            + "LEFT JOIN Selling sell ON prod.id = sell.product "
	            + "LEFT JOIN Contract con ON prod.id = con.product "
	            + "WHERE prod.id = :productid "
	            + "AND img.pageDiv = 1 AND sell.sellingStatus = 1 "
	            + "AND (sell.wishPrice IS NULL OR sell.wishPrice = "
	            + "(SELECT MIN(wishPrice) "
	            + "FROM Selling "
	            + "WHERE product = prod.id)) "
	            + "AND (buy.wishPrice IS NULL OR buy.wishPrice = "
	            + "(SELECT MIN(wishPrice) "
	            + "FROM Buying "
	            + "WHERE product = prod.id)) "
	            + "ORDER BY con.contractDate DESC "
	            + "LIMIT 1",
	            nativeQuery = false)
	List<ProductOneDTO> getProductOne(@Param("productid") Long productid);
	
	// 어디서 참조하고 있는지..?
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductBuyDTO"
	          + "(prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, prod.sizeMax, prod.sizeUnit, "
	          + "img.imagePath, sell.sellingStatus, sell.productSize, MIN(sell.wishPrice), sell.inventoryDiv, sell.member.memberNumber) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "LEFT JOIN Selling sell ON prod.id = sell.product "
	            + "WHERE prod.id = :productid "
	            + "AND img.pageDiv = 1 AND sell.sellingStatus = 1 "
	            + "AND (sell.inventoryDiv = 1 OR "
	            + "(sell.inventoryDiv = 2 AND NOT EXISTS "
	            + "(SELECT 1 FROM Selling subSell WHERE prod.id = subSell.product "
	            + "AND sell.wishPrice = subSell.wishPrice "
	            + "AND subSell.inventoryDiv = 1))) "
	            + "GROUP BY prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, "
	            + "prod.sizeMax, prod.sizeUnit, img.imagePath, sell.sellingStatus, sell.productSize, sell.inventoryDiv, sell.member.memberNumber "
				+ "ORDER BY MIN(sell.wishPrice) ASC"
	            ,nativeQuery = false)
	List<ProductBuyDTO> getProductSelection(@Param("productid") Long productid);
	
	
	

	
	
	// 보관상품 데이터만 출력
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductBuyDTO"
	          + "(prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, prod.sizeMax, prod.sizeUnit, "
	          + "img.imagePath, sell.sellingStatus, sell.productSize, MIN(sell.wishPrice), sell.inventoryDiv, sell.member.memberNumber) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "LEFT JOIN Selling sell ON prod.id = sell.product "
	            + "WHERE prod.id = :productid "
	            + "AND img.pageDiv = 1 AND sell.sellingStatus = 1 AND sell.inventoryDiv = 1 "
	            + "GROUP BY prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, "
	            + "prod.sizeMax, prod.sizeUnit, img.imagePath, sell.sellingStatus, sell.productSize, sell.inventoryDiv, sell.member.memberNumber "
				+ "ORDER BY MIN(sell.wishPrice) ASC"
	            ,nativeQuery = false)
	List<ProductBuyDTO> getFastProduct(@Param("productid") Long productid);
	
	// 일반상품 데이터만 출력
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductBuyDTO"
	          + "(prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, prod.sizeMax, prod.sizeUnit, "
	          + "img.imagePath, sell.sellingStatus, sell.productSize, MIN(sell.wishPrice), sell.inventoryDiv, sell.member.memberNumber) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "LEFT JOIN Selling sell ON prod.id = sell.product "
	            + "WHERE prod.id = :productid "
	            + "AND img.pageDiv = 1 AND sell.sellingStatus = 1 AND sell.inventoryDiv = 2 "
	            + "GROUP BY prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, prod.sizeMin, "
	            + "prod.sizeMax, prod.sizeUnit, img.imagePath, sell.sellingStatus, sell.productSize, sell.inventoryDiv, sell.member.memberNumber "
				+ "ORDER BY MIN(sell.wishPrice) ASC"
	            ,nativeQuery = false)
	List<ProductBuyDTO> getNormalProduct(@Param("productid") Long productid);
	
	
	
	
	
	
	
	@Query(value="SELECT "
	          + "new com.example.ballis.DTO.ProductBuyMethodDTO"
	          + "(prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, img.imagePath, "
	          + "sell.sellingStatus, sell.productSize, MIN(sell.wishPrice), sell.inventoryDiv, sell.member.memberNumber, "
	          + "buy.buyingStatus, buy.productSize, MAX(buy.wishPrice), buy.member.memberNumber) "
	            + "FROM Product prod "
	            + "INNER JOIN Image img ON prod.id = img.targetId "
	            + "LEFT JOIN Selling sell ON prod.id = sell.product AND sell.sellingStatus = 1 "
	            + "AND sell.productSize = :size "
	            + "AND (sell.inventoryDiv = 1 OR "
	            + "(sell.inventoryDiv = 2 AND NOT EXISTS "
	            + "(SELECT 1 FROM Selling subSell WHERE prod.id = subSell.product "
	            + "AND sell.wishPrice = subSell.wishPrice "
	            + "AND subSell.inventoryDiv = 1))) "
	            + "LEFT JOIN Buying buy ON sell.productSize = buy.productSize AND buy.buyingStatus = 1 "
	            + "WHERE prod.id = :productid "
	            + "AND img.pageDiv = 1"
	            + "GROUP BY prod.id, prod.productEngName, prod.productKorName, prod.modelNumber, img.imagePath, "
	            + "sell.sellingStatus, sell.productSize, sell.inventoryDiv, sell.member.memberNumber, "
	            + "buy.buyingStatus, buy.productSize, buy.member.memberNumber",
	            nativeQuery = false)
	List<ProductBuyMethodDTO> getProductBySize(@Param("productid") Long productid, @Param("size") Integer size);
	
	
	
	@Query(value="SELECT "
			+ "new com.example.ballis.DTO.ProductAllDTO"
			+ "(prod.id, prod.productEngName, prod.productKorName, MAX(img.imagePath), bd.brandName, "
			+ "sell.wishPrice, sell.inventoryDiv, COUNT(DISTINCT wi.id), COUNT(DISTINCT re.id), "
			+ "prod.category, prod.gender, prod.brand.id, sell.productSize, prod.launchingDate, COUNT(DISTINCT con.id)) "
			+ "FROM Product prod "
            + "INNER JOIN Image img ON prod.id = img.targetId "
            + "INNER JOIN Brand bd ON prod.brand = bd.brandId "
            + "LEFT JOIN Selling sell ON prod.id = sell.product "
            + "LEFT JOIN Wish wi ON prod.id = wi.product "
            + "LEFT JOIN Review re ON prod.id = re.product "
            + "LEFT JOIN Contract con ON prod.id = con.product "        
            + "WHERE img.pageDiv = 1 AND img.mainImageDiv = 1 AND sell.sellingStatus = 1 "
            + "AND sell.wishPrice = "
            + "(SELECT MIN(wishPrice) "
            + "FROM Selling "
            + "WHERE product = prod.id) "
            + "AND (sell.inventoryDiv = 1 OR "
            + "(sell.inventoryDiv = 2 AND NOT EXISTS "
            + "(SELECT 1 FROM Selling subSell WHERE prod.id = subSell.product "
            + "AND sell.wishPrice = subSell.wishPrice "
            + "AND subSell.inventoryDiv = 1))) "
            + "GROUP BY prod.id, prod.productEngName, prod.productKorName, bd.brandName, sell.wishPrice, sell.inventoryDiv, "
            + "prod.category, prod.gender, prod.brand, sell.productSize "
            + "ORDER BY "
            + "CASE :sort "
            + "WHEN 1 THEN COUNT(DISTINCT con.id) "
            + "WHEN 2 THEN COUNT(DISTINCT wi.id) "
            + "WHEN 3 THEN prod.launchingDate "
            + "WHEN 4 THEN COUNT(DISTINCT re.id) "
            + "ELSE COUNT(DISTINCT con.id) END DESC",
    		nativeQuery = false)
    List<ProductAllDTO> getProductAll(@Param("sort") Integer sort);
	

}