package survey.backend.service.Implement;

import org.springframework.stereotype.Service;
import survey.backend.dto.StagiaireDto;
import survey.backend.error.NoDataFoundError;
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
                .id(1)
                .lastName("Doe")
                .firstName("John")
                .birthDate(LocalDate.of(1900, 1, 12))
                .build(),
            StagiaireDto.builder()
                .id(12)
                .lastName("Doe")
                .firstName("Jane")
                .birthDate(LocalDate.of(1920, 7, 6))
                .build(),
            StagiaireDto.builder()
                .id(57)
                .lastName("Unknown")
                .firstName("John")
                .birthDate(LocalDate.of(1952, 2, 29))
                .build(),
            StagiaireDto.builder()
                .id(78)
                .lastName("Unknown")
                .firstName("Jane")
                .birthDate(LocalDate.of(1942, 1, 29))
                .build()
        );
    }

    @Override
    public Optional<StagiaireDto> findById(int id) {
        //return Optional.empty();

        if (id % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(StagiaireDto.builder()
                .id(id)
                .lastName("Jobs")
                .firstName("Steve")
                .birthDate(LocalDate.of(1976, 3, 1))
                .build());
        }
    }

    @Override
    public Set<StagiaireDto> search(String lastName, String firstName) {
        //return null;
        final String DEFAULT_FAMILY = "found";
        if (Objects.isNull(lastName) && Objects.isNull(firstName)){
            return Set.of();
        }
        return Set.of(
            StagiaireDto.builder()
                .id(1)
                .lastName(Objects.isNull(lastName) ? "Found" : lastName)
                .firstName(Objects.isNull(firstName) ? "John" : firstName)
                .build(),
            StagiaireDto.builder()
                .id(12)
                .lastName(Objects.isNull(lastName) ? "Found" : lastName)
                .firstName(Objects.isNull(firstName) ? "Jane" : firstName)
                .build(),
            StagiaireDto.builder()
                .id(57)
                .lastName(Objects.isNull(lastName) ? "Found" : lastName)
                .firstName(Objects.isNull(firstName) ? "Jimmy" : firstName)
                .build()
        );
    }

    @Override
    public StagiaireDto add(StagiaireDto stagiaireDto) {
        //return null;
        stagiaireDto.setId(RandomGenerator.getDefault().nextInt());
        return stagiaireDto;
    }

    @Override
    //public StagiaireDto update(StagiaireDto stagiaireDto) {
    public Optional<StagiaireDto> update(StagiaireDto stagiaireDto) {
        //return null;
        if (stagiaireDto.getId() % 2 == 0) {
                return Optional.empty();
            } else {
                return Optional.of(stagiaireDto);
            }
    }

    @Override
    //public void delete(int id) {
    public boolean delete(int id) {
        return  id % 2 == 1;
    }
}
