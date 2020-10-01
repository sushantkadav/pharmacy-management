package com.fluxinfotech.pharmacymanagementsystem.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Serializable> {
}
