package com.fluxinfotech.pharmacymanagementsystem.drug;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drug")
public class DrugController {

    private DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping
    public ResponseEntity<DrugDTO> saveOrUpdate(@RequestBody DrugDTO payload) {
        DrugDTO drugDTO = drugService.saveOrUpdate(payload);
        return ResponseEntity.ok(drugDTO);
    }

    @GetMapping
    public Page<DrugDTO> getAllCompany(Pageable pageable) {
        return drugService.getAllDrug(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDTO> getCompanyById(@PathVariable Long id) {
        DrugDTO companyDTO = drugService.getById(id);
        return ResponseEntity.ok(companyDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        Boolean value = drugService.deleteDrug(id);
        return ResponseEntity.ok(value);
    }
}
