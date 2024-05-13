package com.uma.example.springuma.integration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InformeControllerIT extends AbstractIntegration {

        @Autowired
        private ObjectMapper objectMapper;

        @LocalServerPort
        private Integer port;

        private WebTestClient client;

        private Medico medico;
        private Paciente paciente;

        private Informe informe;

        @PostConstruct
        public void init() {
                client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port)
                                .responseTimeout(Duration.ofMillis(30000)).build();

                medico = new Medico();
                medico.setNombre("Medico1");
                medico.setEspecialidad("Mamografias");
                medico.setDni("87654321A");
                medico.setId(1);

                paciente = new Paciente();
                paciente.setNombre("Paciente1");
                paciente.setEdad(20);
                paciente.setCita("03-06-2024");
                paciente.setDni("12345678X");
                paciente.setMedico(medico);
                paciente.setId(1);

                File imageFile = new File("src/test/resources/healthy.png");
                Imagen imagen = new Imagen(imageFile.toString().getBytes(), paciente);
                imagen.setId(1);
                imagen.setNombre("healthy.png");

                informe = new Informe(
                                "Not cancer (label 0),  score: 0.984481368213892",
                                "Mamografia", imagen);
                informe.setId(1);
        }

        @Test
        @DisplayName("Crea y devuelve correctamente el informe seleccionado por el método get")
        public void saveAndGetInform_isOK() throws IOException {
                crearMedico(medico);
                crearPaciente(paciente);
                crearImagen(new File("src/test/resources/healthy.png"), paciente);
                crearInforme(informe);

                FluxExchangeResult<Informe> result = client.get().uri("/informe/1")
                                .exchange()
                                .expectStatus().isOk().returnResult(Informe.class);
                Informe informeObtained = result.getResponseBody().blockFirst();

                assertEquals(informe.toString(), informeObtained.toString());
        }

        @Test
        @DisplayName("Elimina correctamente el informe seleccionado por el método delete")
        public void deleteInforme_isDeletedOK() throws IOException {
                crearMedico(medico);
                crearPaciente(paciente);
                crearImagen(new File("src/test/resources/healthy.png"), paciente);
                crearInforme(informe);

                FluxExchangeResult<Informe> result = client.get().uri("/informe/1")
                                .exchange()
                                .expectStatus().isOk().returnResult(Informe.class);
                Informe informeObtained = result.getResponseBody().blockFirst();

                assertEquals(informe.toString(), informeObtained.toString());

                client.delete().uri("/informe/1")
                                .exchange()
                                .expectStatus()
                                .isNoContent();

                FluxExchangeResult<Informe> resultDelete = client.get().uri("/informe/1")
                                .exchange()
                                .expectStatus()
                                .isOk().returnResult(Informe.class);
                Informe informeDeleted = resultDelete.getResponseBody().blockFirst();
                
                assertNull(informeDeleted);
        }

        private void crearMedico(Medico medico) {
                client.post()
                                .uri("/medico")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(medico), Medico.class)
                                .exchange()
                                .expectStatus().isCreated();
        }

        private void crearPaciente(Paciente paciente) {
                client.post()
                                .uri("/paciente")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(paciente), Paciente.class)
                                .exchange()
                                .expectStatus().isCreated();
        }

        private void crearInforme(Informe informe) throws IOException {
                FluxExchangeResult<String> predictionBody = client.get().uri("/imagen/predict/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().valueEquals("Content-Type", "application/json")
                                .returnResult(String.class);

                JsonNode preditionResponseJson = objectMapper.readTree(predictionBody.getResponseBodyContent());
                String predictionResult = preditionResponseJson.get("prediction").asText();
                client.post().uri("/informe")
                                .body(Mono.just(informe), Informe.class)
                                .exchange()
                                .expectStatus().isCreated()
                                .expectBody().returnResult();

                informe.setPrediccion(predictionResult);

        }

        private void crearImagen(File imageFile, Paciente paciente) throws IOException {

                MultipartBodyBuilder builder = new MultipartBodyBuilder();
                builder.part("image", new FileSystemResource(imageFile));

                client.post()
                                .uri("/imagen")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .body(BodyInserters.fromMultipartData(builder.build())
                                                .with("paciente", paciente))
                                .exchange()
                                .expectStatus().isOk().returnResult(String.class);
        }

}