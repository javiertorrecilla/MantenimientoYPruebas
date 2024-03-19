package clubdeportivo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoTest {

    ClubDeportivo club;

    @Test
    @DisplayName("Comprueba que se crea un Club Deportivo correctamente con su nombre")
    public void ClubDeportivo_WithTam_CheckCreation() throws ClubException{
        String nombre = "ClubDeportivo";
        club = new ClubDeportivo(nombre);
        String expectedValue = nombre + " --> [  ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }
    
    @Test
    @DisplayName("Comprueba que se crea un Club Deportivo con un numero n de grupos correctamente")
    public void ClubDeportivo_WithNGroups_CheckCreation() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 3;
        club = new ClubDeportivo(nombre, n);
        String expectedValue = nombre + " --> [  ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que dos clubes deportivos no son iguales porque no tienen el mismo nombre")
    public void ClubDeportivo_Compare_DifferentNames() throws ClubException{
        String nombre1 = "ClubDeportivo";
        String nombre2 = "ClubBaloncesto";
        int n = 3;
        ClubDeportivo cd1 = new ClubDeportivo(nombre1);
        ClubDeportivo cd2 = new ClubDeportivo(nombre2, n);
        String expectedValue = cd1.toString();
        String returnValue = cd2.toString();
        assertNotEquals(expectedValue, returnValue);
    }


    @Test
    @DisplayName("Comprueba que se lanza un error si se crea un club con 0 o menos grupos")
    public void ClubDeportivo_NoGroups_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 0;
        assertThrows(ClubException.class, () -> club = new ClubDeportivo(nombre, n));
    }

    @Test
    @DisplayName("Comprueba que se lanza un error si se crea un club con 0 o menos grupos")
    public void ClubDeportivo_NoName_ThrowsError() throws ClubException{
        String nombre = null;
        int n = 3;
        assertThrows(ClubException.class, () -> club = new ClubDeportivo(nombre, n));
    }

    @Test
    @DisplayName("Comprueba lanza un error cuando no hay suficientes datos para crear un grupo")
    public void anyadirActividad_MissingDatas_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 3;
        club = new ClubDeportivo(nombre, n);

        String[] datos = {"GrupoUno", "Yoga", "5", "3"}; 

        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }


    @Test
    @DisplayName("Comprueba que se añade una actividad en un grupo nuevo dado un string valido")
    public void anyadirActividad_StringDatos_CheckToString() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 1;
        club = new ClubDeportivo(nombre, n);
        
        String[] datos = {"GrupoUno", "Yoga", "20", "15", "10"}; 

        club.anyadirActividad(datos);

        String expectedValue = nombre + " --> [ (GrupoUno - Yoga - 10.0 euros - P:20 - M:15) ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que se añaden dos actividades en el mismo club dado un string valido")
    public void anyadir2Actividades_StringDatos_CheckToString() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 2;
        club = new ClubDeportivo(nombre, n);
        
        String[] datos1 = {"GrupoUno", "Yoga", "20", "15", "10"}; 
        club.anyadirActividad(datos1);

        String[] datos2 = {"GrupoDos", "Pilates", "40", "30", "15"};
        club.anyadirActividad(datos2);

        String expectedValue = nombre + " --> [ (GrupoUno - Yoga - 10.0 euros - P:20 - M:15), (GrupoDos - Pilates - 15.0 euros - P:40 - M:30) ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que si se intentan agregar dos actividades de grupos distintos con el limite de un grupo en el club lanza una excepcion")
    public void anyadirActividad_LimiteEsUno_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 1;
        club = new ClubDeportivo(nombre, n);
        
        String[] datos1 = {"GrupoUno", "Yoga", "20", "15", "10"}; 
        club.anyadirActividad(datos1);

        String[] datos2 = {"GrupoDos", "Pilates", "40", "30", "15"};

        assertThrows(ClubException.class,()-> club.anyadirActividad(datos2));
        
    }

    @Test
    @DisplayName("Comprueba que si se añade al mismo grupo la misma actividad se actualizan las plazas")
    public void anyadir2ActividadesIguales_MismoGrupo_CheckToString() throws ClubException{
        String nombre = "ClubDeportivo";
        int n = 2;
        club = new ClubDeportivo(nombre, n);
        
        String[] datos1 = {"GrupoUno", "Yoga", "20", "15", "10"}; 
        club.anyadirActividad(datos1);

        String[] datos2 = {"GrupoUno", "Yoga", "40", "30", "15"};
        club.anyadirActividad(datos2);

        int nplazas = Integer.parseInt(datos2[2]); 

        String expectedValue = nombre + " --> [ (GrupoUno - Yoga - 10.0 euros - P:" + nplazas + " - M:15) ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que se lanza un error debido a que no se insertan los datos de formato correcto")
    public void anyadirActividad_StringDatos_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
        String[] datos = {"GrupoUno", "Yoga", "Veinte", "15", "10.50"}; 

        assertThrows(ClubException.class,() -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Comprueba que se lanza un error debido a que se inserta un grupo nulo")
    public void anyadirActividad_NullGroup_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
         club = new ClubDeportivo(nombre, tam1);
        Grupo grupo = null;

        assertThrows(ClubException.class,() -> club.anyadirActividad(grupo));
    }

    @Test
    @DisplayName("Comprueba que se actualiza las plazas de un grupo ya existente")
    public void anyadirActividad_ExistingGroup_UpdatePlazas() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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
        club = new ClubDeportivo(nombre, tam);

        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo);

        String expectedValue = "ClubDeportivo --> [ (GrupoUno - Yoga - 10.0 euros - P:10 - M:5) ]";
        String returnValue = club.toString();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que se añade una actividad a un club dado un grupo de la misma")
    public void anyadirActividad_MasGruposQueTamClub_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam = 1;
        club = new ClubDeportivo(nombre, tam);

        String codigo = "GrupoUno";
        String codigo2 = "GrupoDos";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        Grupo grupo2 = new Grupo(codigo2, actividad, nplazas, metriculados, tarifa)
        
        club.anyadirActividad(grupo);
        assertThrows(ClubException.class, ()->club.anyadirActividad(grupo2));
    }

    @Test
    @DisplayName("Comprueba que se lanza un error debido a que se inserta una actividad nula")
    public void plazasLibres_NullActivity_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
         club = new ClubDeportivo(nombre, tam1);
         String actividad = null;

        assertThrows(ClubException.class,() -> club.plazasLibres(actividad));
    }

    @Test
    @DisplayName("Comprueba las plazas libres de una actividad cuando hay mas de un grupo de dicha actividad")
    public void plazasLibres_MoreThanOneGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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
    @DisplayName("Comprueba las plazas libres de una actividad cuando solo hay un grupo de dicha actividad")
    public void plazasLibres_OnlyOneGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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
    @DisplayName("Comprueba que si no existe dicha actividad en el club no hay plazas")
    public void plazasLibres_NoGroup_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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

    @Test
    @DisplayName("Comprueba que se lanza un error debido a que se inserta una actividad nula")
    public void matricular_NullActivity_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
         club = new ClubDeportivo(nombre, tam1);
         String actividad = null;
         int npersonas = 5;

        assertThrows(ClubException.class,() -> club.matricular(actividad, npersonas));
    }

    @Test
    @DisplayName("Comprueba que se lanza un error debido a que se intenta matriculas cero o menos personas")
    public void matricular_IncorrectPeople_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
         club = new ClubDeportivo(nombre, tam1);
         String actividad = "Yoga";
         int npersonas = 0;

        assertThrows(ClubException.class,() -> club.matricular(actividad, npersonas));
    }

    @Test
    @DisplayName("Comprueba que salta un error cuando se intenta matricular a más personas de las plazas libres que quedan en una actividad")
    public void matricular_LessPlazas_ThrowsError() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);
        
        int npersonas = 10;
        assertThrows(ClubException.class, () -> club.matricular(grupo1.getActividad(), npersonas));
    }

    @Test
    @DisplayName("Comprueba que se actualizan las matriculaciones de un grupo correctamente")
    public void matricular_CheckMatriculas() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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
    @DisplayName("Comprueba que se actualizan las matriculaciones de un grupo correctamente cuando hay mas de un grupo en el club")
    public void matricular_VariosGrupos_CheckMatriculas() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        String codigo2 = "GrupoDos";
        String actividad2 = "Pilates";
        int nplazas2 = 10;
        int matriculados2 =  5;
        double tarifa2 =  10;
        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);

        club.anyadirActividad(grupo2);

        int nuevos = 3;

        int expectedValue = matriculados+nuevos;
        club.matricular(grupo1.getActividad(), nuevos);
        int returnValue = grupo1.getMatriculados();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que en un club con 3 grupos de la misma actividad, se matricula correctamente en funcion de sus plazas libres en orden")
    public void matricular_MoreThanOneGroup_CheckMatriculaciones() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
        String codigo = "GrupoUno";
        String actividad = "Yoga";
        int nplazas = 10;
        int matriculados =  5;
        double tarifa =  10;
        Grupo grupo1 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        club.anyadirActividad(grupo1);

        String codigo2 = "GrupoDos";
        String actividad2 = "Yoga";
        int nplazas2 = 10;
        int matriculados2 =  5;
        double tarifa2 =  10;
        Grupo grupo2 = new Grupo(codigo2, actividad2, nplazas2, matriculados2, tarifa2);

        club.anyadirActividad(grupo2);

        String codigo3 = "GrupoTres";
        String actividad3 = "Yoga";
        int nplazas3 = 10;
        int matriculados3 =  5;
        double tarifa3 =  10;
        Grupo grupo3 = new Grupo(codigo3, actividad3, nplazas3, matriculados3, tarifa3);

        club.anyadirActividad(grupo3);

        int nuevos = 12;
        int expectedValue = 5+5+5+12;
        club.matricular(grupo1.getActividad(), nuevos);
        int returnValue = grupo3.getMatriculados()+grupo2.getMatriculados()+grupo1.getMatriculados();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que te devuelve correctamente los ingresos de un Club Deportivo")
    public void ingresos_Check() throws ClubException{
        String nombre = "ClubDeportivo";
        int tam1 = 3;
        club = new ClubDeportivo(nombre, tam1);
        
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
