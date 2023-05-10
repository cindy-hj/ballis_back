package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSelling is a Querydsl query type for Selling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSelling extends EntityPathBase<Selling> {

    private static final long serialVersionUID = -1220422921L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSelling selling = new QSelling("selling");

    public final ListPath<Contract, QContract> contracts = this.<Contract, QContract>createList("contracts", Contract.class, QContract.class, PathInits.DIRECT2);

    public final NumberPath<Integer> dataStatus = createNumber("dataStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> inventoryDiv = createNumber("inventoryDiv", Integer.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final ListPath<Payment, QPayment> payments = this.<Payment, QPayment>createList("payments", Payment.class, QPayment.class, PathInits.DIRECT2);

    public final QProduct product;

    public final NumberPath<Integer> productSize = createNumber("productSize", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sellingStatus = createNumber("sellingStatus", Integer.class);

    public final NumberPath<Integer> wishPrice = createNumber("wishPrice", Integer.class);

    public QSelling(String variable) {
        this(Selling.class, forVariable(variable), INITS);
    }

    public QSelling(Path<? extends Selling> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSelling(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSelling(PathMetadata metadata, PathInits inits) {
        this(Selling.class, metadata, inits);
    }

    public QSelling(Class<? extends Selling> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

