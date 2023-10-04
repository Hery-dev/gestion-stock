package fr.customer.accounting.service;

import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import fr.customer.accounting.entity.Fournisseur;
import fr.customer.accounting.repository.FournisseurRepository;
import fr.customer.accounting.service.mapper.FournisseurMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    public Optional<FournisseurDetails> createFournisseur(FournisseurDetails fournisseurDetails){
        if(fournisseurRepository.findFournisseurByFournisseurCode(fournisseurDetails.getFournisseurCode()).isPresent()){
            return Optional.empty();
        }
        Fournisseur fournisseur = FournisseurMapper.mapToModel(fournisseurDetails);
        fournisseur.setCreatedBy("dev");
        fournisseur.setCreatedDate(Instant.now());
        fournisseurRepository.save(fournisseur);
        return FournisseurMapper.mapToDomain(fournisseur);
    }

    public Optional<FournisseurDetails> findFournisseurByFournisseurCode(String fournisseurCode){
        return fournisseurRepository.findFournisseurByFournisseurCode(fournisseurCode)
                .flatMap(FournisseurMapper::mapToDomain);
    }

    public List<FournisseurDetails> findAll(){
        return fournisseurRepository.findAll().stream()
                .map(FournisseurMapper::mapToDomain)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<FournisseurDetails> updateFournisseurByFournisseurCode(String fournisseurCode, FournisseurDetails fournisseurDetails){
        return fournisseurRepository.findFournisseurByFournisseurCode(fournisseurCode)
                .map(fournisseur -> updateFournisseur(fournisseur, fournisseurDetails))
                .flatMap(FournisseurMapper::mapToDomain);
    }

    private Fournisseur updateFournisseur(Fournisseur old, FournisseurDetails fournisseurDetails){
        updater(old::setFournisseurName, fournisseurDetails.getFournisseurName());
        updater(old::setFournisseurContact, fournisseurDetails.getFournisseurContact());
        updater(old::setFournisseurAddress, fournisseurDetails.getFournisseurAddress());
        old.setLastModifiedBy("dev");
        old.setLastModifiedDate(Instant.now());
        return old;
    }
    private void updater(Consumer<String> updating, String value){
        if(StringUtils.isNotBlank(value)){
            updating.accept(value);
        }
    }
}
