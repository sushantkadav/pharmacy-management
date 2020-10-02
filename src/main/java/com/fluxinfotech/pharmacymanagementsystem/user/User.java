package com.fluxinfotech.pharmacymanagementsystem.user;

import com.fluxinfotech.pharmacymanagementsystem.common.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends Auditable<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String mobileNo;
    private Boolean isActive = true;
}
