package queue;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest<T> {

    private DoubleLinkedList<Object> node;

    @Test 
    @DisplayName("Es instanciado con una doble lista enlazada")
    public void isInstantiatedWithNew(){
        new DoubleLinkedListTest<>();
    }

    @BeforeEach
    public void createNewList(){
        node = new DoubleLinkedList<>();
    }

    @Nested 
    @DisplayName("Test metodo prepend")
    class prependTest{
        @Test
        @DisplayName("Comprueba que con la lista vacia, al añadir delante el primer nodo, el primero y el ultimo es el ultimo")
        public void prepend_EmptyList_FirstEqualsLast(){
            Object valor = new Object();
            node.prepend(valor);
            
            Object expectedValue1 = node.first();
            Object expectedValue2 = node.last();

            assertEquals(valor, expectedValue1);
            assertEquals(expectedValue1, expectedValue2);
        }

        @Test
        @DisplayName("Comprueba que si añadimos por delante dos nodos, el ultimo añadido sera el primero y el primero sera el ultimo")
        public void prepend_TwoNodes_LastIsFirst(){
            Object valor1 = new Object();
            node.prepend(valor1);
            Object valor2 = new Object();
            node.prepend(valor2);
            
            Object expectedValueFirst = valor2;
            Object returnValueFirst = node.first();
            Object expectedValueLast = valor1;
            Object returnValueLast = node.last();

            assertEquals(expectedValueFirst, returnValueFirst);
            assertEquals(expectedValueLast, returnValueLast);
        }
    }
    
    @Nested
    @DisplayName("Test para el metodo append")
    class appendTest{
        @Test
        @DisplayName("Comprueba que con la lista vacia, al añadir por detras el primer nodo, el primero y el ultimo es el ultimo")
        public void append_EmptyList_FirstEqualsLast(){
            Object valor = new Object();
            node.append(valor);
            
            Object expectedValue1 = node.first();
            Object expectedValue2 = node.last();

            assertEquals(valor, expectedValue1);
            assertEquals(expectedValue1, expectedValue2);
        }

        @Test
        @DisplayName("Comprueba que si añadimos por detras dos nodos, el ultimo añadido sera el ultimo y el primero sera el primero")
        public void prepend_TwoNodes_LastIsFirst(){
            Object valor1 = new Object();
            node.append(valor1);
            Object valor2 = new Object();
            node.append(valor2);
            
            Object expectedValueFirst = valor1;
            Object returnValueFirst = node.first();
            Object expectedValueLast = valor2;
            Object returnValueLast = node.last();

            assertEquals(expectedValueFirst, returnValueFirst);
            assertEquals(expectedValueLast, returnValueLast);
        }
    }

    
    @Nested 
    @DisplayName("Test para el metodo deleteFirst")
    class deleteFirstTest{

         @Test
         @DisplayName("Comprueba que al borrar el primer elemento de una lista vacia lanza un error")
         public void deleteFirst_EmptyList_ThrowsError(){
            assertThrows(DoubleLinkedQueueException.class,()-> node.deleteFirst());
         }

        @Test 
        @DisplayName("Comprueba que al borrar el primer elemento de una lista, si es el unico el primero y el ultimo tiene que ser nulo y lanza una excepcion")
        public void deleteFirst_OneNode_ThrowsError(){
            Object valor = new Object();
            node.append(valor);
            node.deleteFirst();

            assertThrows(DoubleLinkedQueueException.class,()-> node.first());
            assertThrows(DoubleLinkedQueueException.class,()-> node.last());
        }
        

        @Test 
        @DisplayName("Comprueba que al borrar el primer elemento de una lista con dos elementos, el primer elemento de la lista pasa a ser el segundo y como solo hay un elemento tambien sera el ultimo")
        public void deleteFirst_TwoNodes_FirstIsNotNull(){
            Object valor1 = new Object();
            node.append(valor1);
            Object valor2 = new Object();
            node.append(valor2);
            node.deleteFirst();

            Object returnedValue = node.first();
            Object expectedValue = valor2;
            Object returnedValue2 = node.last();

            assertEquals(returnedValue, expectedValue);
            assertEquals(returnedValue2, expectedValue);
        }
    }

    
    @Nested 
    @DisplayName("Test para el metodo deleteLast")
    public class deleteLastTest{

        @Test
        @DisplayName("Comprueba que al borrar el ultimo elemento de una lista vacia lanza un error")
        public void deleteLast_EmptyList_ThrowsError(){
           assertThrows(DoubleLinkedQueueException.class,()-> node.deleteLast());
        }

        @Test 
        @DisplayName("Comprueba que al borrar el ultimo elemento de una lista, si es el unico el primero y el ultimo tiene que ser nulo y lanza una excepcion")
        public void deleteLast_OneNode_ThrowsError(){
            Object valor = new Object();
            node.append(valor);
            node.deleteLast();

            assertThrows(DoubleLinkedQueueException.class,()-> node.first());
            assertThrows(DoubleLinkedQueueException.class,()-> node.last());
        }

        @Test 
        @DisplayName("Comprueba que al borrar el ultimo elemento de una lista con dos elementos, el primer elemento de la lista seguira siendo el primero y como solo hay un elemento tambien sera el ultimo")
        public void deleteFirst_TwoNodes_FirstIsNotNull(){
            Object valor1 = new Object();
            node.append(valor1);
            Object valor2 = new Object();
            node.append(valor2);

            node.deleteLast();

            Object returnedValue = node.first();
            Object expectedValue = valor1;
            Object returnedValue2 = node.last();

            assertEquals(returnedValue, expectedValue);
            assertEquals(returnedValue2, expectedValue);
        }
    }
    
    @Nested 
    @DisplayName("Test para el metodo first")
    public class firstTest{
        @Test
        @DisplayName("Comprueba que first() lanza una excepcion si la lista esta vacia")
        public void first_EmptyList_ThrowsError(){
            assertThrows(DoubleLinkedQueueException.class, ()->node.first());
        }

        @Test
        @DisplayName("Comprueba que first() devuelve el primer elemento correctamente")
        public void first_TwoNodes_CheckFirst(){
            Object valor1 = new Object();
            Object valor2 = new Object();

            node.append(valor1);
            node.append(valor2);

            Object expectedValue = valor1;
            Object returnValue = node.first();

            assertEquals(expectedValue, returnValue);
        }
    }
    

    @Nested
    @DisplayName("Test para el metodo last")
    public class lastTest{
        @Test
        @DisplayName("Comprueba que last() lanza una excepcion si la lista esta vacia")
        public void last_EmptyList_ThrowsError(){
            assertThrows(DoubleLinkedQueueException.class,()-> node.last());
        }

        @Test
        @DisplayName("Comprueba que last() devuelve el ultimo elemento correctamente")
        public void last_TwoNodes_CheckLast(){
            Object valor1 = new Object();
            Object valor2 = new Object();

            node.append(valor1);
            node.append(valor2);

            Object expectedValue = valor2;
            Object returnValue = node.last();

            assertEquals(expectedValue, returnValue);
        }
    }
    
    @Nested
    @DisplayName("Test para el metodo size")
    public class sizeTest{

        @Test 
        @DisplayName("Comprueba que size() devuelve cero si la lista esta vacia")
        public void size_EmptyList_ReturnZero(){
            int expectedValue = 0;
            int returnValue = node.size();

            assertEquals(expectedValue, returnValue);
        }

        @Test 
        @DisplayName("Comprueba que size() devuelve uno si la lista tiene un elemento")
        public void size_OneNode_ReturnOne(){
            Object valor = new Object();
            node.append(valor);

            int expectedValue = 1;
            int returnedValue = node.size();

            assertEquals(returnedValue, expectedValue);
        }
    }

}
