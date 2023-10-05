package util;

import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import fr.customer.accounting.entity.Fournisseur;
import fr.customer.accounting.service.mapper.FournisseurMapper;

public final class TestBuilder {
    public static FournisseurDetails buildFournisseurDetails(){
        FournisseurDetails fournisseurDetails = new FournisseurDetails();
        fournisseurDetails.setFournisseurCode("FR_002");
        fournisseurDetails.fournisseurName("Operateur");
        fournisseurDetails.setFournisseurContact("+33 5 78 478 41");
        fournisseurDetails.setFournisseurAddress("Paris France");
        return fournisseurDetails;
    }

    public static FournisseurDetails buildFournisseurDetailsUpdated(){
        FournisseurDetails fournisseurDetails = new FournisseurDetails();
        fournisseurDetails.setFournisseurCode("FR_002");
        fournisseurDetails.fournisseurName("Operateur modifier");
        fournisseurDetails.setFournisseurContact("+33 5 78 478 41");
        fournisseurDetails.setFournisseurAddress("Paris France modifier");
        return fournisseurDetails;
    }

    public static Fournisseur mapToFournisseur(FournisseurDetails fournisseurDetails){
        Fournisseur fournisseur = FournisseurMapper.mapToModel(fournisseurDetails);
        fournisseur.setCreatedBy("test");
        return fournisseur;
    }

    private TestBuilder() {
    }
}
