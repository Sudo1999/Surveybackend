package survey.backend.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.StagiaireDto;
import survey.backend.entities.Stagiaire;
import survey.backend.repository.StagiaireRepository;
import java.util.Optional;

@Service
public class StagiaireService implements survey.backend.service.StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Override
    public Iterable<Stagiaire> findAll() {
        return this.stagiaireRepository.findAll();
    }

    @Override
    public Optional<Stagiaire> findById(int id) {
        return this.stagiaireRepository.findById((long) id);
    }

    @Override
    public Iterable<Stagiaire> search(String lastName, String firstName) {
        // TODO implement method in the repository
        return null;
    }

    @Override
    public Stagiaire add(StagiaireDto stagiaireDto) {
        return this.stagiaireRepository.save(stagiaireDto.toStagiaire());   // save() fait la persistence de la base de données
    }

    @Override
    public Optional<Stagiaire> update(StagiaireDto stagiaireDto) {
        Stagiaire stagiaire = stagiaireDto.toStagiaire();
        Optional<Stagiaire> optStagiaire = this.stagiaireRepository.findById(stagiaire.getId());
        if(optStagiaire.isPresent()) {
            this.stagiaireRepository.save(stagiaire);
            return Optional.of(stagiaire);  // Optional pour le cas où le stagiaire aurait disparu de la base entre temps
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        Optional<Stagiaire> optStagiaire = this.stagiaireRepository.findById((long)id);
        if(optStagiaire.isPresent()) {
            this.stagiaireRepository.delete(optStagiaire.get());
            return true;
        }
        return false;
    }
}
