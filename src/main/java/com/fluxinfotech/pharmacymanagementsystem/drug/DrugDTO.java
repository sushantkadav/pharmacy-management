package com.fluxinfotech.pharmacymanagementsystem.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {

    private Long id;

    private String drugName;
    private String drugType;
    private Long companyId;
    private String section;
    private Boolean isActive;

}
