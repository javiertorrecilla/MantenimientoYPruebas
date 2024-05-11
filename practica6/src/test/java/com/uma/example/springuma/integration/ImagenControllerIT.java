package com.uma.example.springuma.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagenControllerIT {
    
    @LocalServerPort
    private Integer port;

    private WebTestClient client;

    private Paciente paciente;

    private Medico medico;

    private ObjectMapper objectMapper = new ObjectMapper();


    @PostConstruct
    public void init(){
        client = WebTestClient.bindToServer().baseUrl("http://localhost:"+port)
        .responseTimeout(Duration.ofMillis(30000)).build();

        medico = new Medico("22222222A", "Alberto", "otorrino");
        medico.setId(1);

        paciente = new Paciente("Paco", 50, "10 Mayo", "11111111X", medico);
        paciente.setId(1);

        // Hacer la solicitud POST para crear el médico y el paciente
        client.post()
                .uri("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(medico), Medico.class)
                .exchange()
                .expectStatus().isCreated();

        client.post()
                .uri("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(paciente), Paciente.class)
                .exchange()
                .expectStatus().isCreated();

    }

    @Test
    @DisplayName("Test que comprueba que cuando se sube una imagen correctamente devuelve un codigo de tipo 200")
    public void uploadImage_checkCodigo2xx(){
        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));

        FluxExchangeResult<String> responseBody = client.post()
        .uri("/imagen")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build())
        .with("paciente", paciente))
        .exchange()
        .expectStatus().is2xxSuccessful().returnResult(String.class);

        String result = responseBody.getResponseBody().blockFirst();

        assertEquals("{\"response\" : \"file uploaded successfully : " + uploadFile.getName() + "\"}", result);
     }

     @Test 
     @DisplayName("Test que comprueba que si se sube una imagen sana y se realiza la predicción, devuelve no cancer")
     public void getImagenPrediction_isHealthy() throws IOException{
        //subimos la imagen
        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));

        FluxExchangeResult<String> responseBody = client.post()
        .uri("/imagen")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build())
        .with("paciente", paciente))
        .exchange()
        .expectStatus().is2xxSuccessful().returnResult(String.class);

        //hacemos la prediccion
        FluxExchangeResult<String> predictionBody = client.get().uri("/imagen/predict/1")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().valueEquals("Content-Type", "application/json")
        .returnResult(String.class);

        JsonNode jsonNode = objectMapper.readTree(predictionBody.getResponseBodyContent());
        String prediction = jsonNode.get("prediction").asText();

        //comprobamos que devuelve no cancer
        assertEquals("Not cancer (label 0),  score: 0.984481368213892", prediction);
     }

     @Test 
     @DisplayName("Test que comprueba que si se sube una imagen no sana y se realiza la predicción, devuelve cancer (label 1)")
     public void getImagenPrediction_isNotHealthy() throws IOException{
        //subimos la imagen
        File uploadFile = new File("./src/test/resources/no_healthty.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));

        FluxExchangeResult<String> responseBody = client.post()
        .uri("/imagen")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build())
        .with("paciente", paciente))
        .exchange()
        .expectStatus().is2xxSuccessful().returnResult(String.class);

        //hacemos la prediccion
        FluxExchangeResult<String> predictionBody = client.get().uri("/imagen/predict/1")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().valueEquals("Content-Type", "application/json")
        .returnResult(String.class);

        JsonNode jsonNode = objectMapper.readTree(predictionBody.getResponseBodyContent());
        String prediction = jsonNode.get("prediction").asText();

        //comprobamos que devuelve no cancer
        assertEquals("Cancer (label 1), score: 0.6412607431411743", prediction);
     }

     @Test
    @DisplayName("Test que comprueba que si no se han subido aun imagenes para ese paciente, la lista sera vacia")
    public void getImagenes_ReturnImagenesEmpty() {
        client.get().uri("/imagen/paciente/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() // hace la peticion
            .expectStatus().isOk() // comprueba que el codigo es OK
            .expectHeader().valueEquals("Content-Type", "application/json") // comprueba que el content type es json
            .expectBody().jsonPath("$", hasSize(0)); // comprueba que la respuesta tenga un array con tamanyo 0
    }
}



