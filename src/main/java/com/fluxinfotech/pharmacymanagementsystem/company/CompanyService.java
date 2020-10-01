package com.fluxinfotech.pharmacymanagementsystem.company;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    public CompanyDTO saveOrUpdate(CompanyDTO payload) {
        CompanyDTO companyDTO = null;
        try {
            Company company = new Company();
            if (payload.getId() != null) {
                company = getEntityById(payload.getId());
            }
            modelMapper.map(payload, company);

            Company savedCompany = companyRepository.save(company);
            companyDTO = modelMapper.map(savedCompany, CompanyDTO.class);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return companyDTO;
    }

    private Company getEntityById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        }
        return null;
    }

    public CompanyDTO getById(Long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            return modelMapper.map(companyOptional.get(), CompanyDTO.class);
        }
        return null;
    }

    public Page<CompanyDTO> getAllCompany(Pageable pageable) {
        Page<Company> companies = companyRepository.findAll(pageable);
        Page<CompanyDTO> companyDTOList = companies
                .map(company -> modelMapper.map(company, CompanyDTO.class));
        return companyDTOList;
    }

    @Transactional
    public Boolean deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return true;
    }
}
