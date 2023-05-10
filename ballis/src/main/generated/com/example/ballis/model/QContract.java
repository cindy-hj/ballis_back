package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -943562165L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final NumberPath<Long> buyerNumber = createNumber("buyerNumber", Long.class);

    public final QBuying buying;

    public final NumberPath<Integer> buyingStatus = createNumber("buyingStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> contractDate = createDateTime("contractDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> paidDate = createDateTime("paidDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> paidPrice = createNumber("paidPrice", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QProduct product;

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> sellerNumber = createNumber("sellerNumber", Long.class);

    public final QSelling selling;

    public final NumberPath<Integer> sellingStatus = createNumber("sellingStatus", Integer.class);

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buying = inits.isInitialized("buying") ? new QBuying(forProperty("buying"), inits.get("buying")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.selling = inits.isInitialized("selling") ? new QSelling(forProperty("selling"), inits.get("selling")) : null;
    }

}

