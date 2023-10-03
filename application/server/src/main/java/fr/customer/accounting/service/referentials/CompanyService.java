package fr.customer.accounting.service.referentials;

import com.accounting.customers.fr.rest.model.company.CompanyDetails;
import fr.customer.accounting.entity.referentials.Company;
import fr.customer.accounting.repository.CompanyRepository;
import fr.customer.accounting.service.mapper.CompanyMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyDetails> createCompany(CompanyDetails companyDetails){
        if(companyRepository.findCompanyByCompanyErpCode(companyDetails.getCompanyErpCode()).isPresent()){
            return Optional.empty();
        }

        Company company = new Company();
        company.setCompanyErpCode(companyDetails.getCompanyErpCode());
        company.setCompanyName(companyDetails.getCompanyName());
        company.setSiren(companyDetails.getSiren());
        company.setCompanyAddress(companyDetails.getAddress());
        company.setCreatedBy("dev");
        company.setCreatedDate(Instant.now());
        companyRepository.save(company);

        return CompanyMapper.mapToDomain(company);
    }

    public Optional<CompanyDetails> getCompanyByCompanyErpCode(String companyErpCode){
        return companyRepository.findCompanyByCompanyErpCode(companyErpCode)
                .flatMap(CompanyMapper::mapToDomain);
    }
    @Transactional
    public Optional<CompanyDetails> updateCompanyByCompanyErpCode(String companyErpCode, CompanyDetails companyDetails){
        return companyRepository.findCompanyByCompanyErpCode(companyErpCode)
                .map(company -> updateCompany(company, companyDetails))
                .flatMap(CompanyMapper::mapToDomain);
    }

    public List<CompanyDetails> findALl(){
        return companyRepository.findAll().stream()
                .map(CompanyMapper::mapToDomain)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private Company updateCompany(Company oldCompany, CompanyDetails companyDetails){
        updateIfNotBlank(oldCompany::setCompanyName, companyDetails.getCompanyName());
        updateIfNotBlank(oldCompany::setSiren, companyDetails.getSiren());
        updateIfNotBlank(oldCompany::setCompanyAddress, companyDetails.getAddress());
        return oldCompany;
    }

    private void updateIfNotBlank(Consumer<String> updater, String newValue) {
        if (StringUtils.isNotBlank(newValue)) {
            updater.accept(newValue);
        }
    }
}
