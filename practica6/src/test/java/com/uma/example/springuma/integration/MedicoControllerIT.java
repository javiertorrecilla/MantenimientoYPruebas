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

import java.util.LinkedHashMap;

import com.uma.example.springuma.model.Medico;
import static org.hamcrest.Matchers.instanceOf;

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
    @DisplayName("Test que comprueba que un medico se obtiene correctamente tras haberlo añadido a medicoService")
    public void getMedico_isObtainedWithGet() throws JsonProcessingException, Exception{
        create_medico(medico);

        this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", instanceOf(LinkedHashMap.class)))
        .andExpect(jsonPath("$.dni").value(medico.getDni()))
        .andExpect(jsonPath("$.nombre").value(medico.getNombre()))
        .andExpect(jsonPath("$.especialidad").value(medico.getEspecialidad()));
    }

    
    private void create_medico(Medico medico) throws JsonProcessingException, Exception{
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Test que comprueba que un medico se actualiza correctamente si se modifican sus atributos")
    public void updateMedico_isUpdatedWithUpdate() throws JsonProcessingException, Exception{
        create_medico(medico);

        //actualizamos campos
        medico.setId(1);
        medico.setNombre("Pedro");
        medico.setEspecialidad("dermatologia");

        //realizamos solicitud put
        this.mockMvc.perform(put("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().is2xxSuccessful());
        
        //verificamos que ha sido actualizado
        this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", instanceOf(LinkedHashMap.class)))
        .andExpect(jsonPath("$.dni").value(medico.getDni()))
        .andExpect(jsonPath("$.nombre").value("Pedro"))
        .andExpect(jsonPath("$.especialidad").value("dermatologia"));
   
    }

    @Test
    @DisplayName("Test que comprueba que si se borra el medico, posteriormente si lo intentas obtener recibes un codigo de tipo 500")
    public void deleteMedico_withRemoveMedicoID() throws JsonProcessingException, Exception{
        create_medico(medico);

        this.mockMvc.perform(delete("/medico/1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Test que comprueba que se puede encontrar un medico por su dni")
    public void getMedicoByDni_isObtainedWithDni() throws JsonProcessingException, Exception{
        create_medico(medico);

        this.mockMvc.perform(get("/medico/dni/11111111X"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value(medico.getDni()))
        .andExpect(jsonPath("$.nombre").value(medico.getNombre()))
        .andExpect(jsonPath("$.especialidad").value(medico.getEspecialidad()));
    }

    @Test
    @DisplayName("Test que comprueba que si el dni no existe el medico no se puede encontrar")
    public void getMedicoByDni_noExiste_Error() throws JsonProcessingException, Exception{
        create_medico(medico);

        this.mockMvc.perform(get("/medico/dni/23"))
        .andExpect(status().is4xxClientError());
     }
}
