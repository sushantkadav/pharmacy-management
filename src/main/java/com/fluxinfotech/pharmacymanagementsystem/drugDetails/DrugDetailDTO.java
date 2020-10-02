package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrugDetailDTO {

  private Long id;
  private Long drugId;
  private String drugName;
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
