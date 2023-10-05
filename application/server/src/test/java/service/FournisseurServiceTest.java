package service;

import com.accounting.customers.fr.rest.model.fournisseur.FournisseurDetails;
import fr.customer.accounting.Application;
import fr.customer.accounting.repository.FournisseurRepository;
import fr.customer.accounting.service.FournisseurService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.TestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Stream;

@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test", classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
class FournisseurServiceTest {
    @Autowired
    private FournisseurRepository fournisseurRepository;

    private Stream<FournisseurDetails> fournisseurDetailsStream(){
        return Stream.of(TestBuilder.buildFournisseurDetails());
    }

    private FournisseurService fournisseurService;

    @BeforeEach
    void setUp(){
        fournisseurService = new FournisseurService(fournisseurRepository);
    }

    @AfterEach
    void cleanUp(){
        fournisseurRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("fournisseurDetailsStream")
    void createFournisseur(FournisseurDetails fournisseurDetails){
        Optional<FournisseurDetails> response = fournisseurService.createFournisseur(fournisseurDetails);

        Assertions.assertThat(response).isPresent();
        assertEquals(response.get(), fournisseurDetails);
    }

    @ParameterizedTest
    @MethodSource("fournisseurDetailsStream")
    void createFournisseurWhenEntityAlreadyExist(FournisseurDetails fournisseurDetails){
        fournisseurRepository.save(TestBuilder.mapToFournisseur(fournisseurDetails));
        Optional<FournisseurDetails> response = fournisseurService.createFournisseur(fournisseurDetails);

        Assertions.assertThat(response).isNotPresent();
    }

    @ParameterizedTest
    @MethodSource("fournisseurDetailsStream")
    void getFournisseurByFournisseurCode(FournisseurDetails fournisseurDetails){
        fournisseurRepository.save(TestBuilder.mapToFournisseur(fournisseurDetails));
        Optional<FournisseurDetails> response = fournisseurService.findFournisseurByFournisseurCode(fournisseurDetails.getFournisseurCode());

        Assertions.assertThat(response).isPresent();
        assertEquals(response.get(), fournisseurDetails);
    }

    @Test
    void getFournisseurByFournisseurCodeWhenEntityNotFound(){
        Optional<FournisseurDetails> response = fournisseurService.findFournisseurByFournisseurCode("test");

        Assertions.assertThat(response).isNotPresent();
    }

    @ParameterizedTest
    @MethodSource("fournisseurDetailsStream")
    void updateFournisseurDetailsByFournisseurDetailsCode(FournisseurDetails fournisseurDetails){
        fournisseurRepository.save(TestBuilder.mapToFournisseur(fournisseurDetails));
        Optional<FournisseurDetails> response = fournisseurService.updateFournisseurByFournisseurCode(fournisseurDetails.getFournisseurCode(), TestBuilder.buildFournisseurDetailsUpdated());

        Assertions.assertThat(response).isPresent();
        FournisseurDetails fournisseurDetailsToTest = response.get();
        // unmodified column
        assertEquals(fournisseurDetails.getFournisseurCode(), fournisseurDetailsToTest.getFournisseurCode());
        // modified column
        assertEquals(fournisseurDetailsToTest.getFournisseurName(), TestBuilder.buildFournisseurDetailsUpdated().getFournisseurName());
        assertEquals(fournisseurDetailsToTest.getFournisseurAddress(), TestBuilder.buildFournisseurDetailsUpdated().getFournisseurAddress());
        assertEquals(fournisseurDetailsToTest.getFournisseurContact(), TestBuilder.buildFournisseurDetailsUpdated().getFournisseurContact());
    }

    @Test
    void updateFournisseurByFournisseurCodeWhenEntityNotFound(){
        Optional<FournisseurDetails> response = fournisseurService.updateFournisseurByFournisseurCode("test", TestBuilder.buildFournisseurDetailsUpdated());
        Assertions.assertThat(response).isNotPresent();
    }

}
