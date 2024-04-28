//practica realizada por Sandra Vázquez Pérez y Javier Torrecilla Reyes

package org.mps.boundedqueue;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ArrayBoundedQueueTest {

    ArrayBoundedQueue<String> arrayBoundedQueue;

    @Nested 
    @DisplayName("Tests para el constructor")
    public class arrayBoundedQueue{
        @Test
        @DisplayName("Test que comprueba que si la capacidad que se le pasa a la cola es menor o igual a cero lanza una excepcion")
        public void ArrayBoundedQueue_capacityCero_ThrowsError(){
            int capacity = 0;
            String message = "ArrayBoundedException: capacity must be positive";
            assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(()->{
                arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            })
            .withMessage(message);
        }

        @Test
        @DisplayName("Test que comprueba que si la capacidad que se le pasa a la cola es menor o igual a cero lanza una excepcion")
        public void ArrayBoundedQueue_capacityMenosUno_ThrowsError(){
            int capacity = -1;
            String message = "ArrayBoundedException: capacity must be positive";
            assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(()->{
                arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            })
            .withMessage(message);
        }
    
        @Test 
        @DisplayName("Test que comprueba que cuando se crea la cola con capacidad correcta no lanza una excepcion")
        public void ArrayBoundedQueue_correctCapacity_NotThrowsError(){
            int capacity = 2;
    
            assertThatCode(()-> new ArrayBoundedQueue<>(capacity)).doesNotThrowAnyException();
        }
        
        @Test
        @DisplayName("Test que comprueba que si creas una cola con capacidad 2 sin elementos esta vacia")
        public void ArrayBoundedQueue_ZeroElements_CheckIsEmpty(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            boolean isEmpty = arrayBoundedQueue.isEmpty();

            assertThat(isEmpty).isTrue();
        }

        @Test
        @DisplayName("Test que comprueba que al crear la cola no esta llena")
        public void ArrayBoundedQueue_ZeroElements_isNotFull(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            boolean isFull = arrayBoundedQueue.isFull();

            assertThat(isFull).isFalse();
        }

        @Test
        @DisplayName("Test que comprueba que si creas una cola el tamaño es cero")
        public void ArrayBoundedQueue_ZeroElements_SizeZero(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            int size = arrayBoundedQueue.size();

            assertThat(size).isEqualTo(0);
        }

        @Test
        @DisplayName("Test que comprueba que si creas una cola, el metodo getFirst devolvera cero ya que señala la primera posicion")
        public void ArrayBoundedQueue_ZeroElements_FirstIsZero(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            int getFirst = arrayBoundedQueue.getFirst();

            assertThat(getFirst).isEqualTo(0);
        }
        
        @Test
        @DisplayName("Test que comprueba que si creas una cola, el metodo getLast devolvera cero ya que es la primera posiciion libre del buffer")
        public void ArrayBoundedQueue_ZeroElements_LastIsZero(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            int getLast = arrayBoundedQueue.getLast();

            assertThat(getLast).isEqualTo(0);
        }
    }

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
    @DisplayName("Tests para la clase put")
    public class putTest{

        
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
        @DisplayName("Comprueba que si se le intenta introducir en la lista un valor nulo salta una excepcion")
        public void put_ElementNull_ThrowsError(){
            int capacity = 2;
            String message = "put: element cannot be null";
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(()->{
                arrayBoundedQueue.put(null);
            })
            .withMessage(message);
        }

        @Test
        @DisplayName("Test que comprueba que una cola vacia el siguiente libre es el primero")
        public void put_EmptyQueue_getLast(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(0);
        }

        @Test
        @DisplayName("Test que comprueba que una cola vacia el primer elemento apunta a la posicion cero")
        public void put_EmptyQueue_getFirst(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(0);

        }

        @Test
        @DisplayName("Test que comprueba que inicialmente una cola esta vacia")
        public void put_EmptyQueue_isEmpty(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            assertThat(arrayBoundedQueue.isEmpty())
            .isTrue();
        }

        @Test
        @DisplayName("Test que comprueba que cuando se aplica un put el primer elemento de la cola continua siendo la posicion cero")
        public void put_OneElement_getFirst(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");

            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(0);
        }

        @Test
        @DisplayName("Test que comprueba que cuando se realiza un put, la siguiente posicion libre sera la uno, ya que la cero ya esta ocupada")
        public void put_OneElement_getLast(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");

            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(1);
        }

        @Test
        @DisplayName("Test que comprueba que cuando se realiza un put el tamaño cambia")
        public void put_OneElement_setSize(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(1);
        }

        @Test
        @DisplayName("Test que comprueba que cuando se realiza un put la cola ya no esta vacia")
        public void put_OneElement_queueNotEmpty(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");

            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();
        }
    
        @Test
        @DisplayName("Test que comprueba que si se llena la cola y se realiza un get, la siguiente posicion libre es la cero y comprueba que ahora la cola no esta llena ni vacia")
        public void put_Circularidad_Check(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
    
            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(1);
            
            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(0);

            assertThat(arrayBoundedQueue.isFull())
            .isFalse();
    
            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(2);
        }

        @Test
        @DisplayName("Test que comprueba que si se introducen 3 elementos con capacidad 4, si se hace un get su primer elemento pasara a ser la posicion uno pero la posicion libre siguiente sera la 3 (la ultima)")
        public void put_Capacity4_CheckFirstAndLast(){
            int capacity = 4;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
    

            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(1);
            
            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(capacity-1);

            assertThat(arrayBoundedQueue.isFull())
            .isFalse();
    
            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(2);
        }
    
        @Test
        @DisplayName("Comprueba que si se llena la cola, la siguiente posicion libre es la cero y que ademas la cola esta llena y no vacia")
        public void put_ArrayBecomeEmpty_Check(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
    
    
            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(0);
    
            assertThat(arrayBoundedQueue.isFull())
            .isTrue();

            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(capacity);
        }

    }

    @Nested
    @DisplayName("Tests para el metodo get")
    class get{
        
        @Test
        @DisplayName("Comprueba que si la cola esta vacia si hacemos un get se lanza una excepcion")
        public void get_isEmpty_ThrowsError(){
            int capacity = 2;
            String message = "get: empty bounded queue";
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
    
            assertThatExceptionOfType(EmptyBoundedQueueException.class)
            .isThrownBy(()->{
                arrayBoundedQueue.get();
            })
            .withMessage(message);
        }

        @Test 
        @DisplayName("Test que comprueba que si se hace un get del unico elemento que habia en la cola con capacidad 1, tanto la primera posicion como la siguiente libre es la cero y ademas el tamaño vuelve a ser cero")
        public void get_CapacityOne_FirstAndLastEquals(){
            int capacity = 1;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.get();


            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(0);
    
            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(0);

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(0);

            assertThat(arrayBoundedQueue.isEmpty())
            .isTrue();

            assertThat(arrayBoundedQueue.isFull())
            .isFalse();
        }
    
        @Test
        @DisplayName("Test que comprueba que cuando se realiza un get despues de introducir 3 elementos con capacidad 4, la primera posicion se actualiza, además el tamaño cambia y se devuelve el valor correcto")
        public void get_Check(){
            int capacity = 4;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
    

            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(1);
    
            assertThat(arrayBoundedQueue.size())
            .isEqualTo(2);

            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();

            assertThat(arrayBoundedQueue.isFull())
            .isFalse();
        }
    
        @Test
        @DisplayName("Test que comprueba si se introducen tres elementos en una cola de capacidad 3 y se sacan dos, el primer elemento esta en la ultima posicion de la cola y la siguiente posicion disponible sera la cero")
        public void get_Circularidad_Check(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
    
    
            assertThat(arrayBoundedQueue.getFirst())
            .isEqualTo(2);
            
            assertThat(arrayBoundedQueue.getLast())
            .isEqualTo(0);
    
            assertThat(arrayBoundedQueue.isEmpty())
            .isFalse();

            assertThat(arrayBoundedQueue.isFull())
            .isFalse();

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(1);
        }
    
        @Test
        @DisplayName("Comprueba que si se eliminan todos los elementos de una cola, donde apunta el primer elemento y la siguiente posicion libre sera la cero (la misma) y que además la cola esta vacia")
        public void get_ArrayBecomeEmpty_Check(){
            int capacity = 3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();
            arrayBoundedQueue.get();

    
            assertThat(0)
            .isEqualTo(arrayBoundedQueue.getFirst())
            .isEqualTo(arrayBoundedQueue.getLast());
    
            assertThat(arrayBoundedQueue.isEmpty())
            .isTrue();
    
            assertThat(arrayBoundedQueue.isFull())
            .isFalse();

            assertThat(arrayBoundedQueue.size())
            .isEqualTo(0);
        }

    }

    
    @Nested
    @DisplayName("Tests para el metodo iteratos")
    public class iteratorTest{
        
        @Test
        @DisplayName("Test que comprueba que en una cola vacia no se tiene siguiente y ademas se lanza una expcepcion si se llama al siguiente")
        public void iterator_emptyQueue_assertThrows(){
            int capacity = 2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            String message = "next: bounded queue iterator exhausted";

            Iterator<String> iterator = arrayBoundedQueue.iterator();

            assertThat(iterator.hasNext())
            .isFalse();

            assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(()->{
                iterator.next();
            })
            .withMessage(message);
        }


        @Test 
        @DisplayName("Test que comprueba que una cola con un elemento tiene siguiente y es el elemento que se ha realizado el put")
        public void iterator_oneElement_hasNext(){
            int capacity=2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            Iterator<String> iterator = arrayBoundedQueue.iterator();
            String value = "2";

            arrayBoundedQueue.put(value);

            assertThat(iterator.hasNext())
            .isTrue();

            assertThat(iterator.next())
            .isEqualTo(value);
        }

        @Test 
        @DisplayName("Test que comprueba que si se le realiza get al unico elemento de la cola no tiene siguiente en el iterador y que ademas si se ejecuta el siguiente lanza una excepcion")
        public void iterator_oneElementAndGet_AssertThrows(){
            int capacity=2;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            String message = "next: bounded queue iterator exhausted";
            Iterator<String> iterator = arrayBoundedQueue.iterator();
            String value = "2";

            arrayBoundedQueue.put(value);
            arrayBoundedQueue.get();


            assertThat(iterator.hasNext())
            .isFalse();

            assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(()->{
                iterator.next();
            })
            .withMessage(message);
        }

        @Test 
        @DisplayName("Test que comprueba que el next del iterador avanza correctamente")
        public void iterator_moreThanOneElement_checkNextIterator(){
            int capacity=3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            Iterator<String> iterator = arrayBoundedQueue.iterator();

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            iterator.next();

            assertThat(iterator.hasNext())
            .isTrue();

            assertThat(iterator.next())
            .isEqualTo("1");
        }

        @Test 
        @DisplayName("Test que comprueba que si se ha llegado al final de la cola con el iterador, si se vuelve a ejecutar next lanza una excepcion")
        public void iterator_lastPosition_assertThrows(){
            int capacity=3;
            arrayBoundedQueue = new ArrayBoundedQueue<>(capacity);
            String message = "next: bounded queue iterator exhausted";
            Iterator<String> iterator = arrayBoundedQueue.iterator();

            arrayBoundedQueue.put("0");
            arrayBoundedQueue.put("1");
            arrayBoundedQueue.put("2");
            iterator.next();
            iterator.next();
            iterator.next();

            assertThat(iterator.hasNext())
            .isFalse();

            assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(()->{
                iterator.next();
            })
            .withMessage(message);
        }
    }
    
}
