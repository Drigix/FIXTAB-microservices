package com.fixtab.company.mapper;

import org.mapstruct.Mapper;

import com.fixtab.company.model.Company;
import com.fixtab.company.model.dto.CompanyDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CompanyMapper {

    CompanyDTO toDto(Company client);

    Company toEntity(CompanyDTO clientDTO);

    default Company fromId (Long id) {
        if (id == null) {
            return null;
        }
        Company client = new Company();
        client.setId(id);
        return client;
    }
    
}
