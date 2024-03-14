package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    @Test
    @DisplayName("Comprueba que dos clubes deportivos son iguales si su nombre es el mismo y el numero de grupos tambien lo es")
    public void ClubDeportivo_Compare_CheckEquals() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre, tam);
        ClubDeportivo cd2 = new ClubDeportivo(nombre, tam);
        String expectedValue = cd1.toString();
        String returnValue = cd2.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que dos clubes deportivos no son iguales porque no tienen el mismo nombre")
    public void ClubDeportivo_Compare_DifferentNames() throws ClubException{
        String nombre1 = "ClubDeportivo";
        String nombre2 = "ClubBaloncesto";
        int tam = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre1, tam);
        ClubDeportivo cd2 = new ClubDeportivo(nombre2, tam);
        String expectedValue = cd1.toString();
        String returnValue = cd2.toString();
        assertNotEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que dos clubes deportivos no son iguales porque no tienen el mismo numero de grupos")
    public void ClubDeportivo_Compare_DifferentGroups() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        int tam2 = 5;
        ClubDeportivo cd1 = new ClubDeportivo(nombre, tam1);
        ClubDeportivo cd2 = new ClubDeportivo(nombre, tam2);
        String expectedValue = cd1.toString();
        String returnValue = cd2.toString();
        assertNotEquals(expectedValue, returnValue);
    }



}
