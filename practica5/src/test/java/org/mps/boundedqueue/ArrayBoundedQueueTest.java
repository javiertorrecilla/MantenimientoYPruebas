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

    @Test
    @DisplayName("Comprueba que ")
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
    @DisplayName("Test que comprueba que cuando se aplica un get se obtiene el primer elemento de la lista y se regula el tamaño y el puntero first y last")
    public void get_First_Check(){
        int capacity = 3;
        int expectedFirst = 1;
        int expectedLast = 2;
        String expectedeElement = "0";
        int expectedSize = arrayBoundedQueue.size()-1;
        arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
        arrayBoundedQueue.put("0");
        arrayBoundedQueue.put("1");
        arrayBoundedQueue.put("2");

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
    @DisplayName("Test que comprueba que cuando se aplica un get se obtiene el primer elemento de la lista")
    public void get_Circularidad_Check(){
        int capacity = 3;
        arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
        arrayBoundedQueue.put("0");
        arrayBoundedQueue.put("1");
        arrayBoundedQueue.put("2");
        arrayBoundedQueue.get();
        arrayBoundedQueue.get();
        arrayBoundedQueue.put("3");
        arrayBoundedQueue.get();

        int expectedFirst = 0;
        int expectedLast = 1;
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

