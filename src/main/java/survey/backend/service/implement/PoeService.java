package survey.backend.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.repository.PoeRepository;
import java.util.Optional;

@Service
public class PoeService {

    @Autowired
    PoeRepository poeRepository;

    public Iterable<Poe> findAll() {
        return this.poeRepository.findAll();
    }

    public Optional<Poe> findById(Long id) {
        return this.poeRepository.findById(id);
    }

    // Fonction à développer plus tard si nécessaire
    public Iterable<Poe> search() {
        return null;
    }

    public Poe add(PoeDto poeDto) {
        return this.poeRepository.save(poeDto.toPoe());
    }

    public Optional<Poe> update(PoeDto poeDto) {
        Poe poe = poeDto.toPoe();
        Optional<Poe> optPoe = this.poeRepository.findById(poe.getId());
        if(optPoe.isPresent()) {
            this.poeRepository.save(poe);
            return Optional.of(poe);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Poe> optPoe = this.poeRepository.findById(id);
        if(optPoe.isPresent()) {
            this.poeRepository.delete(optPoe.get());
            return true;
        }
        return false;
    }
}
