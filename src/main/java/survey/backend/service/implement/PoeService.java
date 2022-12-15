package survey.backend.service.implement;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import survey.backend.dto.PoeDto;
//import survey.backend.entities.Poe;
//import survey.backend.repository.PoeRepository;
//import java.util.Optional;
//
//@Service
//public class PoeService {
//
//    @Autowired
//    PoeRepository repository;
//
//    public Iterable<Poe> findAll() {
//        return this.repository.findAll();
//    }
//
//    public Optional<Poe> findById(int id) {
//        return this.repository.findById((long) id);
//    }
//
//    public Poe add(PoeDto poeDto) {
//        return this.repository.save(poeDto.toPoe());
//    }
//
//    public Optional<Poe> update(PoeDto poeDto) {
//        Poe poe = poeDto.toPoe();
//        Optional<Poe> optPoe = this.repository.findById(poe.getId());
//        if(optPoe.isPresent()) {
//            this.repository.save(poe);
//            return Optional.of(poe);
//        }
//        return Optional.empty();
//    }
//
//    public boolean delete(int id) {
//        Optional<Poe> optPoe = this.repository.findById((long)id);
//        if(optPoe.isPresent()) {
//            this.repository.delete(optPoe.get());
//            return true;
//        }
//        return false;
//    }
//}
