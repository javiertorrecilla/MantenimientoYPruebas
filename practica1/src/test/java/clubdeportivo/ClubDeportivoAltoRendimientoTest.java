package clubdeportivo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoAltoRendimientoTest {

    ClubDeportivoAltoRendimiento club;

    @Test
    @DisplayName("Comprueba que lanza una excepcion cuando se crea un club de alto rendimiento con maximo menor o igual que 0")
    public void ClubDeportivoAltoRendimiento_NegativeMaximun_ThrowsError(){
        String nombre = "ClubDeAltoRendimiento";
        int maximo = -2;
        int incremento = 5;
        
        assertThrows(ClubException.class, () -> club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    @Test
    @DisplayName("Comprueba que lanza una excepcion cuando se crea un club de alto rendimiento con incremento menor o igual que 0")
    public void ClubDeportivoAltoRendimiento_NegativeIncrement_ThrowsError(){
        String nombre = "ClubDeAltoRendimiento";
        int maximo = 5;
        int incremento = -5;
        
        assertThrows(ClubException.class, () -> club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    @Test
    @DisplayName("Comprueba que se crea correctamente un club sin tamaño")
    public void ClubDeportivoAltoRendimiento_NoSize_Check() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        
        String expectedValue = nombre + " --> [  ]";
        String returnValue = club.toString();

        assertEquals(expectedValue, returnValue);
        
    }

    @Test
    @DisplayName("Comprueba que lanza una excepcion cuando se crea un club de alto rendimiento con un tamaño y con maximo menor o igual que 0")
    public void ClubDeportivoAltoRendimiento_NegativeMaximun_ThrowsErrorV2(){
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = -2;
        int incremento = 5;

        assertThrows(ClubException.class, () -> club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento));
    }

    @Test
    @DisplayName("Comprueba que lanza una excepcion cuando se crea un club de alto rendimiento con un tamaño y con incremento menor o igual que 0")
    public void ClubDeportivoAltoRendimiento_NegativeIncrement_ThrowsErrorV2(){
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = -5;

        assertThrows(ClubException.class, () -> club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento));
    }

    @Test
    @DisplayName("Comprueba que se crea correctamente un club con tamaño")
    public void ClubDeportivoAltoRendimiento_WithTam_Check() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        
        String expectedValue = nombre + " --> [  ]";
        String returnValue = club.toString();

        assertEquals(expectedValue, returnValue);
        
    }

    @Test
    @DisplayName("Comprueba que se añade correctamente un grupo a un club")
    public void anyadirActividad_NewGroup_CheckPlazasMasQueMaximo() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        String[] datos = {"GrupoUno", "Yoga", "10", "3", "10"}; 
        club.anyadirActividad(datos);

        String expectedValue = nombre + " --> [ (GrupoUno - Yoga - 10.0 euros - P:5 - M:3) ]";
        String returnValue = club.toString();

        assertEquals(expectedValue, returnValue);
        
    }

    @Test
    @DisplayName("Comprueba que se añade correctamente un grupo a un club con numero de plazas menores al maximo")
    public void anyadirActividad_NewGroup_CheckPlazasMenosQueMaximo() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        String[] datos = {"GrupoUno", "Yoga", "4", "3", "10"}; 
        club.anyadirActividad(datos);

        String expectedValue = nombre + " --> [ (GrupoUno - Yoga - 10.0 euros - P:4 - M:3) ]";
        String returnValue = club.toString();

        assertEquals(expectedValue, returnValue);
        
    }

    @Test
    @DisplayName("Comprueba lanza un error cuando no hay suficientes datos para crear un grupo")
    public void anyadirActividad_MissingDatas_ThrowsError() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        String[] datos = {"GrupoUno", "Yoga", "5", "3"}; 

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Comprueba que se lanza un error cuando se proporciona un formato incorrecto en los datos")
    public void anyadirActividad_IncorrectFormat_ThrowsError() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        String[] datos = {"GrupoUno", "Yoga", "5", "3", "Diez"}; 

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    
    @Test
    @DisplayName("Comprueba que te devuelve el ingreso del Club Deportivo de Alto Rendimiento Correctamente")
    public void ingresos_AltoRendimiento_Check() throws ClubException{
        String nombre = "ClubDeAltoRendimiento";
        int tam = 5;
        int maximo = 5;
        int incremento = 5;
        club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        String[] datos = {"GrupoUno", "Yoga", "10", "3", "10"}; 
        club.anyadirActividad(datos);
        double cantidad = Integer.parseInt(datos[3]) * Integer.parseInt(datos[4]);

        double expectedValue = cantidad + cantidad * ((double) incremento/100);
        double returnValue = club.ingresos();

        assertEquals(expectedValue, returnValue);
    } 
}
