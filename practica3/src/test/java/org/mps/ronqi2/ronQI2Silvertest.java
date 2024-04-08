//practica realizada por Javier Torrecilla Reyes y Sandra Vázquez Pérez

package org.mps.ronqi2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.dispositivo.Dispositivo;
import org.mps.dispositivo.DispositivoSilver;

@ExtendWith(MockitoExtension.class)
public class ronQI2SilverTest {

    RonQI2Silver ronqui2Silver;
    
    
    @Mock (lenient = true)
    DispositivoSilver dispositivoPrueba;
    
    @BeforeEach
    public void createNewRonQI2Silver(){
        ronqui2Silver = new RonQI2Silver();
        ronqui2Silver.disp = dispositivoPrueba;
    }
    
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    @Nested
    @DisplayName("Clase que comprueba todos los caminos bases para el metodo inicializar")
    class inicializarTest{

        @Test
        @DisplayName("Comprueba que cuando se inicializa de manera correcta, solo se llama una vez al configurar de cada sensor")
        public void inicializar_AllOk_VerifyConfigurar(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();

            assertTrue(result);
            verify(dispositivoPrueba, times(1)).configurarSensorPresion();
            verify(dispositivoPrueba, times(1)).configurarSensorSonido();
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se configura el Sensor del sonido")
        public void inicializar_NoConfiguradoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se configura el Sensor de la presion")
        public void inicializar_NoConfiguradoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se configura el Sensor ni del sonido ni de la presion")
        public void inicializar_NoConfigurados_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor del sonido")
        public void inicializar_NoConectadoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta ni configura el Sensor del sonido")
        public void inicializar_NoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor del sonido ni se configura el sensor de la presion")
        public void inicializar_NoConectSonidoNoConfigPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor del sonido ni se configura ningun sensor")
        public void inicializar_SoloConectadoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor de la presion")
        public void inicializar_NoConectadoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor de la presion ni se configura el Sensor del sonido")
        public void inicializar_NoConectPresionNoConfigSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta ni configura el Sensor de la presion")
        public void inicializar_NoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque solo se conecta el Sensor del sonido")
        public void inicializar_SoloConectadoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta ningun sensor")
        public void inicializar_SoloConfiguradosNoConectados_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque solo se ha configurado el Sensor de la presion")
        public void inicializar_SoloConfigPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque solo se ha configurado el Sensor del sonido")
        public void inicializar_SoloConfigSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta ni configura ningun Sensor")
        public void inicializar_AllFalse_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }


    }

    @Test
    @DisplayName("Comprueba que si añadimos un dispositivo, se actualiza la referencia en la clase RonQI2")
    public void anyadirDispositivo_checkEquals(){
        DispositivoSilver dispositivoNuevo = mock(DispositivoSilver.class);
        ronqui2Silver.anyadirDispositivo(dispositivoNuevo);

        DispositivoSilver expectedValue = dispositivoNuevo;
        Dispositivo returnedValue = ronqui2Silver.disp;

        assertEquals(expectedValue, returnedValue);
    }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */

    @Nested
    @DisplayName("Clase que comprueba todas las posibles opciones del metodo reconectar")
    class reconectarTest{

        @Test
        @DisplayName("Comprueba que si un dispositivo esta conectado, no se intenta reconectar")
        public void reconectar_DispositivoConectado_AssertFalse(){
            when(dispositivoPrueba.estaConectado()).thenReturn(true);

            boolean result = ronqui2Silver.reconectar();

            assertFalse(result);
            verify(dispositivoPrueba, times(0)).conectarSensorPresion();
            verify(dispositivoPrueba, times(0)).conectarSensorSonido();
        }

