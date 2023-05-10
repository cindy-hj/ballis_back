package com.example.ballis.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.ballis.model.Product;
import com.example.ballis.model.Selling;

public class ProductSpecification {

	public static Specification<Product> withCategory(Integer category) {
		return (root, query, builder) -> builder.equal(root.get("category"), category);
	}
	
	public static Specification<Product> withGender(Integer gender) {
		return (root, query, builder) -> builder.equal(root.get("gender"), gender);
	}
	
	public static Specification<Product> withBrandId(Integer brandId) {
		return (root, query, builder) -> builder.equal(root.get("brandId"), brandId);
	}
	
    public static Specification<Selling> withInventoryDiv(Integer inventoryDiv) {
        return (root, query, builder) -> builder.equal(root.get("inventoryDiv"), inventoryDiv);
    }

    public static Specification<Selling> withSize(Integer size) {
        return (root, query, builder) -> builder.equal(root.get("size"), size);
    }

    public static Specification<Selling> withWishPrice(Integer wishPrice) {
        return (root, query, builder) -> builder.equal(root.get("wishPrice"), wishPrice);
    }
}
