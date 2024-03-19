package clubdeportivo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {
    
    private Grupo grupo;

    //los test de los getters se encuentran en GrupoTest.java para un mayor orden en el codigo

    @Test
    @DisplayName("Comoprueba que el nombre del grupo no puede ser nulo")
    public void grupo_NombreNulo_ThrowsError() throws ClubException{
        String codigo = null;
        String actividad = "Pilates";
        int nplazas = 0;
        int matriculados = 0;
        double tarifa = 15;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comoprueba que la actividad del grupo no puede ser nula")
    public void grupo_ActividadNula_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = null;
        int nplazas = 0;
        int matriculados = 0;
        double tarifa = 15;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprueba que el numero de plazas de un grupo no puede ser menor a 1")
    public void grupo_NumeroPlazasMenorAUno_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 0;
        int matriculados = 0;
        double tarifa = 15;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprueba que el numero de matriculados de un grupo no puede ser menor a 0")
    public void grupo_NumeroMatriculadosNegativo_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 20;
        int matriculados = -2;
        double tarifa = 15;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprueba que la tarifa debe ser positiva y estrictamente mayor a cero")
    public void grupo_TarifaNegativa_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 20;
        int matriculados = 10;
        double tarifa = 0;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprueba que el numero de matriculados debe ser menor al numero de plazas del grupo")
    public void grupo_MasMatriculasQuePlazas_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 20;
        int matriculados = 30;
        double tarifa = 15;

        assertThrows(ClubException.class,()-> grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprueba el numero de plazas libres de ese grupo")
    public void plazasLibres_CheckPlazasLibres() throws ClubException {
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
        int n = 50;
        grupo.actualizarPlazas(n);
        int returnValue = grupo.getPlazas();

        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que se lanza un error cuando se actualiza a menos de 1 el numero de plazas")
    public void actualizarPlazas_PlazasMenorAUno_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n=0;
        assertThrows(ClubException.class,()-> grupo.actualizarPlazas(n));
    }

    @Test 
    @DisplayName("Comprueba que se lanza un error cuando hay menos plazas que el numero de matriculados")
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
    public void matricular_NumeroAMatricularCorrecto_CheckNumeroMatriculados() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n = 8;
        int expectedValue = matriculados + n;
        grupo.matricular(n);
        int returnValue = grupo.getMatriculados();
        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que no se pueden matricular mas personas que el numero de plazas libres")
    public void matricular_MatriculadosMayorQuePlazas_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n = 12;
        assertThrows(ClubException.class, ()->grupo.matricular(n));
    }

    @Test 
    @DisplayName("Comprueba que no se pueden matricular menos de una persona")
    public void matricular_NumeroMatriculadosNegativos_ThrowsError() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        int n = 0;
        assertThrows(ClubException.class, ()->grupo.matricular(n));
    }

    @Test 
    @DisplayName("Comprueba que se construye bien el objeto Grupo y el toString es correcto")
    public void toString_ObjectGroup_ChecktoString() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String returnValue = grupo.toString();
        String expectedValue = "("+ codigo + " - "+actividad+" - " + tarifa + " euros "+ "- P:" + nplazas +" - M:" +matriculados+")";

        assertEquals(returnValue, expectedValue);
    }

    @Test
    @DisplayName("Comprueba que dos grupos son iguales si sus codigos y actividades son iguales, ignorando mayusculas y minusculas")
    public void equals_TwoGroups_ReturnTrue() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String codigo2 = "grupouno";
        String actividad2 = "pilates";
        int nplazas2 = 10;
        int matriculados2 = 5;
        double tarifa2 = 3;

        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);
        
        assertTrue(grupo.equals(grupo2));
    }

    @Test
    @DisplayName("Comprueba que dos grupos no son iguales ya que su codigo no es el mismo")
    public void equals_TwoGroupsDifferentCode_ReturnFalse() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String codigo2 = "grupodos";
        String actividad2 = "pilates";
        int nplazas2 = 10;
        int matriculados2 = 5;
        double tarifa2 = 3;

        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);
        
        assertFalse(grupo.equals(grupo2));
    }

    @Test
    @DisplayName("Comprueba que dos grupos no son iguales ya que su actividad no es la misma")
    public void equals_TwoGroupsDifferentActivity_ReturnFalse() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String codigo2 = "grupouno";
        String actividad2 = "yoga";
        int nplazas2 = 10;
        int matriculados2 = 5;
        double tarifa2 = 3;

        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);
        
        assertFalse(grupo.equals(grupo2));
    }

    @Test
    @DisplayName("Comprueba que el método equals devuelve false cuando se compara un Grupo con un objeto que no es Grupo")
    public void equals_CompareWithNonGroupObject_ReturnFalse() throws ClubException {
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;
            
        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        Object otroObjeto = new Object(); // Crear un objeto que no sea Grupo

        assertFalse(grupo.equals(otroObjeto));
    }


    @Test 
    @DisplayName("Comprueba que dos grupos tienen el mismo hashCode por tener el mismo codigo y actividad, independientemente de mayusculas y minusculas")
    public void hashCode_TwoGroups_CheckSameHashCode() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String codigo2 = "grupouno";
        String actividad2 = "pilates";
        int nplazas2 = 10;
        int matriculados2 = 5;
        double tarifa2 = 3;

        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);
        
        int value1 = grupo.hashCode();
        int value2 = grupo2.hashCode();

        assertEquals(value1, value2);
    }

    @Test 
    @DisplayName("Comprueba que dos grupos no tienen el mismo hashcode si difieren en su codigo o actividad")
    public void hashCode_TwoGroups_CheckDifferentHashCode() throws ClubException{
        String codigo = "GrupoUno";
        String actividad = "Pilates";
        int nplazas = 30;
        int matriculados = 20;
        double tarifa = 15;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        
        String codigo2 = "grupodos";
        String actividad2 = "pilates";
        int nplazas2 = 10;
        int matriculados2 = 5;
        double tarifa2 = 3;

        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);
        
        int value1 = grupo.hashCode();
        int value2 = grupo2.hashCode();

        assertNotEquals(value1, value2);
    }
}
