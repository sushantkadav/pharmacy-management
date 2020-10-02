package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugDetailRepository extends JpaRepository<DrugDetail, Long> {

  @Query(value = "select *\n" +
          "from drug_detail dg\n" +
          "where dg.drug_id = :drugId\n" +
          "  and dg.expiry_date > now() and dg.is_active = true ", nativeQuery = true)
  List<DrugDetail> findAllByDrugIdAndIsActiveTrue(@Param("drugId") Long drugId);

  @Query(value = "select *\n" +
          "from drug_detail dg\n" +
          "where dg.expiry_date <= now() and dg.is_active = true ", nativeQuery = true)
  List<DrugDetail> findAllByExpiredDrug();

  @Modifying
  @Query(value = "update drug_detail dg set dg.balance_quantity = dg.balance_quantity - :balanceQuantity " +
          "where dg.id =:id", nativeQuery = true)
  void updateBalanceQuantity(@Param("id") Long id, @Param("balanceQuantity") Long balanceQuantity);


}

