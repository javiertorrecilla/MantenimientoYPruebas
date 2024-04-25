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
}
