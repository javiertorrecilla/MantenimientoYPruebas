package com.uma.example.springuma.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;

public class PacienteControllerIT extends AbstractIntegration{
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Medico medico;
    private Paciente paciente;

    @BeforeEach
    public void init(){

        medico = new Medico();
        medico.setDni("11111111X");
        medico.setNombre("JavierTorrecilla");
        medico.setEspecialidad("ginecologo");
        medico.setId(1);

        paciente = new Paciente();
        paciente.setNombre("Sandra");
        paciente.setCita("14 de agosto");
        paciente.setDni("12341234F");
        paciente.setEdad(33);
        paciente.setId(1);
        paciente.setMedico(medico);
    }

    private void create_medicoOK(Medico medico) throws JsonProcessingException, Exception{
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated());

    }

    private void create_pacienteOK(Paciente paciente) throws JsonProcessingException, Exception{
        this.mockMvc.perform(post("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test que comprueba que un paciente se obtiene correctamente")
    public void getPaciente_isObtainedWithGet() throws JsonProcessingException, Exception{
        create_medicoOK(medico);
        create_pacienteOK(paciente);

        this.mockMvc.perform(get("/paciente/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value(paciente.getDni()))
        .andExpect(jsonPath("$.nombre").value(paciente.getNombre()));
    }

    @Test
    @DisplayName("Test que comprueba que si el medico no tiene asociado aun ningun paciente la lista esta vacia")
    public void listOfPacientes_initially_isEmpty() throws Exception{
        create_medicoOK(medico);

        this.mockMvc.perform(get("/paciente/medico/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(0)))
        .andExpect(content().string("[]"))
        .andReturn();

    }

    @Test
    @DisplayName("Test que comprueba se obtiene la lista de pacientes de un medico y tiene longitud 1 porque solo tienen un paciente ese medico")
    public void listOfPacientes_unPaciente_sizeOne() throws JsonProcessingException, Exception{
        create_medicoOK(medico);
        create_pacienteOK(paciente);

        this.mockMvc.perform(get("/paciente/medico/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].dni").value(paciente.getDni()));
    }

    @Test
    @DisplayName("Test que comprueba que un paciente se actualiza correctamente")
    public void updateCuenta_updatePaciente_isUpdatedWithPut() throws JsonProcessingException, Exception{
        create_medicoOK(medico);
        create_pacienteOK(paciente);
        paciente.setDni("12345678S");

        this.mockMvc.perform(put("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(get("/paciente/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value("12345678S"));
    }

    @Test
    @DisplayName("Test que comprueba que se elimina un paciente correctamente y que si se intenta obtener se recibe un codigo de tipo 500")
    public void deletePaciente_isDeleted() throws JsonProcessingException, Exception{
        create_medicoOK(medico);
        create_pacienteOK(paciente);

        this.mockMvc.perform(delete("/paciente/1")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpectAll(status().is2xxSuccessful());

        this.mockMvc.perform(get("/paciente/1"))
        .andExpect(status().is5xxServerError());
    }


}
