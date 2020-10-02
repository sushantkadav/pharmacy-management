package com.fluxinfotech.pharmacymanagementsystem.drugDetails;

import com.fluxinfotech.pharmacymanagementsystem.common.TemplateResolver;
import com.fluxinfotech.pharmacymanagementsystem.drug.DrugService;
import com.fluxinfotech.pharmacymanagementsystem.saleDrugs.SalesDrug;
import com.fluxinfotech.pharmacymanagementsystem.saleDrugs.SalesDrugDTO;
import com.fluxinfotech.pharmacymanagementsystem.saleDrugs.SalesDrugRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class DrugDetailService {

  private DrugDetailRepository drugDetailRepository;
  private SalesDrugRepository salesDrugRepository;
  private TemplateResolver templateResolver;
  private DrugService drugService;
  private ModelMapper modelMapper;

  public DrugDetailService(DrugDetailRepository drugDetailRepository, SalesDrugRepository salesDrugRepository, TemplateResolver templateResolver, DrugService drugService, ModelMapper modelMapper) {
    this.drugDetailRepository = drugDetailRepository;
    this.salesDrugRepository = salesDrugRepository;
    this.templateResolver = templateResolver;
    this.drugService = drugService;
    this.modelMapper = modelMapper;
  }

  public DrugDetailDTO saveOrUpDate(DrugDetailDTO drugDetailDTO) {
    DrugDetail drugDetail = new DrugDetail();
    if (drugDetailDTO.getId() != null) {
      drugDetail = findEntityById(drugDetailDTO.getId());
    }
    modelMapper.map(drugDetailDTO, drugDetail);
    DrugDetail saveDrugDetail = drugDetailRepository.save(drugDetail);
    return modelMapper.map(saveDrugDetail, DrugDetailDTO.class);
  }

  public List<DrugDetailDTO> findDrugDetailsByDrugId(Long drugId) {
    List<DrugDetail> drugDetailList = drugDetailRepository.findAllByDrugIdAndIsActiveTrue(drugId);
    List<DrugDetailDTO> drugDetailListDTO = drugDetailList.stream()
            .map(drugDetail -> modelMapper.map(drugDetail, DrugDetailDTO.class))
            .collect(Collectors.toList());
    setDrugName(drugDetailListDTO);
    return drugDetailListDTO;
  }

  public DrugDetailDTO findById(Long id) {
    DrugDetail drugDetail = findEntityById(id);
    DrugDetailDTO drugDetailDTO = null;
    if (drugDetail != null) {
      drugDetailDTO = modelMapper.map(drugDetail, DrugDetailDTO.class);
      setDrugName(Collections.singletonList(drugDetailDTO));
    }
    return drugDetailDTO;
  }

  private DrugDetail findEntityById(Long id) {
    Optional<DrugDetail> drugDetailOptional = drugDetailRepository.findById(id);
    return drugDetailOptional.orElse(null);
  }

  public List<DrugDetailDTO> findAllByExpiredDrug() {
    List<DrugDetail> drugDetailList = drugDetailRepository.findAllByExpiredDrug();
    List<DrugDetailDTO> drugDetailListDTO = drugDetailList.stream()
            .map(drugDetail -> modelMapper.map(drugDetail, DrugDetailDTO.class))
            .collect(Collectors.toList());
    setDrugName(drugDetailListDTO);
    return drugDetailListDTO;
  }

  private void setDrugName(List<DrugDetailDTO> drugDetailDTOS) {
    Set<Long> drugIds = drugDetailDTOS.stream()
            .map(DrugDetailDTO::getDrugId)
            .collect(Collectors.toSet());
    Map<Long, String> drugIdNameMap = drugService.getDrugIdNameMap(drugIds);
    drugDetailDTOS.forEach(drugDetailDTO -> {
      drugDetailDTO.setDrugName(drugIdNameMap.get(drugDetailDTO.getDrugId()));
    });
  }

  public Boolean deleteDrugDetail(Long id) {
    DrugDetail drugDetail = findEntityById(id);
    drugDetail.setIsActive(false);
    drugDetailRepository.save(drugDetail);
    return true;
  }

  public BillDetailDTO data() {
    List<DrugDetailDTO> drugDetailDTOS = Collections.singletonList(DrugDetailDTO.builder().id(1L).drugName("drug1").batchNo("A1").drugQuantity(1L).drugRate(100.0).build());
    return BillDetailDTO.builder()
            .date(LocalDate.now().toString())
            .billNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy-HHmm")))
            .patientName("ALA")
            .patientContactNo("95276873567")
            .doctorName("BABA")
            .drugDetailDTOList(drugDetailDTOS)
            .tax("12%")
            .discount("10")
            .totalAmount("1000")
            .build();
  }

  public byte[] generateBill(BillDetailDTO billDetailDTO) {
    Map<String, Object> map = new HashMap<>();
    checkDataIfExist(billDetailDTO);//setDefault value....
    map.put("billData", billDetailDTO);
    String code = templateResolver.resolveTemplate("BillTemplate.html", map);
    //
    List<DrugDetailDTO> drugDetailDTOList = billDetailDTO.getDrugDetailDTOList();
    List<SalesDrug> salesDrugs = new ArrayList<>();
    drugDetailDTOList.forEach(drugDetailDTO -> {
      drugDetailRepository.updateBalanceQuantity(drugDetailDTO.getId(), drugDetailDTO.getBalanceQuantity());
      SalesDrug salesDrug = new SalesDrug();
      salesDrug.setBatchNo(drugDetailDTO.getBatchNo());
      salesDrug.setBillNo(billDetailDTO.getBillNo());
      salesDrug.setDrugName(drugDetailDTO.getDrugName());
      salesDrug.setRate(drugDetailDTO.getDrugRate());
      salesDrug.setQuantity(drugDetailDTO.getDrugQuantity());
      salesDrug.setAmount(drugDetailDTO.getAmount());
      salesDrug.setDate(billDetailDTO.getDate());

      salesDrugs.add(salesDrug);
    });
    salesDrugRepository.saveAll(salesDrugs);
    //
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(code);
      renderer.layout();
      renderer.createPDF(bos);
      bos.close();
      // write to document
    } catch (IOException | com.lowagie.text.DocumentException e) {
      log.error("IOException While Generating Berthing LineUp Report :", e);
    }
    return bos.toByteArray();
  }

  private void checkDataIfExist(BillDetailDTO billDetailDTO) {
    if (billDetailDTO.getBillNo() == null)
      billDetailDTO.setBillNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy-HHmm")));
    if (billDetailDTO.getDate() == null) billDetailDTO.setDate(LocalDate.now().toString());
  }

  public List<SalesDrugDTO> findAllSalesDrugs() {
    List<SalesDrug> salesDrugs = salesDrugRepository.findAll();
    return salesDrugs.stream()
            .map(salesDrug -> modelMapper.map(salesDrug, SalesDrugDTO.class))
            .collect(Collectors.toList());
  }

}
