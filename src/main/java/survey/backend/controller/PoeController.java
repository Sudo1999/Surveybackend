package survey.backend.controller;

import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeTypeDto;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @GetMapping
    public Set<PoeDto> getList() {
        return Set.of(
            PoeDto.builder()
                    .id(1)
                .title("Java Fullstack")
                .beginDate(LocalDate.of(2022, 10, 24))
                .endDate(LocalDate.of(2023, 1, 27))
                .poeType(PoeTypeDto.POEI)
                .build(),
            PoeDto.builder()
                    .id(2)
                .title("Java Fullstack")
                .beginDate(LocalDate.of(2022, 11, 2))
                .endDate(LocalDate.of(2023, 2, 3))
                .poeType(PoeTypeDto.POEC)
                .build(),
            PoeDto.builder()
                    .id(3)
                .title("Consultant Devops")
                .beginDate(LocalDate.of(2022, 6, 13))
                .endDate(LocalDate.of(2022, 9, 16))
                .poeType(PoeTypeDto.POEC)
                .build(),
            PoeDto.builder()
                    .id(4)
                .title("Consultant Cyber Sécurité")
                .beginDate(LocalDate.of(2021, 9, 13))
                .endDate(LocalDate.of(2021, 11, 16))
                .poeType(PoeTypeDto.POEI)
                .build(),
            PoeDto.builder()
                    .id(5)
                .title("Consultant SAP")
                .beginDate(LocalDate.of(2022, 4, 13))
                .endDate(LocalDate.of(2022, 8, 16))
                .poeType(PoeTypeDto.POEI)
                .build(),
            PoeDto.builder()
                    .id(6)
                .title("Consultant BI")
                .beginDate(LocalDate.of(2022, 9, 24))
                .endDate(LocalDate.of(2022, 11, 23))
                .poeType(PoeTypeDto.POEI)
                .build()
        );
    }

    @GetMapping("{id}")
    public Optional<PoeDto> getPoe(@PathVariable("id") int id) {
    return Optional.of(PoeDto.builder()
            .id(6)
            .title("Consultant BI")
            .beginDate(LocalDate.of(2022, 9, 24))
            .endDate(LocalDate.of(2022, 11, 23))
            .poeType(PoeTypeDto.POEI)
            .build());
    }
}