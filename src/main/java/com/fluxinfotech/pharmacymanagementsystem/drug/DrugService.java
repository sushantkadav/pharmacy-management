package com.fluxinfotech.pharmacymanagementsystem.drug;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class DrugService {

    private DrugRepository drugRepository;
    private ModelMapper modelMapper;

    public DrugService(DrugRepository drugRepository, ModelMapper modelMapper) {
        this.drugRepository = drugRepository;
        this.modelMapper = modelMapper;
    }

    public DrugDTO saveOrUpdate(DrugDTO payload) {
        DrugDTO drugDTO = null;
        try {
            Drug drug = new Drug();
            if (payload.getId() != null) {
                drug = getEntityById(payload.getId());
            }
            modelMapper.map(payload, drug);

            Drug savedDrug = drugRepository.save(drug);
            drugDTO = modelMapper.map(savedDrug, DrugDTO.class);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return drugDTO;
    }

    private Drug getEntityById(Long id) {
        Optional<Drug> drugOptional = drugRepository.findById(id);
        if (drugOptional.isPresent()) {
            return drugOptional.get();
        }
        return null;
    }

    public DrugDTO getById(Long id) {

        Optional<Drug> drugOptional = drugRepository.findById(id);
        if (drugOptional.isPresent()) {
            return modelMapper.map(drugOptional.get(), DrugDTO.class);
        }
        return null;
    }

    public Page<DrugDTO> getAllDrug(Pageable pageable) {
        Page<Drug> drugs = drugRepository.findAll(pageable);
        Page<DrugDTO> drugDTOList = drugs
                .map(drug -> modelMapper.map(drug, DrugDTO.class));
        return drugDTOList;
    }

    @Transactional
    public Boolean deleteDrug(Long id) {
        drugRepository.deleteById(id);
        return true;
    }

    public Map<Long, String> getDrugIdNameMap(Set<Long> drugIds) {
        if (CollectionUtils.isEmpty(drugIds)) return Collections.emptyMap();
        List<Drug> drugList = drugRepository.findAllByIdIn(drugIds);
        Map<Long, String> drugIdNameMap = new HashMap<>();

        drugList.forEach(drug -> drugIdNameMap.put(drug.getId(),drug.getDrugName()));
        return drugIdNameMap;
    }


}
