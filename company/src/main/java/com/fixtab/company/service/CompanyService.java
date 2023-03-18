package com.fixtab.company.service;

import com.fixtab.company.model.dto.CompanyDTO;

public interface CompanyService {

    public void createCompany(CompanyDTO companyDTO);

    public CompanyDTO getCompanyById(Long id);
    
}
