package com.example.ballis.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 481613331L;

    public static final QMember member = new QMember("member1");

    public final StringPath accountNumber = createString("accountNumber");

    public final ListPath<Address, QAddress> addresss = this.<Address, QAddress>createList("addresss", Address.class, QAddress.class, PathInits.DIRECT2);

    public final StringPath bankName = createString("bankName");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final ListPath<Buying, QBuying> buyings = this.<Buying, QBuying>createList("buyings", Buying.class, QBuying.class, PathInits.DIRECT2);

    public final ListPath<Card, QCard> cards = this.<Card, QCard>createList("cards", Card.class, QCard.class, PathInits.DIRECT2);

    public final StringPath depositor = createString("depositor");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final ListPath<Inquiry, QInquiry> inquirys = this.<Inquiry, QInquiry>createList("inquirys", Inquiry.class, QInquiry.class, PathInits.DIRECT2);

    public final NumberPath<Long> memberNumber = createNumber("memberNumber", Long.class);

    public final NumberPath<Integer> memberStatus = createNumber("memberStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<Payment, QPayment> payments = this.<Payment, QPayment>createList("payments", Payment.class, QPayment.class, PathInits.DIRECT2);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> registDate = createDateTime("registDate", java.time.LocalDateTime.class);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<Selling, QSelling> sellings = this.<Selling, QSelling>createList("sellings", Selling.class, QSelling.class, PathInits.DIRECT2);

    public final ListPath<Wish, QWish> wishs = this.<Wish, QWish>createList("wishs", Wish.class, QWish.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

