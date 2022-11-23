package survey.backend.service;

import survey.backend.dto.StagiaireDto;

import java.util.Optional;
import java.util.Set;

public interface StagiaireService {

    Set<StagiaireDto> findAll();

    Optional<StagiaireDto> findById(int id);

    Set<StagiaireDto> search(String lastName, String firstName);

    StagiaireDto add(StagiaireDto stagiaireDto);

    StagiaireDto update(StagiaireDto stagiaireDto);

    void delete(int id);
}
