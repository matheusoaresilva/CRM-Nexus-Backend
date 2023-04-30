package com.matheus.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.crm.entity.Supplier;
import com.matheus.crm.exception.NotFoundException;
import com.matheus.crm.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	
	public Optional<Supplier> findSupplierById(Long id){
		Optional<Supplier> supplierOptional = supplierRepository.findById(id);
		if (!supplierOptional.isPresent()) {
			throw new NotFoundException("ID: " + id + " not found!");
		}
		
		return supplierOptional;
	}
	
	public List<Supplier> findAllSuppliers(){
		return supplierRepository.findAll();
	}
	
	public Supplier addSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
}
