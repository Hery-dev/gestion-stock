package fr.customer.accounting.repository;

import fr.customer.accounting.entity.referentials.Company;
import fr.customer.accounting.entity.referentials.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
    Optional<PaymentMethod> findPaymentMethodByPaymentMethodCodeAndCompany(String paymentMethodCode, Company company);
}
