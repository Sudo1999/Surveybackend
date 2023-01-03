package survey.backend.service;

import survey.backend.dto.StagiaireDto;
//import survey.backend.entities.Stagiaire;
import java.util.Collection;
import java.util.Optional;

public interface StagiaireService {

    /**
     * find all the stagiaires
     * @return list of all the stagiaires
     */
//    Iterable<StagiaireDto> findAll();
    Iterable<StagiaireDto> findAll();
    // Iterable est le moins pratique de tous les types (Collection, List, Set)
    // Le type Collection est général et permet de changer le type du Repository sans rien changer ici
    // (il faut simplement changer en même temps la classe qui implémente l'interface)

    /**
     * find stagiaire with its id
     * @param id
     * @return optional with stagiaire if found else optional empty
     */
    Optional<StagiaireDto> findById(Long id);

    /**
     * search stagiaires with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return stagiaires set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no stagiaire found with these criteria or both criteria are null
     */
    Iterable<StagiaireDto> search(String lastName, String firstName);

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
    Optional<StagiaireDto> update(StagiaireDto stagiaireDto);

    /**
     * delete stagiaire with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
     boolean delete(Long id);
}