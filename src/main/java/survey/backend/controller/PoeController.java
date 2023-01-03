package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.implement.PoeService;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    private PoeService poeService;

    @GetMapping
    public Iterable<PoeDto> findAll() {
        return poeService.findAll();
    }

    @GetMapping("{id}")
    public PoeDto findById(@PathVariable("id") Long id) {
        Optional<PoeDto> optPoe = poeService.findById(id);
        if (optPoe.isPresent()) {
            return optPoe.get();
        } else {
            throw NoDataFoundError.withId("PoeDto", id);
        }
    }

    // Fonction à développer plus tard si nécessaire
    @GetMapping("search")
    public Iterable<PoeDto> search() {
        return poeService.search();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto add(@Valid @RequestBody PoeDto poeDto) {
        return poeService.add(poeDto);
    }

    @PutMapping
    public PoeDto update(@Valid @RequestBody PoeDto poeDto) {
        return poeService.update(poeDto)
            .orElseThrow(
                    () -> NoDataFoundError.withId("PoeDto", poeDto.getId())
            );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!poeService.delete(id)) {
            throw NoDataFoundError.withId("PoeDto", id);
        }
    }
}