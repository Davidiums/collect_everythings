package davidius.authentificationservice.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthentificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAuthentification() throws Exception {
        mockMvc.perform(get("/authentification"))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200
                .andExpect(content().string("Hello World"));  // Vérifie que le contenu renvoyé est "Hello World"
    }
}
