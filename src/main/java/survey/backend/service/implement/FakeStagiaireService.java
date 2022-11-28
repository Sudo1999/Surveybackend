package survey.backend.service.implement;

import org.springframework.stereotype.Service;
import survey.backend.dto.StagiaireDto;
import survey.backend.service.StagiaireService;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;

@Service
public class FakeStagiaireService implements StagiaireService {

    @Override
    public Set<StagiaireDto> findAll() {
        return Set.of(
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
                .build());
    }

    @Override
    public Optional<StagiaireDto> findById(int id) {
        if (id % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(StagiaireDto.builder()
                .id(id)
                .lastName("Jobs")
                .firstName("Steve")
                .birthDate(LocalDate.of(1955, 2, 24))
                .build());
        }
    }

    @Override
    public Set<StagiaireDto> search(String lastName, String firstName) {
        //return null;
        final String DEFAULT_FAMILY = "FOUNDER";
        if (Objects.isNull(lastName) && Objects.isNull(firstName)) {
            return Set.of();
        }
        return Set.of(
                StagiaireDto.builder()
                        .id(208)
                        .lastName(Objects.isNull(lastName) ? "Founder" : lastName)
                        .firstName(Objects.isNull(firstName) ? "Mary" : firstName)
                        .build(),
                StagiaireDto.builder()
                        .id(209)
                        .lastName("Founder")
                        .firstName("Jane")
                        .build(),
                StagiaireDto.builder()
                        .id(214)
                        .lastName("Founder")
                        .firstName("William")
                        .build());
    }

    @Override
    public StagiaireDto add(StagiaireDto stagiaireDto) {
        //return null;
        stagiaireDto.setId(RandomGenerator.getDefault().nextInt());
        return stagiaireDto;
    }

    @Override
    public Optional<StagiaireDto> update(StagiaireDto stagiaireDto) {
        //return null;
        if (stagiaireDto.getId() % 2 == 0) {
            return Optional.empty();
        } else {
            stagiaireDto.setLastName("Bourne");
            stagiaireDto.setFirstName("Jason");
            return Optional.of(stagiaireDto);
        }
    }

    @Override
    //public void delete(int id) {
    public boolean delete(int id) {
        return id % 2 == 1;
    }
}
