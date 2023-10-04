package fr.customer.accounting.service.mapper;

import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import fr.customer.accounting.entity.Fournisseur;

import java.util.Objects;
import java.util.Optional;

public final class FournisseurMapper {
    private FournisseurMapper() {
    }

    public static Optional<FournisseurDetails> mapToDomain(Fournisseur fournisseur){
        if(Objects.isNull(fournisseur)){
            return Optional.empty();
        }
        FournisseurDetails fournisseurDetails = new FournisseurDetails();
        fournisseurDetails.setFournisseurCode(fournisseur.getFournisseurCode());
        fournisseurDetails.setFournisseurName(fournisseur.getFournisseurName());
        fournisseurDetails.setFournisseurContact(fournisseur.getFournisseurContact());
        fournisseurDetails.setFournisseurAddress(fournisseur.getFournisseurAddress());
        return Optional.of(fournisseurDetails);
    }

    public static Fournisseur mapToModel(FournisseurDetails domain){
        if(Objects.isNull(domain)){
            return new Fournisseur();
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setFournisseurCode(domain.getFournisseurCode());
        fournisseur.setFournisseurName(domain.getFournisseurName());
        fournisseur.setFournisseurContact(domain.getFournisseurContact());
        fournisseur.setFournisseurAddress(domain.getFournisseurAddress());
        return fournisseur;
    }
}
