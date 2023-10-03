package fr.customer.accounting.service.mapper;

import com.accounting.customers.fr.rest.model.company.CompanyDetails;
import com.accounting.customers.fr.rest.model.paymentMethod.PaymentMethodDetails;
import fr.customer.accounting.entity.referentials.Company;
import fr.customer.accounting.entity.referentials.PaymentMethod;

import java.util.Objects;
import java.util.Optional;

public final class PaymentMethodMapper {
    private PaymentMethodMapper() {
    }

    public static Optional<PaymentMethodDetails> mapToDomain(PaymentMethod paymentMethod){
        if(Objects.isNull(paymentMethod)){
            return Optional.empty();
        }
        PaymentMethodDetails paymentMethodDetails = new PaymentMethodDetails();
        paymentMethodDetails.setPaymentMethodCode(paymentMethod.getPaymentMethodCode());
        paymentMethodDetails.setPaymentMethodName(paymentMethod.getPaymentMethodName());
        paymentMethodDetails.setIsActive(paymentMethod.isActive());
        return Optional.of(paymentMethodDetails);
    }

    public static PaymentMethod mapToModel(PaymentMethodDetails paymentMethodDetails){
        if(Objects.isNull(paymentMethodDetails)){
            return new PaymentMethod();
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodCode(paymentMethodDetails.getPaymentMethodCode());
        paymentMethod.setPaymentMethodName(paymentMethodDetails.getPaymentMethodName());
        paymentMethod.setActive(paymentMethodDetails.getIsActive());
        return paymentMethod;
    }
}
