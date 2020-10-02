package com.fluxinfotech.pharmacymanagementsystem.drug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Serializable> {
    List<Drug> findAllByIdIn(Set<Long> drugIds);
}
