package com.fluxinfotech.pharmacymanagementsystem.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String companyCode;
    private String companyName;
    private String companyAddress;
    private String companyDetails;
    private Boolean isActive = true;
}
