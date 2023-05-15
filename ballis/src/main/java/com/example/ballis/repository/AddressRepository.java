package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ballis.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByMemberMemberNumber(Long member);

}
