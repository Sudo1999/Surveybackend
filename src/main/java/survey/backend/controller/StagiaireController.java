package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.StagiaireDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.StagiaireService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController     // Retention Runtime => Elle va être conservée pendant toute la durée du projet
@RequestMapping("api/stagiaire")     // S'occupe du routage (un contrôleur est un point d'entrée)
public class StagiaireController {

    @Autowired  // DI (Dependency Injection)
    // Il liste les cibles autorisées. Une seule implémentation concrète est possible. Il fait du cablage automatique de composants.
    private StagiaireService stagiaireService;

    /*
    @GetMapping
    public String list() {
        return "Liste de stagiaires";
    }*/

    /**
     * List of stagiaires
     * route: /api/stagiaire
     * @return list of stagiaires
     */
    @GetMapping
    public Set<StagiaireDto> list() {       // On pourrait aussi le faire avec List au lieu de Set
        return stagiaireService.findAll();
//        return Set.of(
//            StagiaireDto.builder()
//                .id(8)
//                .lastName("Brown")
//                .firstName("Mary")
//                .build(),
//            StagiaireDto.builder()
//                .id(9)
//                .lastName("Doe")
//                .firstName("Jane")
//                .build(),
//            StagiaireDto.builder()
//                .id(12)
//                .lastName("Doe")
//                .firstName("John")
//                .build(),
//            StagiaireDto.builder()
//                .id(14)
//                .lastName("Smith")
//                .firstName("William")
//                .build());
    }

    /*
    @GetMapping("{id}")
    public String one(@PathVariable("id") int id) {
        return "One stagiaire: " + id;
    }*/

    /**
     * A stagiaire by its id
     * route: /api/stagiaire/{id}
     * @param id
     * @return a stagiaire
     */
    @GetMapping("{id}")
    public StagiaireDto getById(@PathVariable("id") int id) {
        Optional<StagiaireDto> optStagiaireDto = stagiaireService.findById(id);
        if (optStagiaireDto.isPresent()) {
            return optStagiaireDto.get();
        } else {
            //throw new IllegalArgumentException(
                //"Stagiaire with id " + id + " not found");
            throw NoDataFoundError.withId("Stagiaire", id);
        }
//        //return Optional.empty();
//        return StagiaireDto.builder()
//                .id(id)
//                .lastName("Doe")
//                .firstName("John")
//                //.birthDate(LocalDate.of(1900, 7, 1)
//                .build();
    }

    /*
    @GetMapping("search")
    public String search(
        @RequestParam(name="fn", required = false) String firstname,    // On précise le nom des paramètres quand ils sont plusieurs
        @RequestParam(name="ln", required = false) String lastname      // required = false signifie que le paramètre est optionnel
    ) {
        return "Search result: fn = " + firstname + " ; ln = " + lastname;
    }*/

    /**
     * Search stagiaires with criteria
     * route: /api/stagiaire/search?fn=some_firstname&ln=some_lastname
     * @param firstname (optional)
     * @param lastname  (optional)
     * @return stagiaires corresponding
     */
    @GetMapping("search")
    public Set<StagiaireDto> search(
            @RequestParam(name = "fn", required = false) String firstname,
            @RequestParam(name = "ln", required = false) String lastname
    ) {
        return Set.of(
            StagiaireDto.builder()
                .id(8)
                .lastName(Objects.isNull(lastname) ? "Found" : lastname)
                .firstName(Objects.isNull(firstname) ? "Mary" : firstname)
                .build(),
            StagiaireDto.builder()
                .id(9)
                .lastName("Found")
                .firstName("Jane")
                .build(),
            StagiaireDto.builder()
                .id(12)
                .lastName("Found")
                .firstName("Jim")
                .build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StagiaireDto add(@Valid @RequestBody StagiaireDto stagiaireDto) {
        // TODO: add in under layer
        // TODO stagiaireDto must be valid
        return stagiaireService.add(stagiaireDto);
//        stagiaireDto.setId(54321);
//        return stagiaireDto;
    }

    @PutMapping
    public StagiaireDto update(@RequestBody StagiaireDto stagiaireDto) {
        // TODO: update this object if exists and return it
        // TODO stagiaireDto must be valid
        return stagiaireDto;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        // TODO: delete this object if exists
    }
}