// Realizado por Javier Torrecilla Reyes y Sandra Vázquez Pérez

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoGetTest {
    
    private Grupo grupo;

    @Test
    @DisplayName("Comprueba que el codigo del grupo es devuelto correctamente")
    public void getCodigo_CheckGetCodigo() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String returnValue = grupo.getCodigo();

        assertEquals(codigo, returnValue);
    }

    @Test
    @DisplayName("Comprueba que la actividad del grupo es devuelta correctamente")
    public void getActividad_CheckGetActividad() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String returnValue = grupo.getActividad();

        assertEquals(actividad, returnValue);
    }

    @Test
    @DisplayName("Comprueba que el numero de plazas del grupo son devueltas correctamente")
    public void getPlazas_CheckGetPlazas() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int returnValue = grupo.getPlazas();

        assertEquals(nplazas, returnValue);
    }

    @Test
    @DisplayName("Comprueba que el numero de matriculados del grupo son devueltos correctamente")
    public void getMatriculados_CheckGetMatriculados() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int returnValue = grupo.getMatriculados();

        assertEquals(matriculados, returnValue);
    }

    @Test
    @DisplayName("Comprueba que la tarifa del grupo es devuelta correctamente")
    public void getTarifa_CheckGetTarifa() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        double returnValue = grupo.getTarifa();

        assertEquals(tarifa, returnValue);
    }
}
