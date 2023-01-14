package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.service.PoeService;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.PoeDto;
//import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    private PoeService poeService;

    @GetMapping
    public Collection<PoeDto> findAll() {
        return poeService.findAll();
    }

    // Fonction désactivée pour la remplacer par une fonction appelant la classe PoeFullDto (voir plus bas) :
//    @GetMapping("{id}")
//    public PoeDto findById(@PathVariable("id") long id) {
//        Optional<PoeDto> optPoeDto = poeService.findById(id);
//        if (optPoeDto.isPresent()) {
//            return optPoeDto.get();
//        } else {
//            throw NoDataFoundError.withId("PoeDto", id);
//        }
//    }

    // Fonction à développer plus tard si nécessaire :
    @GetMapping("search")
    public Collection<PoeDto> search() {
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
                    () -> NoDataFoundError.withId("Poe", poeDto.getId())
            );
    }

    @DeleteMapping("{poeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("poeId") long poeId) {
        if (!poeService.delete(poeId)) {
            throw NoDataFoundError.withId("Poe", poeId);
        }
    }

    // Fonctions prenant en compte la liste de stagiaires de la Poe :

    @GetMapping("{poeId}")
    public PoeFullDto findById(@PathVariable("poeId") long poeId) {
//        Optional<PoeFullDto> optPoeFullDto = poeService.findById(poeId);
//        if (optPoeFullDto.isPresent()) {
//            return optPoeFullDto.get();
//        } else {
//            throw NoDataFoundError.withId("Poe", poeId);
//        }
        // Autre façon d'écrire la fonction :
        return poeService.findById(poeId)
            .orElseThrow(() -> {
                    throw NoDataFoundError.withId("Poe", poeId);
                });
    }

    @PatchMapping("{poeId}/addStagiaire/{stagiaireId}")
    public PoeFullDto addStagiaire(
            @PathVariable("poeId") long poeId,
            @PathVariable("stagiaireId") long stagiaireId
    ) {
        return poeService.addStagiaire(poeId, stagiaireId)
            .orElseThrow(() -> {
                throw NoDataFoundError.withIds("Either Poe or stagiaire", poeId, stagiaireId);
            });
    }

    @PatchMapping("{poeId}/addStagiaires")
    public PoeFullDto addStagiaires(
            @PathVariable("poeId") long poeId,
            @RequestBody List<Long> stagiairesIds
    ){
        return poeService.addStagiaires(poeId, stagiairesIds)
            .orElseThrow(() -> {
                throw NoDataFoundError.withId("Either Poe or stagiaires in Poe", poeId);
            });
    }

    @DeleteMapping("{poeId}/removeStagiaire/{stagiaireId}")
    public PoeFullDto removeStagiaire(
//            @RequestParam(value = "p", required = true) long poeId,
//            @RequestParam(value = "s", required = true) long stagiaireId
            @PathVariable("poeId") long poeId,
            @PathVariable("stagiaireId") long stagiaireId
    ) {
        var optPoeFullDto = this.poeService.removeStagiaire(poeId, stagiaireId);
        if(optPoeFullDto.isEmpty()){
            throw NoDataFoundError.withValues("Either Poe or stagiaire",
                    "Poe Id = " + poeId + " and stagiaire Id = " + stagiaireId );
        }
        return optPoeFullDto.get();
    }

    @DeleteMapping("{poeId}/removeAllStagiaires")
    public PoeFullDto removeAllStagiaires(@PathVariable("poeId") long poeId) {
        var optPoeFullDto = this.poeService.removeAllStagiaires(poeId);
        if(optPoeFullDto.isEmpty()){
            throw NoDataFoundError.withId("Either Poe or stagiaires in Poe", poeId);
        }
        return optPoeFullDto.get();
    }
}
