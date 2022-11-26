package survey.backend.service;

import org.springframework.stereotype.Service;
import survey.backend.dto.StagiaireDto;
import java.util.Optional;
import java.util.Set;

public interface StagiaireService {

    Set<StagiaireDto> findAll();
    // Pour l'exercice du FakeStagiaireService, on récupère les annotations fournies dans git

    /**
     * find stagiaire with its id
     * @param id
     * @return optional with stagiaire if found else optional empty
     */
    Optional<StagiaireDto> findById(int id);

    /**
     * search stagiaires with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return stagiaire set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no stagiaire found with these criteria or both criteria are null
     */
    Set<StagiaireDto> search(String lastName, String firstName);

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
