package survey.backend.repository;

import survey.backend.dto.StagiaireDto;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StagiaireRepository {

    private Set<StagiaireDto> stagiaires;

    public StagiaireRepository() {      // Constructeur
        this.createFakeRepository();
    }

    public Set<StagiaireDto> getStagiaires() {
        return this.stagiaires;
    }

    public Optional<StagiaireDto> findById(int id) {
        return this.stagiaires.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
//        StagiaireDto[] stagiaires = (StagiaireDto[]) this.stagiaires.toArray();
//        for (StagiaireDto stagiaire: stagiaires) {
//            if (stagiaire.getId() == id) {
//                return Optional.of(stagiaire);
//            }
//        }
    }

    public boolean delete(StagiaireDto stagiaire) {
            return this.stagiaires.remove(stagiaire);
        }
    //public boolean delete(int id) {
//        Optional<StagiaireDto> stagiaire = this.findById(id);
//                //stagiaires.stream()
//                //.filter(s -> s.getId() == id)
//                //.findFirst();
//        if(stagiaire.isPresent()) {
//            return this.stagiaires.remove(stagiaire.get());
//        }
            /*
            this.stagiaires.remove(stagiaire);
            int size = this.stagiaires.size();
            this.stagiaires = this.stagiaires.stream()
                    .filter(s -> s.getId() != stagiaire.getId())
                    .collect(Collectors.toSet());
            return size != this.stagiaires.size();
        }*/
        //return false;
        //return this.stagiaires.remove();
    //}

    public StagiaireDto add(StagiaireDto stagiaireDto) {
        //stagiaireDto.setId(RANDOM_ID_GENERATOR.nextInt());
        stagiaireDto.setId(nextId());
        this.stagiaires.add(stagiaireDto);
        return stagiaireDto;
    }

    private void createFakeRepository() {
        this.stagiaires = Set.of(
            StagiaireDto.builder()
                .id(8)
                .lastName("Brown")
                .firstName("Mary")
                .birthDate(LocalDate.of(1976, 9, 10))
                .build(),
            StagiaireDto.builder()
                .id(9)
                .lastName("Doe")
                .firstName("Jane")
                .birthDate(LocalDate.of(1980, 2, 29))
                .build(),
            StagiaireDto.builder()
                .id(12)
                .lastName("Doe")
                .firstName("John")
                .birthDate(LocalDate.of(1960, 12, 21))
                .build(),
            StagiaireDto.builder()
                .id(14)
                .lastName("Smith")
                .firstName("William")
                .birthDate(LocalDate.of(1946, 3, 31))
                .build()
        ).stream().collect(Collectors.toSet());
    }

    private Integer nextId() {
        StagiaireDto[] stagiaires = (StagiaireDto[]) this.stagiaires.toArray();
        int nextId = 1;
        for (StagiaireDto stagiaire : stagiaires) {
            if (stagiaire.getId() > nextId) {
                nextId = stagiaire.getId();
            }
        }
        return Integer.valueOf(nextId + 1 );
    }
}
