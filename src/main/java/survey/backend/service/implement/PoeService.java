package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.Utils.StreamUtils;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.entities.Poe;
import survey.backend.entities.Stagiaire;
import survey.backend.repository.PoeRepository;
import survey.backend.repository.StagiaireRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class PoeService implements survey.backend.service.PoeService {

    @Autowired
    private PoeRepository poeRepository;
    @Autowired
    private StagiaireRepository stagiaireRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Collection<PoeDto> findAll() {
        return StreamUtils.toStream(this.poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeDto.class))
                .toList();
    }

    // Fonction à développer plus tard si nécessaire :
    public Collection<PoeDto> search() {
        return null;
    }

    public PoeDto add(PoeDto poeDto) {
        Poe poeEntity = modelMapper.map(poeDto, Poe.class);
        this.poeRepository.save(poeEntity);
        return modelMapper.map(poeEntity, PoeDto.class);
    }

    public Optional<PoeDto> update(PoeDto poeDto) {
        return this.poeRepository.findById(poeDto.getId())
                .map(poeEntity -> {
                    // Update entity object from db with dto fields
                    modelMapper.map(poeDto, poeEntity);     // C'est bien poeEntity et non Poe.class
                    // Synchronize with database
                    poeRepository.save(poeEntity);
                    // Transform entity updated in dto
                    return modelMapper.map(poeEntity, PoeDto.class);
                });
    }

    public boolean delete(long id) {
        Optional<Poe> optPoe = this.poeRepository.findById(id);
        if (optPoe.isPresent()) {
            this.poeRepository.delete(optPoe.get());
            return true;
        }
        return false;
    }

    // Fonctions prenant en compte la liste de stagiaires de la Poe :

    @Override
    public Optional<PoeFullDto> findById(long id) {
        return this.poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeFullDto.class));
    }

    @Override
    public Optional<PoeFullDto> addStagiaire(long poeId, long stagiaireId) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> stagiaireRepository.findById(stagiaireId)
                        .map(stagiaireEntity -> {
                            // Add stagiaire to poe
                            poeEntity.getStagiaires().add(stagiaireEntity);
                            // Synchronize with database
                            poeRepository.save(poeEntity);
                            // Return updated poe as PoeDto
                            return modelMapper.map(poeEntity, PoeFullDto.class);
                        })
                );
    }

    @Override
    public Optional<PoeFullDto> addStagiaires(long poeId, Collection<Long> stagiairesIds) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    var stagiaireEntities = StreamUtils.toStream(stagiaireRepository.findAllById(stagiairesIds))
                            .toList();
                    if (stagiairesIds.size() != stagiaireEntities.size()) {
                        return Optional.empty();
                    }
                    // Add stagiaires to poe
                    poeEntity.getStagiaires().addAll(stagiaireEntities);
                    // Synchronize with database
                    poeRepository.save(poeEntity);
                    // Return updated poe as PoeDto
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public Optional<PoeFullDto> removeStagiaire(long poeId, long stagiaireId) {
        var optPoe = this.poeRepository.findById(poeId);
        var optStagiaire = this.stagiaireRepository.findById(stagiaireId);

        if (optPoe.isPresent() && optStagiaire.isPresent()) {
            var poeEntity = optPoe.get();
            var stagiaireEntity = optStagiaire.get();

            Set<Stagiaire> updatedStagiaires = poeEntity.getStagiaires();
            updatedStagiaires.remove(stagiaireEntity);
            poeEntity.setStagiaires(updatedStagiaires);
            this.poeRepository.save(poeEntity);
            return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PoeFullDto> clearStagiaires(long poeId) {
        // TODO
        return Optional.empty();
    }
}
