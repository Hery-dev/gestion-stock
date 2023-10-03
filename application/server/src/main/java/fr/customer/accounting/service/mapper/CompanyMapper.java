package fr.customer.accounting.service.mapper;

import com.accounting.customers.fr.rest.model.company.CompanyDetails;
import fr.customer.accounting.entity.referentials.Company;

import java.util.Objects;
import java.util.Optional;

public final class CompanyMapper {
    private CompanyMapper() {
    }

    public static Optional<CompanyDetails> mapToDomain(Company entity){
        if(Objects.isNull(entity)){
            return Optional.empty();
        }
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompanyErpCode(entity.getCompanyErpCode());
        companyDetails.setCompanyName(entity.getCompanyName());
        companyDetails.setSiren(entity.getSiren());
        companyDetails.setAddress(entity.getCompanyAddress());
        return Optional.of(companyDetails);
    }

    public static Company mapToModel(CompanyDetails companyDetails){
        if(Objects.isNull(companyDetails)){
            return new Company();
        }
        Company company = new Company();
        company.setCompanyErpCode(companyDetails.getCompanyErpCode());
        company.setCompanyName(companyDetails.getCompanyName());
        company.setSiren(companyDetails.getSiren());
        company.setCompanyAddress(companyDetails.getAddress());
        return company;
    }
}
