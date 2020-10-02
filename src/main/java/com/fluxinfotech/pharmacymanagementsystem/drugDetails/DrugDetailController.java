package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import com.fluxinfotech.pharmacymanagementsystem.saleDrugs.SalesDrugDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/drug-details")
public class DrugDetailController {

  private DrugDetailService drugDetailService;

  public DrugDetailController(DrugDetailService drugDetailService) {
    this.drugDetailService = drugDetailService;
  }

  @PostMapping
  public ResponseEntity<DrugDetailDTO> saveOrUpdate(@RequestBody DrugDetailDTO payload) {
    DrugDetailDTO drugDetailDTO = drugDetailService.saveOrUpDate(payload);
    return ResponseEntity.ok().body(drugDetailDTO);
  }

  @GetMapping("/by-drug/{drugId}")
  public ResponseEntity<List<DrugDetailDTO>> findDrugDetailsByDrugId(@PathVariable Long drugId) {
    return ResponseEntity.ok().body(drugDetailService.findDrugDetailsByDrugId(drugId));
  }

  @GetMapping("/expiry-drugs")
  public ResponseEntity<List<DrugDetailDTO>> findAllByExpiredDrug() {
    return ResponseEntity.ok().body(drugDetailService.findAllByExpiredDrug());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DrugDetailDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok().body(drugDetailService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCompany(@PathVariable Long id) {
    Boolean value = drugDetailService.deleteDrugDetail(id);
    return ResponseEntity.ok(value);
  }

  @GetMapping("/generate-bill")
  public ResponseEntity<byte[]> generateBill(@RequestBody BillDetailDTO billDetailDTO) throws IOException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PDF);
    String billDate = billDetailDTO.getDate() != null ? billDetailDTO.getDate() : LocalDate.now().toString();
    httpHeaders.add("Content-Disposition", "filename=\"" + "Bill-" + billDate + ".pdf\"");
    return ResponseEntity.ok().headers(httpHeaders).body(drugDetailService.generateBill(billDetailDTO));
  }

  @GetMapping("/sales-drugs")
  public ResponseEntity<List<SalesDrugDTO>> findAllSalesDrugs() {
    return ResponseEntity.ok().body(drugDetailService.findAllSalesDrugs());
  }

}
