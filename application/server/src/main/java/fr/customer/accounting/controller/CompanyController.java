package fr.customer.accounting.controller;

import com.accounting.customers.fr.api.company.CompanyApi;
import com.accounting.customers.fr.rest.model.company.CompanyDetails;
import com.accounting.customers.fr.rest.model.company.CompanyDetailsResources;
import com.accounting.customers.fr.rest.model.company.CompanyResources;
import fr.customer.accounting.service.referentials.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController implements CompanyApi {

    private final String COMPANY_DETAIL_MESSAGE = "Company was created";
    private final String COMPANY_DETAILS_CODE = "COMPANY_DETAILS_CODE";
    private final String COMPANY_NOT_SAVED = "Company not saved";
    private final String COMPANY_DETAILS = "COMPANY_DETAILS";
    private final String COMPANY_NOT_FOUND = "Company details not found";
    private final String NOT_FOUND = "COMPANY_NOT_FOUND";
    private final String COMPANY_UPDATED_MESSAGE = "Company was updated successfully!";
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<CompanyDetailsResources> createCompany(CompanyDetails companyDetails) {
        return companyService.createCompany(companyDetails)
                .map(response -> ResponseEntity.ok().body(createCompanyDetailsResources(COMPANY_DETAIL_MESSAGE, COMPANY_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createCompanyDetailsResources(COMPANY_NOT_SAVED, COMPANY_DETAILS_CODE, null), HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<CompanyResources> findAll() {
        return ResponseEntity.ok().body(createCompanyResources(COMPANY_DETAILS, COMPANY_DETAILS, companyService.findALl()));
    }

    @Override
    public ResponseEntity<CompanyDetailsResources> getCompanyByCompanyErpCode(String companyErpCode) {
        return companyService.getCompanyByCompanyErpCode(companyErpCode)
                .map(response -> ResponseEntity.ok().body(createCompanyDetailsResources(COMPANY_DETAILS, COMPANY_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createCompanyDetailsResources(COMPANY_NOT_FOUND, NOT_FOUND, null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<CompanyDetailsResources> updateCompanyByCompanyErpCode(String companyErpCode, CompanyDetails companyDetails) {
        return companyService.updateCompanyByCompanyErpCode(companyErpCode, companyDetails)
                .map(response -> ResponseEntity.ok().body(createCompanyDetailsResources(COMPANY_UPDATED_MESSAGE, COMPANY_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createCompanyDetailsResources(COMPANY_NOT_FOUND, NOT_FOUND, null), HttpStatus.NOT_FOUND));
    }

    private CompanyDetailsResources createCompanyDetailsResources(String message, String code, CompanyDetails companyDetails){
        CompanyDetailsResources companyDetailsResources = new CompanyDetailsResources();
        companyDetailsResources.setMessage(message);
        companyDetailsResources.setCode(code);
        companyDetailsResources.setCompanyDetails(companyDetails);
        return companyDetailsResources;
    }

    private CompanyResources createCompanyResources(String message, String code, List<CompanyDetails> companyDetails){
        CompanyResources companyResources = new CompanyResources();
        companyResources.setMessage(message);
        companyResources.setCode(code);
        companyResources.companyDetails(companyDetails);
        return companyResources;
    }
}
