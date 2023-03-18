package com.fixtab.company.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fixtab.company.mapper.CompanyMapper;
import com.fixtab.company.model.Company;
import com.fixtab.company.model.dto.CompanyDTO;
import com.fixtab.company.repository.CompanyRepository;
import com.fixtab.company.service.CompanyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public void createCompany(CompanyDTO companyDTO) {
        Company company = this.companyMapper.toEntity(companyDTO);

        companyRepository.save(company);
        log.info("User {} is created", company.getId());
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.getById(id);
        return companyMapper.toDto(company);
    }


}
