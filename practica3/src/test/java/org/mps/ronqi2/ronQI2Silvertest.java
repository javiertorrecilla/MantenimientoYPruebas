package org.mps.ronqi2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.dispositivo.Dispositivo;
import org.mps.dispositivo.DispositivoSilver;

@ExtendWith(MockitoExtension.class)
public class ronQI2Silvertest {

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
        @DisplayName("Comprueba que se inicializa de manera incorrecta porque no se conecta el Sensor a la Presion")
        public void inicializar_NoConfiguradoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConfiguradoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConfigurados_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConectadoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConectSonidoNoConfigPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_SoloConectadoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConectadoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoConectPresionNoConfigSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_NoPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_SoloConectadoSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_SoloConfiguradosNoConectados_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_SoloConfigPresion_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(true);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_SoloConfigSonido_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(true);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }

        @Test
        @DisplayName("Comprueba que se inicializa de manera correcta")
        public void inicializar_AllFalse_AssertFalse(){
            when(dispositivoPrueba.conectarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.conectarSensorSonido()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorPresion()).thenReturn(false);
            when(dispositivoPrueba.configurarSensorSonido()).thenReturn(false);

            boolean result = ronqui2Silver.inicializar();
            
            assertFalse(result);
        }


    }


    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}

