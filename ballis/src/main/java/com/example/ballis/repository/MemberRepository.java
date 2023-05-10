package com.example.ballis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ballis.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
