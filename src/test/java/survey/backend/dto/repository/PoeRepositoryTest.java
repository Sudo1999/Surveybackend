package survey.backend.dto.repository;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//import survey.backend.entities.Poe;
//import survey.backend.enums.PoeType;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = NONE)
//class PoeRepositoryTest {
//
//   @Autowired
//    PoeRepository poeRepository;
//   @Autowired
//    TestEntityManager entityManager;    // Hibernate component to write data in DB before each test
//
//   @Test
//   @SneakyThrows
//    void testFindByEndingInRange() {
//      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////       Date date1 = new Date(2022, 1, 1);
////       Date date2 = new Date(2022, 12, 31);
//      Date date1 = df.parse("2022-01-01");
//      Date date2 = df.parse("2022-12-31");
//       //var poes = poeRepository.findByEndingInRange(date1, date2);
//      var poes = List.of(
//              Poe.builder()
//                      .title("Java Fullstack 1")
//                      .beginDate(df.parse("2021-01-01"))
//                      .endDate(df.parse("2021-06-01"))
//                      .poeType(PoeType.POEI)
//                      .build(),
//              Poe.builder()
//                      .title("Java Fullstack 2")
//                      .beginDate(df.parse("2021-10-01"))
//                      .endDate(df.parse("2022-01-01"))
//                      .poeType(PoeType.POEC)
//                      .build(),
//              Poe.builder()
//                      .title("Java Fullstack 3")
//                      .beginDate(df.parse("2022-03-01"))
//                      .endDate(df.parse("2022-06-01"))
//                      .poeType(PoeType.POEI)
//                      .build(),
//              Poe.builder()
//                      .title("Java Fullstack 4")
//                      .beginDate(df.parse("2022-10-01"))
//                      .endDate(df.parse("2022-12-31"))
//                      .poeType(PoeType.POEC)
//                      .build(),
//              Poe.builder()
//                      .title("Java Fullstack 5")
//                      .beginDate(df.parse("2022-10-01"))
//                      .endDate(df.parse("2023-01-01"))
//                      .poeType(PoeType.POEI)
//                      .build()
//      );
//       System.out.println(poes);
//       // Save data in DB
//       poes.forEach(poe -> entityManager.persist(poe));
//       entityManager.flush();   // Synchronize Hibernate cache with DB
//
//       // When
//       var poesFound = poeRepository.findByEndingInRange(date1, date2);
//
//       // Then
//       System.out.println(poesFound);
//       assertEquals(3, poesFound.size(), "Poes found number");
//       assertAll(poesFound.stream()
//               .map(poe -> () -> assertTrue(
//                       (date1.compareTo(poe.getEndDate()) <= 0)
//                       && (poe.getEndDate().compareTo(date2) <= 0),
//                       "Poe endDate in range"
//               ))
//       );
//   }
//}