        @Test
        @DisplayName("Comprueba que un dispositivo no conectado, no reconecta porque no conecta con el sensor de presion")
        public void reconectar_DispositivoReconectadoNoConectarPresion_AssertFalse(){
            when(dispositivoPrueba.estaConectado()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.reconectar();

            assertFalse(result);
            verify(dispositivoPrueba).conectarSensorPresion();
            verify(dispositivoPrueba, times(0)).conectarSensorSonido();
        }

        @Test
        @DisplayName("Comprueba que un dispositivo no conectado, no reconecta porque no conecta con el sensor de sonido")
        public void reconectar_DispositivoReconectadoNoConectarSonido_AssertFalse(){
            when(dispositivoPrueba.estaConectado()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.reconectar();

            assertFalse(result);
            verify(dispositivoPrueba).conectarSensorPresion();
            verify(dispositivoPrueba).conectarSensorSonido();
        }

        @Test
        @DisplayName("Comprueba que un dispositivo no conectado, reconecta adecuadamente")
        public void reconectar_DispositivoReconectado_AssertTrue(){
            when(dispositivoPrueba.estaConectado()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);

            boolean result = ronqui2Silver.reconectar();

            assertTrue(result);
            verify(dispositivoPrueba).conectarSensorPresion();
            verify(dispositivoPrueba).conectarSensorSonido();
        }
    }
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     */

     //comprobamos primero el metodo obtenerNuevaLectura
    @Nested
    @DisplayName("Metodo que comprueba el metodo obtenerNuevaLectura")
    public class obtenerNuevaLecturaTest{
 /* 
        @Test
        @DisplayName("Comprueba que como inicialmente las listas de presion y sonido estan vacias, se añadira la presion y sonido del dispositivo (son menos de 5 lecturas)")
        public void obtenerNuevaLectura_menosDe5Lecturas_CheckAnianida(){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(22.0f);
            when(dispositivoPrueba.leerSensorSonido()).thenReturn(29.0f);

            ronqui2Silver.obtenerNuevaLectura();
            verify(ronqui2Silver, times(0)).
        }*/
    }
    
    @Nested
    @DisplayName("Clase que comprueba todas las opciones de la evaluación de apnea de sueño")
    public class evaluarApneaSuenyoTest{

        @Test 
        @DisplayName("Comprueba que si no hay ninguna lectura aun se añade a la lista y como la media de Presion es menor devuelve que no hay apnea")
        public void evaluarApneaSuenyo_averagePresionMenor_returnFalse(){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(15.0f);
            when(dispositivoPrueba.leerSensorSonido()).thenReturn(35.0f);
            ronqui2Silver.obtenerNuevaLectura();

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        @Test 
        @DisplayName("Comprueba que con una unica lectura la media del Sonido es menor por lo que no hay apnea")
        public void evaluarApneaSuenyo_averageSonidoMenor_returnFalse(){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(25.0f);
            when(dispositivoPrueba.leerSensorSonido()).thenReturn(25.0f);
            ronqui2Silver.obtenerNuevaLectura();

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        @Test 
        @DisplayName("Comprueba que con una unica lectura no hay apnea porque ni la Presion ni el Sonido superan el limite")
        public void evaluarApneaSuenyo_ageragePresionYSonidoMenor_returnFalse(){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(12.0f);
            when(dispositivoPrueba.leerSensorSonido()).thenReturn(27.0f);
            ronqui2Silver.obtenerNuevaLectura();

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        @Test 
        @DisplayName("Comprueba que con una unica lectura apnea porque la Presion y el Sonido superan el limite")
        public void evaluarApneaSuenyo_ageragePresionYSonidoMayor_returnTrue(){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(26.0f);
            when(dispositivoPrueba.leerSensorSonido()).thenReturn(34.0f);
            ronqui2Silver.obtenerNuevaLectura();

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertTrue(result);
        }

    }
     

     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */

     @Nested 
     @TestInstance(Lifecycle.PER_CLASS)
     @DisplayName("Test para el metodo evaluarApneaSuenyo con multiples llamadas a obtenerNuevaLectura()")
     public class evaluarApneaSuenyoTestMultiplesLlamadas{

        @ParameterizedTest
        @DisplayName("Comprueba que como ya habia 5 lecturas, al añadir otra borra la primera y ahora como la media de la Presion es menor devulve falso")
        @ValueSource(floats = {22.0f, 24.0f, 26.0f, 23.0f, 21.0f, 0.0f})
        public void evaluarApneaSuenyo_6LecturasMediaPresionMenor_returnFalse(float candidate){
            when(dispositivoPrueba.leerSensorPresion()).thenReturn(candidate);
            ronqui2Silver.obtenerNuevaLectura();

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        @ParameterizedTest
        @DisplayName("Comprueba que como hay 5 lecturas todas caben, y como la media de el Sonido es menor devuelve falso")
        @MethodSource("presionYSonidoProvider1")
        public void evaluarApneaSuenyo_5LecturasMediaSonidoMenor_returnFalse(List<Float> presiones, List<Float> sonidos){
            for (int i = 0; i < presiones.size(); i++) {
                when(dispositivoPrueba.leerSensorPresion()).thenReturn(presiones.get(i));
                when(dispositivoPrueba.leerSensorSonido()).thenReturn(sonidos.get(i));
                ronqui2Silver.obtenerNuevaLectura();
            }

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        Stream<Arguments> presionYSonidoProvider1() {
            List<Float> l1 = Arrays.asList(22.0f, 24.0f, 26.0f, 23.0f, 21.0f);
            List<Float> l2 = Arrays.asList(31.0f, 32.0f, 33.0f, 31.0f, 0.0f);
            return Stream.of(
                Arguments.of(l1, l2)
            );
        }

        @ParameterizedTest
        @DisplayName("Comprueba que como hay 10 lecturas se eliminan las 5 primeras, y como la media de la Presion y el Sonido es mayor a los limites devuelve true")
        @MethodSource("presionYSonidoProvider2")
        public void evaluarApneaSuenyo_10LecturasMediaPresionYSonidoMayor_returnTrue(List<Float> presiones, List<Float> sonidos){
            for (int i = 0; i < presiones.size(); i++) {
                when(dispositivoPrueba.leerSensorPresion()).thenReturn(presiones.get(i));
                when(dispositivoPrueba.leerSensorSonido()).thenReturn(sonidos.get(i));
                ronqui2Silver.obtenerNuevaLectura();
            }

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertTrue(result);
        }

        Stream<Arguments> presionYSonidoProvider2() {
            List<Float> l1 = Arrays.asList(0.0f,0.0f,0.0f,0.0f,0.0f,22.0f, 24.0f, 26.0f, 23.0f, 21.0f);
            List<Float> l2 = Arrays.asList(0.0f,0.0f,0.0f,0.0f,0.0f,31.0f, 32.0f, 33.0f, 31.0f, 33.0f);
            return Stream.of(
                Arguments.of(l1, l2)
            );
        }

        @ParameterizedTest
        @DisplayName("Comprueba que como hay 8 lecturas se eliminan las 3 primeras, y como la media de la Presion y el Sonido es menor a los limites devuelve false")
        @MethodSource("presionYSonidoProvider3")
        public void evaluarApneaSuenyo_10LecturasMediaPresionYSonidoMenor_returnFalse(List<Float> presiones, List<Float> sonidos){
            for (int i = 0; i < presiones.size(); i++) {
                when(dispositivoPrueba.leerSensorPresion()).thenReturn(presiones.get(i));
                when(dispositivoPrueba.leerSensorSonido()).thenReturn(sonidos.get(i));
                ronqui2Silver.obtenerNuevaLectura();
            }

            boolean result = ronqui2Silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        Stream<Arguments> presionYSonidoProvider3() {
            List<Float> l1 = Arrays.asList(0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f);
            List<Float> l2 = Arrays.asList(0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f);
            return Stream.of(
                Arguments.of(l1, l2)
            );
        }


     }

     @Nested
     @DisplayName("Tests para el metodo estaConectado()")
     public class estaConectadoTest{

        @Test
        @DisplayName("Comprueba que si el dispositivo esta conectado, el objeto Ronqi2 estara conectado")
        public void estaConectado_dispConectado_assertTrue(){
            when(dispositivoPrueba.estaConectado()).thenReturn(true);

            boolean result = ronqui2Silver.estaConectado();

            assertTrue(result);
        }

        @Test 
        @DisplayName("Comprueba que si el dispositivo no esta conectado, el objeto Ronqi2 tampoco")
        public void estaConectado_dispNoConectado_assertFalse(){
            when(dispositivoPrueba.estaConectado()).thenReturn(false);

            boolean result = ronqui2Silver.estaConectado();

            assertFalse(result);
        }        
     }
}
