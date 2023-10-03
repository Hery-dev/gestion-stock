package fr.customer.accounting.controller;

import com.accounting.customers.fr.api.fournisseur.FournisseurApi;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetailsResources;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurResources;
import fr.customer.accounting.service.FournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FournisseurController implements FournisseurApi {
    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> createFournisseur(FournisseurDetails fournisseurDetails) {
        return null;
    }

    @Override
    public ResponseEntity<FournisseurResources> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> getFournisseurByFournisseurCode(String fournisseurCode) {
        return null;
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> updateFournisseurByFournisseurCode(String fournisseurCode, FournisseurDetails fournisseurDetails) {
        return null;
    }
}
