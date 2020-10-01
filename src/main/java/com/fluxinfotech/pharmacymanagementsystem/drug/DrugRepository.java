package com.fluxinfotech.pharmacymanagementsystem.drug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Serializable> {

}
