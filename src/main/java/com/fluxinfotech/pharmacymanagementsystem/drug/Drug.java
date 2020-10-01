package com.fluxinfotech.pharmacymanagementsystem.drug;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drug {

    @Id
    @GeneratedValue
    private Long id;

    private String drugName;
    private String drugType;
    private Long companyId;
    private String section;
    private Boolean isActive = true;
}
