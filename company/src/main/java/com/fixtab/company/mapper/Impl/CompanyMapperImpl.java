package com.fixtab.company.mapper.Impl;

import org.springframework.stereotype.Component;

import com.fixtab.company.mapper.CompanyMapper;
import com.fixtab.company.model.Company;
import com.fixtab.company.model.dto.CompanyDTO;

@Component
public class CompanyMapperImpl implements CompanyMapper {
    
    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        return company;
    }

    @Override
    public CompanyDTO toDto(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }
}
