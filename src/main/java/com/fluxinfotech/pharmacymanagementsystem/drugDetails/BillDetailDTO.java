package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDetailDTO {

  private String date;
  private String billNo;
  private String patientName;
  private String patientContactNo;
  private String doctorName;
  private List<DrugDetailDTO> drugDetailDTOList;
  private String tax;
  private String discount;
  private String totalAmount;
}
