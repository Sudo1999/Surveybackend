package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.Utils.StreamUtils;
import survey.backend.dto.StagiaireDto;
import survey.backend.entities.Stagiaire;
import survey.backend.repository.StagiaireRepository;
import java.util.Optional;

@Service
public class StagiaireService implements survey.backend.service.StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<StagiaireDto> findAll() {
        //return this.stagiaireRepository.findAll();
        return StreamUtils.toStream(this.stagiaireRepository.findAll())
                .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class))
                .toList();
    }

    @Override
    public Optional<StagiaireDto> findById(long id) {
        //return this.stagiaireRepository.findById(id);
        return this.stagiaireRepository.findById(id)
                .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class));
    }

    @Override
    public Iterable<StagiaireDto> search(String lastName, String firstName) {
//        if (lastName != null && firstName != null) {
//            return this.stagiaireRepository.listByLastNameAndFirstName(lastName, firstName);
//        }
//        if (lastName != null) {
//            return this.stagiaireRepository.findByLastName(lastName);
//        }
//        return this.stagiaireRepository.findByFirstName(firstName);

        if (lastName != null && firstName != null) {
            return StreamUtils.toStream(this.stagiaireRepository.listByLastNameAndFirstName(lastName, firstName))
                    .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class))
                    .toList();
        }
        if (lastName != null) {
            return StreamUtils.toStream(this.stagiaireRepository.findByLastName(lastName))
                    .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class))
                    .toList();
        }
        return StreamUtils.toStream(this.stagiaireRepository.findByFirstName(firstName))
                .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class))
                .toList();
        // Si c'était une liste :
//        return this.stagiaireRepository.findByFirstName(firstName)
//                .stream()
//                .map(stagiaireEntity -> modelMapper.map(stagiaireEntity, StagiaireDto.class))
//                .toList();
    }

    @Override
    public StagiaireDto add(StagiaireDto stagiaireDto) {
        //return this.stagiaireRepository.save(stagiaireDto.toStagiaire());   // save() fait la persistence de la base de données
        Stagiaire stagiaireEntity = modelMapper.map(stagiaireDto, Stagiaire.class);
        this.stagiaireRepository.save(stagiaireEntity);
        return modelMapper.map(stagiaireEntity, StagiaireDto.class);
    }

    @Override
    public Optional<StagiaireDto> update(StagiaireDto stagiaireDto) {
//        Stagiaire stagiaire = stagiaireDto.toStagiaire();
//        Optional<Stagiaire> optStagiaire = this.stagiaireRepository.findById(stagiaire.getId());
//        if(optStagiaire.isPresent()) {
//            this.stagiaireRepository.save(stagiaire);
//            return Optional.of(stagiaire);  // Optional pour le cas où le stagiaire aurait disparu de la base entre temps
//        }
//        return Optional.empty();

        return this.stagiaireRepository.findById(stagiaireDto.getId())
            .map(stagiaireEntity -> {
                // Update entity object from db with dto fields
                modelMapper.map(stagiaireDto, stagiaireEntity);     // C'est bien stagiaireEntity et non Stagiaire.class
                // Synchronize with database
                stagiaireRepository.save(stagiaireEntity);
                // Transform entity updated in dto
                return modelMapper.map(stagiaireEntity, StagiaireDto.class);
            });
    }

    @Override
    public boolean delete(long id) {
        Optional<Stagiaire> optStagiaire = this.stagiaireRepository.findById(id);
        if(optStagiaire.isPresent()) {
            this.stagiaireRepository.delete(optStagiaire.get());
            return true;
        }
        return false;
    }
}
