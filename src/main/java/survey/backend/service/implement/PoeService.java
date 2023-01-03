package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.Utils.StreamUtils;
import survey.backend.dto.PoeDto;
import survey.backend.entities.Poe;
import survey.backend.repository.PoeRepository;
import java.util.Optional;

@Service
public class PoeService {

    @Autowired
    PoeRepository poeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Iterable<PoeDto> findAll() {
        //return this.poeRepository.findAll();
        return StreamUtils.toStream(this.poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeDto.class))
                .toList();
    }

    public Optional<PoeDto> findById(Long id) {
        //return this.poeRepository.findById(id);
        return this.poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeDto.class));
    }

    // Fonction à développer plus tard si nécessaire
    public Iterable<PoeDto> search() {
        return null;
    }

    public PoeDto add(PoeDto poeDto) {
        //return this.poeRepository.save(poeDto.toPoe());
        Poe poeEntity = modelMapper.map(poeDto, Poe.class);
        this.poeRepository.save(poeEntity);
        return modelMapper.map(poeEntity, PoeDto.class);
    }

    public Optional<PoeDto> update(PoeDto poeDto) {
//        Optional<PoeDto> optPoe = this.poeRepository.findById(poe.getId());
//        if(optPoe.isPresent()) {
//            this.poeRepository.save(poe);
//            return Optional.of(poe);
//        }
//        return Optional.empty();

        return this.poeRepository.findById(poeDto.getId())
                .map(poeEntity -> {
                    // Update entity object from db with dto fields
                    modelMapper.map(poeDto, poeEntity);
                    // Synchronize with database
                    poeRepository.save(poeEntity);
                    // Transform entity updated in dto
                    return modelMapper.map(poeEntity, PoeDto.class);
                });
    }

    public boolean delete(Long id) {
//        Optional<PoeDto> optPoe = this.poeRepository.findById(id);
//        if(optPoe.isPresent()) {
//            this.poeRepository.delete(optPoe.get());
//            return true;
//        }
//        return false;

        Optional<Poe> optPoe = this.poeRepository.findById(id);
        if(optPoe.isPresent()) {
            this.poeRepository.delete(optPoe.get());
            return true;
        }
        return false;
    }
}
