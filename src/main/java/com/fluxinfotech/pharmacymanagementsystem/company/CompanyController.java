package com.fluxinfotech.pharmacymanagementsystem.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> saveOrUpdate(@RequestBody CompanyDTO payload) {
        CompanyDTO companyDTO = companyService.saveOrUpdate(payload);
        return ResponseEntity.ok(companyDTO);
    }

    @GetMapping
    public Page<CompanyDTO> getAllCompany(Pageable pageable) {
        return companyService.getAllCompany(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.getById(id);
        return ResponseEntity.ok(companyDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        Boolean value = companyService.deleteCompany(id);
        return ResponseEntity.ok(value);
    }
}
