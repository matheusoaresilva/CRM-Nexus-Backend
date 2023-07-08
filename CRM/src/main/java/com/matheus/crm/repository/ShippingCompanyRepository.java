package com.matheus.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.crm.entity.ShippingCompany;

@Repository
public interface ShippingCompanyRepository extends JpaRepository<ShippingCompany, Long>{

	Optional<ShippingCompany> findById(Long id);
}