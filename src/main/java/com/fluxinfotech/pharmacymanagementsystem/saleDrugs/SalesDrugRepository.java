package com.fluxinfotech.pharmacymanagementsystem.saleDrugs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDrugRepository extends JpaRepository<SalesDrug, Long> {
}
