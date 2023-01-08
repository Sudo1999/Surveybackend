package survey.backend.service;

import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
//import survey.backend.entities.Poe;
import java.util.Collection;
import java.util.Optional;

public interface PoeService {

    Collection<PoeDto> findAll();

    Collection<PoeDto> search();

    PoeDto add(PoeDto poeDto);

    Optional<PoeDto> update(PoeDto poeDto);

    boolean delete(long id);

    // Fonctions prenant en compte la liste de stagiaires de la Poe :

    Optional<PoeFullDto> findById(long Id);
    Optional<PoeFullDto> addStagiaire(long poeId, long stagiaireId);
    Optional<PoeFullDto> addStagiaires(long poeId, Collection<Long> stagiairesIds);
    Optional<PoeFullDto> removeStagiaire(long poeId, long stagiaireId);
    Optional<PoeFullDto> clearStagiaires(long poeId);
}
