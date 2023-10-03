package fr.customer.accounting.repository;

import fr.customer.accounting.entity.referentials.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findCompanyByCompanyErpCode(String companyErpCode);
}
