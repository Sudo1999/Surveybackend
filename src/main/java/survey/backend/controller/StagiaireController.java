package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.StagiaireDto;
import survey.backend.entities.Stagiaire;
import survey.backend.error.BadRequestError;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.StagiaireService;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
// Annotation @RestController => chaque méthode va renvoyer directement la réponse Json à l'utilisateur.
@RequestMapping("api/stagiaire")     // Routage (un contrôleur est un point d'entrée)
// Des annotations telles que @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, qui ornent les méthodes correspondantes,
// permettent de ne spécifier que l’URL tout en utilisant le verbe HTTP lié, présent juste avant le mapping.
public class StagiaireController {

    @Autowired  // DI (Dependency Injection)
    // Il liste les cibles autorisées et fait du câblage automatique de composants. Une seule implémentation concrète est possible.
    private StagiaireService stagiaireService;

    /**
     * List of stagiaires
     * route: /api/stagiaire
     *
     * @return list of stagiaires
     */
    @GetMapping
    public Iterable<Stagiaire> findAll() {
        return stagiaireService.findAll();
    }

    /**
     * A stagiaire by its id
     * route: /api/stagiaire/{id}
     *
     * @param id
     * @return a stagiaire
     */
    @GetMapping("{id}")
    public Stagiaire findById(@PathVariable("id") int id) {
        Optional<Stagiaire> optStagiaire = stagiaireService.findById(id);
        if (optStagiaire.isPresent()) {
            return optStagiaire.get();
        } else {
            throw NoDataFoundError.withId("Stagiaire", id);
        }
    }

    /**
     * Search stagiaires with criteria
     * route: /api/stagiaire/search?fn=some_firstname&ln=some_lastname
     *
     * @param firstName (optional)
     * @param lastName  (optional)
     * @return stagiaires corresponding
     */
    @GetMapping("search")
    public Iterable<Stagiaire> search(
            @RequestParam(name = "ln", required = false) String lastName,
            @RequestParam(name = "fn", required = false) String firstName
    ) {
        int size = 0;
        if (lastName == null && firstName == null) {
            throw BadRequestError.withNoArg("Search with no args is a bad request !!!");  // 400 Bad Request
        }
        Iterable<Stagiaire> stagiaireCollection = stagiaireService.search(lastName, firstName);
        if (stagiaireCollection instanceof Collection) {
            size = ((Collection<Stagiaire>) stagiaireCollection).size();
        }
        if (size == 0) {
            throw NoDataFoundError.noResults("Stagiaire search", lastName + " " + firstName);
        }
        return stagiaireCollection;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stagiaire add(@Valid @RequestBody StagiaireDto stagiaireDto) {
        return stagiaireService.add(stagiaireDto);
    }

    @PutMapping
    public Stagiaire update(@Valid @RequestBody StagiaireDto stagiaireDto) {
        return stagiaireService.update(stagiaireDto)
            .orElseThrow(
                    () -> NoDataFoundError.withId("Stagiaire",
                            Math.toIntExact(stagiaireDto.getId()))
            );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (!stagiaireService.delete(id)) {
            throw NoDataFoundError.withId("Stagiaire", id);
        }
    }
}
