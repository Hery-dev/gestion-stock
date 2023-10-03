package fr.customer.accounting.service.referentials;

import com.accounting.customers.fr.rest.model.paymentMethod.PaymentMethodDetails;
import fr.customer.accounting.entity.referentials.Company;
import fr.customer.accounting.entity.referentials.PaymentMethod;
import fr.customer.accounting.repository.CompanyRepository;
import fr.customer.accounting.repository.PaymentMethodRepository;
import fr.customer.accounting.service.mapper.PaymentMethodMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final CompanyRepository companyRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, CompanyRepository companyRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.companyRepository = companyRepository;
    }

    public Optional<PaymentMethodDetails> createPaymentMethod(PaymentMethodDetails paymentMethodDetails){
        return companyRepository.findCompanyByCompanyErpCode(paymentMethodDetails.getCompanyErpCode())
                .map(company -> addCompany(PaymentMethodMapper.mapToModel(paymentMethodDetails), company))
                .filter(paymentMethod -> paymentMethodRepository.findPaymentMethodByPaymentMethodCodeAndCompany(paymentMethod.getPaymentMethodCode(), paymentMethod.getCompany()).isEmpty())
                .map(paymentMethod -> {
                    paymentMethod.setCreatedBy("dev");
                    paymentMethod.setCreatedDate(Instant.now());
                    return paymentMethod;
                })
                .flatMap(PaymentMethodMapper::mapToDomain);
    }

    public Optional<PaymentMethodDetails> getPaymentMethodByPaymentMethodCodeAndCompanyErpCode(String paymentMethodCode, String companyErpCode){
        return companyRepository.findCompanyByCompanyErpCode(companyErpCode)
                .flatMap(company -> paymentMethodRepository.findPaymentMethodByPaymentMethodCodeAndCompany(paymentMethodCode, company))
                .flatMap(PaymentMethodMapper::mapToDomain);
    }

    public Optional<PaymentMethodDetails> updatePaymentMethodByPaymentMethodCodeAndCompanyErpCode(
            String paymentMethodCode, String companyErpCode, PaymentMethodDetails paymentMethodDetails){
        return companyRepository.findCompanyByCompanyErpCode(companyErpCode)
                .flatMap(company -> paymentMethodRepository.findPaymentMethodByPaymentMethodCodeAndCompany(paymentMethodCode, company))
                .map(paymentMethod -> updatePaymentMethod(paymentMethod, paymentMethodDetails))
                .flatMap(PaymentMethodMapper::mapToDomain);
    }

    private PaymentMethod updatePaymentMethod(PaymentMethod oldPaymentMethod, PaymentMethodDetails paymentMethodDetails){
       updateIfNotBlank(oldPaymentMethod::setPaymentMethodCode, paymentMethodDetails.getPaymentMethodCode());
       updateIfNotBlank(oldPaymentMethod::setPaymentMethodName, paymentMethodDetails.getPaymentMethodName());
       oldPaymentMethod.setActive(paymentMethodDetails.getIsActive());
       oldPaymentMethod.setLastModifiedBy("dev");
       oldPaymentMethod.setLastModifiedDate(Instant.now());
       return oldPaymentMethod;
    }

    private PaymentMethod addCompany(PaymentMethod paymentMethod, Company company){
        paymentMethod.setCompany(company);
        return paymentMethod;
    }

    private void updateIfNotBlank(Consumer<String> updater, String newValue) {
        if (StringUtils.isNotBlank(newValue)) {
            updater.accept(newValue);
        }
    }

    public List<PaymentMethodDetails> findAll(){
        return List.of(new PaymentMethodDetails());
    }
}
