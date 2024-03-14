package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    @Test
    @DisplayName("Comprueba que se crea el constructor de la manera correcta")
    public void ClubDeportivo_WithTam_CheckCreation() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre, tam);
        String expectedValue = "ClubDeportivo --> [  ]";
        String returnValue = cd1.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que dos clubes deportivos no son iguales porque no tienen el mismo nombre")
    public void ClubDeportivo_Compare_DifferentNames() throws ClubException{
        String nombre1 = "ClubDeportivo";
        String nombre2 = "ClubBaloncesto";
        int tam = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre1);
        ClubDeportivo cd2 = new ClubDeportivo(nombre2, tam);
        String expectedValue = cd1.toString();
        String returnValue = cd2.toString();
        assertNotEquals(expectedValue, returnValue);
    }


    /*@Test
    @DisplayName("Comprueba que se lanza un error si se crea un club con 0 o menos grupos")
    public void ClubDeportivo_NoGroups_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam = 0;
        assertThrows(ClubException.class, () -> ClubDeportivo cd = new ClubDeportivo(nombre, tam));
    }*/

    @Test
    @DisplayName("Comprueba que se añade una actividad dado un string valido")
    public void anyadirActividad_StringDatos_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String[] datos = {"GrupoUno", "Yoga", "20", "15", "10"}; 

        club.anyadirActividad(datos);

        String expectedValue = "ClubDeportivo --> [ (GrupoUno - Yoga - 10.0 euros - P:20 - M:15) ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    /*@Test
    @DisplayName("Comprueba que se lanza un error debido a que no se insertan los datos de formato correcto")
    public void anyadirActividad_StringDatos_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String[] datos = {"GrupoUno", "Yoga", "Veinte", "15", "10.50"}; 

        assertThrows(NumberFormatException.class,() -> club.anyadirActividad(datos));
    }*/

    /*@Test
    @DisplayName("Comprueba que se lanza un error debido a que se inserta un grupo nulo")
    public void anyadirActividad_NullGroup_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertThrows(ClubException.class,() -> club.anyadirActividad(null));
    }*/

    @Test
    @DisplayName("Comprueba que se actualiza las plazas de un grupo ya existente")
    public void anyadirActividad_ExistingGroup_UpdatePlazas() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        String cod = "GrupoUno";
        String act = "Yoga";
        int plazas = 20;
        int matriculas =  5;
        double coste =  10;
        Grupo grupo2 = new Grupo(cod, act, plazas, matriculas, coste);

        club.anyadirActividad(grupo2);

        int expectedValue = 20;
        int returnValue = grupo2.getPlazas();

        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que se añade una actividad a un club dado un grupo de la misma")
    public void anyadirActividad_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre, tam);

        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        cd1.anyadirActividad(grupo);

        String expectedValue = "ClubDeportivo --> [ (GrupoUno - Yoga - 10.0 euros - P:10 - M:5) ]";
        String returnValue = cd1.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que las plazas libres de una actividad cuando hay mas de un grupo de dicha actividad")
    public void plazasLibres_MoreThanOneGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        String codigo2 = "GrupoDos";
        String actividad2 = "Yoga";
        int nplazas2 = 10;
        int matriculados2 =  5;
        double tarifa2 =  10;
        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);

        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);

        int expectedValue = grupo1.plazasLibres()+grupo2.plazasLibres();
        int returnValue = club.plazasLibres(grupo1.getActividad());

        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que las plazas libres de una actividad cuando solo hay un grupo de dicha actividad")
    public void plazasLibres_OnlyOneGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        int expectedValue = grupo1.plazasLibres();
        int returnValue = club.plazasLibres(grupo1.getActividad());

        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que las plazas libres de una actividad cuando solo hay un grupo de dicha actividad")
    public void plazasLibres_NoGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        int expectedValue = 0;
        int returnValue = club.plazasLibres("Pilates");

        assertEquals(expectedValue, returnValue);
    }

    /*@Test
    @DisplayName("Comprueba que salta un error cuando se intenta matricular a más personas de las plazas libres que quedan en una actividad")
    public void matricular_LessPlazas_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        assertThrows(ClubException.class, () -> club.matricular(grupo1.getActividad(), 10));
    }*/

    @Test
    @DisplayName("Comprueba que se actualizan las matriculaciones de un grupo correctamente")
    public void matricular__Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        int nuevos = 3;

        int expectedValue = matriculados+nuevos;
        club.matricular(grupo1.getActividad(), nuevos);
        int returnValue = grupo1.getMatriculados();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que se actualizan las matriculaciones de un grupo correctamente")
    public void matricular_MoreThanOneGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        String codigo2 = "GrupoDos";
        String actividad2 = "Yoga";
        int nplazas2 = 10;
        int matriculados2 =  5;
        double tarifa2 =  10;
        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);

        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);

        int nuevos = 8;

        int expectedValue = matriculados+matriculados2+nuevos;
        club.matricular(grupo1.getActividad(), nuevos);
        int returnValue = grupo1.getMatriculados()+grupo2.getMatriculados();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que no se actualizan las matriculaciones de un grupo correctamente")
    public void ingresos_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        ClubDeportivo club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        double expectedValue = matriculados*tarifa;
        double returnValue = club.ingresos();
        assertEquals(expectedValue, returnValue);
    }

}

