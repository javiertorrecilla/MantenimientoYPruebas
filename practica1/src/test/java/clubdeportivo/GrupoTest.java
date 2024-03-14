package clubdeportivo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {
    
    private Grupo grupo;

    @Test
    @DisplayName("Comprueba el numero de plazas libres de ese grupo")
    public void plazasLibres_CheckNumeroPlazas() throws ClubException {
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int expectedValue = nplazas - matriculados;
        int returnValue = grupo.plazasLibres();

        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que el numero de plazas para el grupo se actualiza correctamente")
    public void actualizarPlazas_PlazasCorrectas_CheckPlazas() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int expectedValue = 50;
        grupo.actualizarPlazas(50);
        int returnValue = grupo.getPlazas();

        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que se lanza un error cuando se actualizan plazas negativas")
    public void actualizarPlazas_PlazasNegativas_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n=-1;
        assertThrows(ClubException.class,()-> grupo.actualizarPlazas(n));
    }

    @Test 
    @DisplayName("Comprueba que se lanza un error cuando hay menos plazas que los matriculados")
    public void actualizarPlazas_NotEnoughPlazas_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n=10;
        assertThrows(ClubException.class,()-> grupo.actualizarPlazas(n));
    }

    @Test
    @DisplayName("Comprueba que se añade correctamente el numero nuevo de matriculados")
    public void matricular_NumeroMatriculados_CheckNumeroMatriculados() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n = 8;
        int expectedValue = matriculados + n;
        grupo.matricular(n);
        assertEquals(expectedValue, grupo.getMatriculados());
    }

}
