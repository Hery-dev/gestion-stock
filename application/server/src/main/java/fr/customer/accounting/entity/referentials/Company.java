package fr.customer.accounting.entity.referentials;

import fr.customer.accounting.entity.AbstractAuditingEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Company extends AbstractAuditingEntity {

    @Id
    private String companyErpCode;

    private String siren;

    private String companyName;

    private String companyAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<PaymentMethod> paymentMethods;

    public String getCompanyErpCode() {
        return companyErpCode;
    }

    public void setCompanyErpCode(String companyErpCode) {
        this.companyErpCode = companyErpCode;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
