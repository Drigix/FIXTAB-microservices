package com.fixtab.company.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fixtab.company.model.dto.CompanyDTO;
import com.fixtab.company.service.CompanyService;
import com.fixtab.company.service.Impl.CompanyServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.createCompany(companyDTO);
    }

}
