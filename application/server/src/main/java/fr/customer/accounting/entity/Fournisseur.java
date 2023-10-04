package fr.customer.accounting.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table
public class Fournisseur extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "fournisseur_id")
    private UUID fournisseurId;
    private String fournisseurCode;
    private String fournisseurName;
    private String fournisseurContact;
    private String fournisseurAddress;

    public UUID getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(UUID fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public String getFournisseurCode() {
        return fournisseurCode;
    }

    public void setFournisseurCode(String fournisseurCode) {
        this.fournisseurCode = fournisseurCode;
    }

    public String getFournisseurName() {
        return fournisseurName;
    }

    public void setFournisseurName(String fournisseurName) {
        this.fournisseurName = fournisseurName;
    }

    public String getFournisseurContact() {
        return fournisseurContact;
    }

    public void setFournisseurContact(String fournisseurContact) {
        this.fournisseurContact = fournisseurContact;
    }

    public String getFournisseurAddress() {
        return fournisseurAddress;
    }

    public void setFournisseurAddress(String fournisseurAddress) {
        this.fournisseurAddress = fournisseurAddress;
    }
}
