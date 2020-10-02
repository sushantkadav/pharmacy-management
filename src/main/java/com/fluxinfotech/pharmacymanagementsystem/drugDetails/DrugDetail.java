package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fluxinfotech.pharmacymanagementsystem.common.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrugDetail extends Auditable<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long drugId;
  private String batchNo;
  private Double drugRate;
  private Long drugQuantity;
  private Double amount;
  private Long balanceQuantity;
  private String purchaseDate;
  private String productionDate;
  private String expiryDate;
  private Boolean isActive = true;
}
