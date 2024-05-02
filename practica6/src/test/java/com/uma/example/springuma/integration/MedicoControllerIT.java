package com.uma.example.springuma.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.uma.example.springuma.model.Medico;
import static org.hamcrest.Matchers.hasSize;

public class MedicoControllerIT extends AbstractIntegration {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Medico medico;

    @BeforeEach
    public void init(){
        medico = new Medico();

        medico.setDni("11111111X");
        medico.setNombre("JavierTorrecilla");
        medico.setEspecialidad("ginecologo");
    }

    @Test
    @DisplayName("Test que comprueba que un medico se obtiene correctamente")
    public void getMedico_isObtainedWithGet() throws JsonProcessingException, Exception{
        create_medico(medico);

        this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value(medico.getDni()));
    }

    
    private void create_medico(Medico medico) throws JsonProcessingException, Exception{
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated());
    }
}
