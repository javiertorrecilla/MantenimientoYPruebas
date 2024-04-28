package org.mps.boundedqueue;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ArrayBoundedQueueTest {

    ArrayBoundedQueue arrayBoundedQueue;
    
    @Test
    public void ArrayBoundedQueue_capacityMenorIgualCero_ThrowsError(){
        int capacity = 0;
        String message = "ArrayBoundedException: capacity must be positive";
        assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(()->{
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
        })
        .withMessage(message);
    }

    @Nested
    @DisplayName("Tests para la clase get")
    class get{
        
        @Test
        @DisplayName("Comprueba que si un array es vacío, si hacemos un get da error")
        public void get_isEmpty_ThrowsError(){
            int capacity = 0;
            String message = "get: empty bounded queue";
    
            assertThatExceptionOfType(EmptyBoundedQueueException.class)
            .isThrownBy(()->{
                arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            })
            .withMessage(message);
        }
    
        @Test
        @DisplayName("Test que comprueba que cuando se aplica un get se actualizan los primeros y últimos elementos de la lista, además el tamaño cambia y se devuelve el numero correcto")
        public void get_Check(){
            int capacity = 3;
            int expectedFirst = 1;
            int expectedLast = 0;
            String expectedeElement = "0";
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            int expectedSize = arrayBoundedQueue.size()-1;
    
            String returnElement = arrayBoundedQueue.get();
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            int returnSize = arrayBoundedQueue.size();
    
            assertThat(returnLast)
            .isEqualTo(expectedLast);
    
            assertThat(returnFirst)
            .isEqualTo(expectedFirst);
    
            assertThat(returnElement)
            .isEqualTo(expectedeElement);
    
            assertThat(returnSize)
            .isEqualTo(expectedSize);
        }
    
        @Test
        @DisplayName("Test que comprueba la circularidad de la lista con un get")
        public void get_Circularidad_Check(){
            int capacity = 3;
            int expectedFirst = 0;
            int expectedLast = 1;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
            arrayBoundedQueue.put("3");
            arrayBoundedQueue.get();
    
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            boolean vacia = arrayBoundedQueue.isEmpty();
    
            assertThat(returnFirst)
            .isEqualTo(expectedFirst);
            
            assertThat(returnLast)
            .isEqualTo(expectedLast);
    
            assertThat(vacia).isFalse();
        }
    
        @Test
        @DisplayName("Comprueba que si se eliminan todos los elementos de una lista, first y last son 0 y está vacía")
        public void get_ArrayBecomeEmpty_Check(){
            int capacity = 3;
            int expectedFirstAndLast = 0;
            boolean vacia;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
    
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            vacia = arrayBoundedQueue.isEmpty();
    
            assertThat(expectedFirstAndLast)
            .isEqualTo(returnFirst)
            .isEqualTo(returnLast);
    
            assertThat(vacia).isTrue();
    
        }

    }

    @Nested
    @DisplayName("Tests para la clase put")
    class put{

        @Test
        @DisplayName("Comprueba que si un array está lleno, si hacemos un put da error")
        public void put_isFull_ThrowsError(){
            int capacity = 2;
            String message = "put: full bounded queue";
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");

            assertThatExceptionOfType(FullBoundedQueueException.class)
            .isThrownBy(()->{
                arrayBoundedQueue.put("2");
            })
            .withMessage(message);
        }

        @Test
        @DisplayName("Comprueba que si un array está lleno, si hacemos un put da error")
        public void put_ElementNull_ThrowsError(){
            int capacity = 2;
            String message = "put: full bounded queue";
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            assertThatExceptionOfType(FullBoundedQueueException.class)
            .isThrownBy(()->{
                arrayBoundedQueue.put(null);
            })
            .withMessage(message);
        }

        @DisplayName("Test que comprueba que cuando se aplica un put se actualizan los primeros y últimos elementos de la lista, el tamaño cambia y no está")
        public void put_Check(){
            int capacity = 3;
            int expectedFirst = 0;
            int expectedLast = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            int expectedSize = arrayBoundedQueue.size()+2;
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
    
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            int returnSize = arrayBoundedQueue.size();
            boolean vacia = arrayBoundedQueue.isEmpty();
    
            assertThat(returnLast)
            .isEqualTo(expectedLast);
    
            assertThat(returnFirst)
            .isEqualTo(expectedFirst);
    
            assertThat(returnSize)
            .isEqualTo(expectedSize);

            assertThat(vacia).isFalse();
        }
    
        @Test
        @DisplayName("Test que comprueba la circularidad de la lista con un put")
        public void put_Circularidad_Check(){
            int capacity = 3;
            int expectedFirst = 1;
            int expectedLast = 0;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();

    
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            boolean vacia = arrayBoundedQueue.isEmpty();
    
            assertThat(returnFirst)
            .isEqualTo(expectedFirst);
            
            assertThat(returnLast)
            .isEqualTo(expectedLast);
    
            assertThat(vacia).isFalse();
        }
    
        @Test
        @DisplayName("Comprueba que si se introducen todos los elementos de una lista, first y last son la capacidad de la lista y está llena")
        public void put_ArrayBecomeEmpty_Check(){
            int capacity = 3;
            int expectedFirstAndLast = 3;
            boolean llena;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
    
            int returnFirst = arrayBoundedQueue.getFirst();
            int returnLast = arrayBoundedQueue.getLast();
            llena = arrayBoundedQueue.isFull();
    
            assertThat(expectedFirstAndLast)
            .isEqualTo(returnFirst)
            .isEqualTo(returnLast);
    
            assertThat(llena).isTrue();
    
        }

    }

}

