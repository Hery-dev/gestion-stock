package fr.customer.accounting.repository;

import fr.customer.accounting.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, UUID> {
}
