package fr.customer.accounting.controller;

import com.accounting.customers.fr.api.paymentMethod.PaymentMethodApi;
import com.accounting.customers.fr.rest.model.paymentMethod.PaymentMehtodResources;
import com.accounting.customers.fr.rest.model.paymentMethod.PaymentMethodDetails;
import com.accounting.customers.fr.rest.model.paymentMethod.PaymentMethodDetailsResources;
import fr.customer.accounting.service.referentials.PaymentMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentMethodController implements PaymentMethodApi {
    private final String PAYMENT_METHOD_DETAILS_MESSAGE = "Payment method was created";
    private final String PAYMENT_METHOD_DETAILS_CODE = "PAYMENT_METHOD_DETAILS_CODE";
    private final String PAYMENT_METHOD_NOT_SAVED = "Payment method not saved";
    private final String PAYMENT_METHOD_DETAILS = "PAYMENT_METHOD_DETAILS";
    private final String PAYMENT_METHOD_NOT_FOUND = "Payment method details not found";
    private final String NOT_FOUND = "PAYMENT_METHOD_NOT_FOUND";
    private final String PAYMENT_METHOD_UPDATED_MESSAGE = "Payment method was updated successfully!";
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public ResponseEntity<PaymentMethodDetailsResources> createPaymentMethod(PaymentMethodDetails paymentMethodDetails) {
        return paymentMethodService.createPaymentMethod(paymentMethodDetails)
                .map(response -> ResponseEntity.ok(createPaymentMethodDetailsResources(PAYMENT_METHOD_DETAILS_MESSAGE, PAYMENT_METHOD_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createPaymentMethodDetailsResources(PAYMENT_METHOD_NOT_SAVED, PAYMENT_METHOD_DETAILS_CODE, null), HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<PaymentMehtodResources> findAll() {
        return ResponseEntity.ok().body(createPaymentMehtodResources(PAYMENT_METHOD_DETAILS, PAYMENT_METHOD_DETAILS_CODE,paymentMethodService.findAll()));
    }

    @Override
    public ResponseEntity<PaymentMethodDetailsResources> getPaymentMethodByPaymentMethodCodeAndCompanyErpCode(String paymentMethodCode, String companyErpCode) {
        return paymentMethodService.getPaymentMethodByPaymentMethodCodeAndCompanyErpCode(paymentMethodCode, companyErpCode)
                .map(response -> ResponseEntity.ok().body(createPaymentMethodDetailsResources(PAYMENT_METHOD_DETAILS, PAYMENT_METHOD_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createPaymentMethodDetailsResources(PAYMENT_METHOD_NOT_FOUND, NOT_FOUND, null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<PaymentMethodDetailsResources> updatePaymentMethodByPaymentMethodCodeAndCompanyErpCode(String paymentMethodCode, String companyErpCode, PaymentMethodDetails paymentMethodDetails) {
        return paymentMethodService.updatePaymentMethodByPaymentMethodCodeAndCompanyErpCode(paymentMethodCode, companyErpCode, paymentMethodDetails)
                .map(response -> ResponseEntity.ok().body(createPaymentMethodDetailsResources(PAYMENT_METHOD_UPDATED_MESSAGE, PAYMENT_METHOD_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(createPaymentMethodDetailsResources(PAYMENT_METHOD_NOT_FOUND, NOT_FOUND, null), HttpStatus.NOT_FOUND));
    }

    private PaymentMehtodResources createPaymentMehtodResources(String message, String code, List<PaymentMethodDetails>paymentMethodDetails){
        PaymentMehtodResources paymentMehtodResources = new PaymentMehtodResources();
        paymentMehtodResources.setMessage(message);
        paymentMehtodResources.setCode(code);
        paymentMehtodResources.setPaymentMethodDetails(paymentMethodDetails);
        return paymentMehtodResources;
    }

    private PaymentMethodDetailsResources createPaymentMethodDetailsResources(String message, String code, PaymentMethodDetails paymentMethodDetails){
        PaymentMethodDetailsResources paymentMethodDetailsResources = new PaymentMethodDetailsResources();
        paymentMethodDetailsResources.setMessage(message);
        paymentMethodDetailsResources.setCode(code);
        paymentMethodDetailsResources.setPaymentMethodDetails(paymentMethodDetails);
        return paymentMethodDetailsResources;
    }
}
