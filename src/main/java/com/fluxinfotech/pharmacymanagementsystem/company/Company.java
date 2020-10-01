package com.fluxinfotech.pharmacymanagementsystem.company;

import com.fluxinfotech.pharmacymanagementsystem.common.Auditable;
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
public class Company extends Auditable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String companyCode;
    private String companyName;
    private String companyAddress;
    private String companyDetails;
    private Boolean isActive = true;
}
