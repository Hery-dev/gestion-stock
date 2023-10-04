package fr.customer.accounting.controller;

import com.accounting.customers.fr.api.fournisseur.FournisseurApi;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetailsResources;
import com.accounting.customers.fr.rest.model.fournisseur.FournisseurResources;
import fr.customer.accounting.service.FournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class FournisseurController implements FournisseurApi {
    private final String FOURNISSEUR_SAVED_MESSAGE = "Fournisseur was saved successfully";
    private final String FOURNISSEUR_UPDATED_MESSAGE = "Fournisseur was updated successfully";
    private final String FOURNISSEUR_DETAILS_CODE = "FOURNISSEUR_DETAILS";
    private final String FOURNISSEUR_NOT_SAVED = "Fournisseur not saved";
    private final String FOURNISSEUR_EMPTY = "Fournisseur empty";
    private final String FOURNISSEUR_DETAILS = "Fournisseur details";
    private final String NOT_FOUND_MESSAGE = "Fournisseur not found";
    private final String NOT_FOUND_CODE = "FOURNISSEUR_NOT_FOUND";
    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> createFournisseur(FournisseurDetails fournisseurDetails) {
        if(Objects.isNull(fournisseurDetails)){
            return new ResponseEntity<>(fournisseurDetailsResourcesResponse(FOURNISSEUR_EMPTY, FOURNISSEUR_DETAILS_CODE, null), HttpStatus.BAD_REQUEST);
        }
        return fournisseurService.createFournisseur(fournisseurDetails)
                .map(response -> ResponseEntity.ok().body(fournisseurDetailsResourcesResponse(FOURNISSEUR_SAVED_MESSAGE, FOURNISSEUR_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(fournisseurDetailsResourcesResponse(FOURNISSEUR_NOT_SAVED, FOURNISSEUR_DETAILS_CODE, null), HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<FournisseurResources> findAll() {
        return ResponseEntity.ok().body(fournisseurResourcesResponse(FOURNISSEUR_DETAILS, FOURNISSEUR_DETAILS_CODE, fournisseurService.findAll()));
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> getFournisseurByFournisseurCode(String fournisseurCode) {
        return fournisseurService.findFournisseurByFournisseurCode(fournisseurCode)
                .map(response -> ResponseEntity.ok().body(fournisseurDetailsResourcesResponse(FOURNISSEUR_DETAILS, FOURNISSEUR_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(fournisseurDetailsResourcesResponse(NOT_FOUND_MESSAGE, NOT_FOUND_CODE, null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<FournisseurDetailsResources> updateFournisseurByFournisseurCode(String fournisseurCode, FournisseurDetails fournisseurDetails) {
        return fournisseurService.updateFournisseurByFournisseurCode(fournisseurCode, fournisseurDetails)
                .map(response -> ResponseEntity.ok().body(fournisseurDetailsResourcesResponse(FOURNISSEUR_UPDATED_MESSAGE, FOURNISSEUR_DETAILS_CODE, response)))
                .orElse(new ResponseEntity<>(fournisseurDetailsResourcesResponse(NOT_FOUND_MESSAGE, NOT_FOUND_CODE, null), HttpStatus.NOT_FOUND));
    }

    private FournisseurDetailsResources fournisseurDetailsResourcesResponse(String message, String code, FournisseurDetails fournisseurDetails){
        FournisseurDetailsResources fournisseurDetailsResources = new FournisseurDetailsResources();
        fournisseurDetailsResources.setMessage(message);
        fournisseurDetailsResources.setCode(code);
        fournisseurDetailsResources.setFournisseurDetails(fournisseurDetails);
        return fournisseurDetailsResources;
    }

    private FournisseurResources fournisseurResourcesResponse(String message, String code, List<FournisseurDetails> fournisseurDetails){
        FournisseurResources fournisseurResources = new FournisseurResources();
        fournisseurResources.setMessage(message);
        fournisseurResources.setCode(code);
        fournisseurResources.setFournisseurDetails(fournisseurDetails);
        return fournisseurResources;
    }
}
