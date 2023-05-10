package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 786755734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final QBrand brand;

    public final ListPath<Buying, QBuying> buyings = this.<Buying, QBuying>createList("buyings", Buying.class, QBuying.class, PathInits.DIRECT2);

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final StringPath color = createString("color");

    public final ListPath<Contract, QContract> contracts = this.<Contract, QContract>createList("contracts", Contract.class, QContract.class, PathInits.DIRECT2);

    public final NumberPath<Integer> dataStatus = createNumber("dataStatus", Integer.class);

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> launchingDate = createDate("launchingDate", java.time.LocalDate.class);

    public final NumberPath<Integer> launchingPrice = createNumber("launchingPrice", Integer.class);

    public final StringPath modelNumber = createString("modelNumber");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath productEngName = createString("productEngName");

    public final StringPath productKorName = createString("productKorName");

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> resellTarget = createNumber("resellTarget", Integer.class);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<Selling, QSelling> sellings = this.<Selling, QSelling>createList("sellings", Selling.class, QSelling.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sizeMax = createNumber("sizeMax", Integer.class);

    public final NumberPath<Integer> sizeMin = createNumber("sizeMin", Integer.class);

    public final NumberPath<Integer> sizeUnit = createNumber("sizeUnit", Integer.class);

    public final ListPath<Wish, QWish> wishs = this.<Wish, QWish>createList("wishs", Wish.class, QWish.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new QBrand(forProperty("brand")) : null;
    }

}

