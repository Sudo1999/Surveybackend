package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import survey.backend.dto.StagiaireDto;
import survey.backend.service.StagiaireService;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StagiaireController.class)
class StagiaireControllerTest {

    final static String BASE_URL = "/api/stagiaire";
    @Autowired
    MockMvc mockMvc;     // Composant pour réaliser les appels du StagiaireController avec des requêtes Http
    @MockBean
    StagiaireService stagiaireService;

    @Test
    void testGetByIdOk() throws Exception {
        // prepare
        int id = 123;
        var stagiaireDto = StagiaireDto.builder()
                .id(id)
                .lastName("Doe")
                .firstName("John")
                .build();

        given(stagiaireService.findById(id))
                .willReturn(Optional.of(stagiaireDto));

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/stagiaire/123")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // then/verify HTTP communication
                .andDo(print())   // log request/response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

        // then/verify Mock service has been called
        then(stagiaireService)
                .should()
                .findById(id);
    }

    @Test
    void testGetByIdKoNotFound() throws Exception {
        // prepare
        int id = 0;
        given(stagiaireService.findById(id))
                .willReturn(Optional.empty());

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_URL + "/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                // then/verify HTTP communication
                .andDo(print()) // log request/response
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Stagiaire with id " + id + " not found"));

        // then/verify Mock service has been called
        then(stagiaireService)
                .should()
                .findById(id);
    }
}