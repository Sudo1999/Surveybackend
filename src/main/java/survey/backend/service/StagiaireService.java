package survey.backend.service;

import org.springframework.stereotype.Service;
import survey.backend.dto.StagiaireDto;
import java.util.Optional;
import java.util.Set;

public interface StagiaireService {

    /**
     * find all the stagiaires
     * @return list of all the stagiaires
     */
    Set<StagiaireDto> findAll();    // Forme de l'adresse = http://localhost:8080/api/stagiaire
    // Le routage est déclaré dans la classe StagiaireController => @RequestMapping("api/stagiaire")

    /**
     * find stagiaire with its id
     * @param id
     * @return optional with stagiaire if found else optional empty
     */
    Optional<StagiaireDto> findById(int id);    // Forme de l'adresse = http://localhost:8080/api/stagiaire/1
    // Le routage est déclaré dans la classe StagiaireController => @RequestMapping("api/stagiaire")

    /**
     * search stagiaires with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return stagiaires set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no stagiaire found with these criteria or both criteria are null
     */
    Set<StagiaireDto> search(String lastName, String firstName);    // A ce stade le Contrôleur n'appelle pas le Fake.
    // Attention par ailleurs à la permutation des deux paramètres (même si l'adresse fonctionne dans les deux sens).
    // Forme de l'adresse = http://localhost:8080/api/stagiaire/search?fn=John&&ln=Doe

    /**
     * add new stagiaire
     * @param stagiaireDto
     * @return stagiaire completed (id, default values)
     */
    StagiaireDto add(StagiaireDto stagiaireDto);

    /**
     * update stagiaire
     * @param stagiaireDto
     * @return stagiaire updated if found, else optional empty
     */
    //StagiaireDto update(StagiaireDto stagiaireDto);
    Optional<StagiaireDto> update(StagiaireDto stagiaireDto);

    /**
     * delete stagiaire with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    //void delete(int id);
     boolean delete(int id);
}
