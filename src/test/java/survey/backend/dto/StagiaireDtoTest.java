package survey.backend.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;   // L'import static permet d'avoir les classes qui serviront aux tests

class StagiaireDtoTest {

    @BeforeAll
    static void initData() {
        Integer id = 1;
        String lastname = "lastname";
        String firstname = "firstname";
        String email = "monemail@monemail.com";
        String phonenumber = "06 06 06 06 06";
        LocalDate birthdate = LocalDate.of(2000, 2, 29);
        StagiaireDto stagiaireDto = new StagiaireDto(id, lastname, firstname, email, phonenumber, birthdate);
    }

    @Test   // Annotation JUnit
    void testDefaultConstructor() {
        StagiaireDto stagiaireDto = new StagiaireDto();  // Retour de fonction : on peut utiliser var car le type est connu
        assertAll(
                () -> assertNull(stagiaireDto.getId(), "id"),
                () -> assertNull(stagiaireDto.getLastName(), "lastname"),
                () -> assertNull(stagiaireDto.getFirstName(), "firstname"),
                () -> assertNull(stagiaireDto.getEmail(), "email"),
                () -> assertNull(stagiaireDto.getPhoneNumber(), "phonenumber"),
                () -> assertNull(stagiaireDto.getBirthDate(), "birthdate")
        );  // Sans le assertAll, le premier qui plante arrête tout => on ne connaît pas le nombre d'erreurs
    }

    // TODO: all args constructor

    @Test
    void testAllArgsConstructor() {
        Integer id = 1;
        String lastname = "lastname";
        String firstname = "firstname";
        String email = "monemail@monemail.com";
        String phonenumber = "06 06 06 06 06";
        LocalDate birthdate = LocalDate.of(2000, 2, 29);
        StagiaireDto stagiaireDto = new StagiaireDto(id, lastname, firstname, email, phonenumber, birthdate);
        assertAll(
            () -> assertEquals(stagiaireDto.getId(), id, "id"),
            () -> assertEquals(stagiaireDto.getLastName(), lastname, "lastname"),
            () -> assertEquals(stagiaireDto.getFirstName(), firstname, "firstname"),
            () -> assertEquals(stagiaireDto.getEmail(), email, "email"),
            () -> assertEquals(stagiaireDto.getPhoneNumber(), phonenumber, "phonenumber"),
            () -> assertEquals(stagiaireDto.getBirthDate(), birthdate, "birthdate")
        );
    }

    // TODO: builder

    @Test
    void testBuilder() {
        // given
        int id = 123;
        String lastname = "doe";
        String firstname = "john";
        // when
        var stagiaireBuilder = StagiaireDto.builder();
        // then
    }
}