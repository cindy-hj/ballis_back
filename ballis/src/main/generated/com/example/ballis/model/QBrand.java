package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBrand is a Querydsl query type for Brand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBrand extends EntityPathBase<Brand> {

    private static final long serialVersionUID = 837037294L;

    public static final QBrand brand = new QBrand("brand");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final StringPath brandName = createString("brandName");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final ListPath<Product, QProduct> products = this.<Product, QProduct>createList("products", Product.class, QProduct.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public QBrand(String variable) {
        super(Brand.class, forVariable(variable));
    }

    public QBrand(Path<? extends Brand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrand(PathMetadata metadata) {
        super(Brand.class, metadata);
    }

}

