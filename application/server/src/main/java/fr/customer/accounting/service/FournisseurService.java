package fr.customer.accounting.service;

import fr.customer.accounting.repository.FournisseurRepository;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
}
