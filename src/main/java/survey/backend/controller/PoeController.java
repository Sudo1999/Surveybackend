package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import survey.backend.repository.PoeRepository;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    PoeRepository repository;
    //private PoeService poeService;

    @GetMapping
    public Iterable<Poe> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Poe findById(@PathVariable("id") int id) {
        Optional<Poe> optPoe = repository.findById((long)id);
        if (optPoe.isPresent()) {
            return optPoe.get();
        } else {
            throw NoDataFoundError.withId("Poe", id);
        }
    }

    // Fonction à développer plus tard si nécessaire
    @GetMapping("search")
    public Iterable<Poe> search() { return null; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Poe add(@Valid @RequestBody PoeDto poeDto) {
        return repository.save(poeDto.toPoe());     // C'est "save" qui remplace "add" dans l'interface CrudRepository
    }

    @PutMapping
    public Poe update(@Valid @RequestBody PoeDto poeDto) {
        Poe poe = poeDto.toPoe();
        Optional<Poe> optPoe = repository.findById(poe.getId());
        if(optPoe.isPresent()) {
            repository.save(poe);   // C'est aussi "save" qui remplace "update" dans l'interface CrudRepository
            return poe;
        } else {
            throw NoDataFoundError.withId("Poe", Math.toIntExact(poeDto.getId()));
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        Optional<Poe> optPoe = repository.findById((long)id);
        if(optPoe.isPresent()) {
            repository.delete(optPoe.get());
        } else {
            throw NoDataFoundError.withId("Poe", id);
        }
    }
}