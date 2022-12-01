package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.backend.entities.Poe;
import survey.backend.service.implement.PoeService;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    private PoeService service;

    @GetMapping
    public Iterable<Poe> findAll() {
        return this.service.findAll();
    }

    /*
    @GetMapping("{id}")
    public Optional<PoeDto> getPoe(@PathVariable("id") int id) {
    return Optional.of(PoeDto.builder()
            .id(6)
            .title("Consultant BI")
            .beginDate(LocalDate.of(2022, 9, 24))
            .endDate(LocalDate.of(2022, 11, 23))
            .poeType(PoeTypeDto.POEI)
            .build());
    }*/
}