package survey.backend.service;

import survey.backend.dto.StagiaireDto;
import survey.backend.entities.Stagiaire;
import java.util.Optional;

public interface StagiaireService {

    /**
     * find all the stagiaires
     * @return list of all the stagiaires
     */
    Iterable<Stagiaire> findAll();

    /**
     * find stagiaire with its id
     * @param id
     * @return optional with stagiaire if found else optional empty
     */
    Optional<Stagiaire> findById(int id);

    /**
     * search stagiaires with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return stagiaires set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no stagiaire found with these criteria or both criteria are null
     */
    Iterable<Stagiaire> search(String lastName, String firstName);

    /**
     * add new stagiaire
     * @param stagiaireDto
     * @return stagiaire completed (id, default values)
     */
    Stagiaire add(StagiaireDto stagiaireDto);

    /**
     * update stagiaire
     * @param stagiaireDto
     * @return stagiaire updated if found, else optional empty
     */
    Optional<Stagiaire> update(StagiaireDto stagiaireDto);

    /**
     * delete stagiaire with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
     boolean delete(int id);
}