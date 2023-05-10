package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuying is a Querydsl query type for Buying
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuying extends EntityPathBase<Buying> {

    private static final long serialVersionUID = 181833493L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBuying buying = new QBuying("buying");

    public final NumberPath<Integer> buyingStatus = createNumber("buyingStatus", Integer.class);

    public final ListPath<Contract, QContract> contracts = this.<Contract, QContract>createList("contracts", Contract.class, QContract.class, PathInits.DIRECT2);

    public final NumberPath<Integer> dataStatus = createNumber("dataStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final QProduct product;

    public final NumberPath<Integer> productSize = createNumber("productSize", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> wishPrice = createNumber("wishPrice", Integer.class);

    public QBuying(String variable) {
        this(Buying.class, forVariable(variable), INITS);
    }

    public QBuying(Path<? extends Buying> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBuying(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBuying(PathMetadata metadata, PathInits inits) {
        this(Buying.class, metadata, inits);
    }

    public QBuying(Class<? extends Buying> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

