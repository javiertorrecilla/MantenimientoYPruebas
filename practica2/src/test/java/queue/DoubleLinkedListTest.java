package queue;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest<T> {

    private DoubleLinkedList<Object> node = new DoubleLinkedList<>();
    
    @Test
    @DisplayName("Comprueba que con la lista vacia, al añadir delante el primer nodo ya no es nulo")
    public void prepend_EmptyList_ReturnFirstNoNull(){
        Object valor = new Object();
        node.prepend(valor);
        
        Object returnValue = node.first();
        assertNotNull(returnValue);
    }

    @Test
    @DisplayName("Comprueba que con la lista vacia, al añadir delante el ultimo nodo ya no es nulo")
    public void prepend_EmptyList_ReturnLastNoNull(){
        Object valor = new Object();
        node.prepend(valor);
        
        Object returnValue = node.last();
        assertNotNull(returnValue);
    }

    @Test
    @DisplayName("Comprueba que si añadimos por delante dos nodos, el ultimo añadido sera el primero")
    public void prepend_TwoNodes_LastIsFirst(){
        Object valor1 = new Object();
        node.prepend(valor1);

        Object valor2 = new Object();
        node.prepend(valor2);
        
        Object expectedValue = valor2;
        Object returnValue = node.first();

        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que al añadir al final a la lista vacia, el primero y el ultimo son iguales")
    public void append_EmptyList_FirstAndLastEquals(){
        Object valor = new Object();
        node.append(valor);

        assertEquals(node.first(), node.last());
    }

    @Test 
    @DisplayName("Comprueba que al añadir dos elementos con append, el ultimo añadido sera el ultimo en la lista")
    public void append_TwoNodes_Last(){
        Object valor1 = new Object();
        Object valor2 = new Object();

        node.append(valor1);
        node.append(valor2);

        Object expectedValue = valor2;
        Object returnValue = node.last();

        assertEquals(expectedValue, returnValue);
    }

    @Test 
    @DisplayName("Comprueba que al borrar el primer elemento de una lista, si es el unico el ultimo tiene que ser nulo")
    public void deleteFirst_OneNode_LastIsNull(){
        Object valor = new Object();
        node.append(valor);

        node.deleteFirst();

        Object returnValue = node.last();
        assertNull(returnValue);
    }

    @Test 
    @DisplayName("Comprueba que al borrar el primer elemento de una lista con dos elementos, el primero no puede ser nulo")
    public void deleteFirst_TwoNodes_FirstIsNotNull(){
        Object valor1 = new Object();
        node.append(valor1);
        Object valor2 = new Object();
        node.append(valor2);

        node.deleteFirst();

        Object returnValue = node.first();
        assertNotNull(returnValue);
    }

    @Test 
    @DisplayName("Comprueba que al borrar el ultimo elemento de una lista, si es el unico el primero tiene que ser nulo")
    public void deleteLast_OneNode_FirstIsNull(){
        Object valor = new Object();
        node.append(valor);

        node.deleteLast();

        Object returnValue = node.first();
        assertNull(returnValue);
    }

    @Test 
    @DisplayName("Comprueba que al borrar el ultimo elemento de una lista con dos elementos, el ultimo no puede ser nulo")
    public void deleteLast_TwoNodes_LastIsNotNull(){
        Object valor1 = new Object();
        node.append(valor1);
        Object valor2 = new Object();
        node.append(valor2);

        node.deleteLast();

        Object returnValue = node.last();
        assertNotNull(returnValue);
    }

    @Test
    @DisplayName("Comprueba que first() devuelve nulo si la lista esta vacia")
    public void first_EmptyList_CheckNull(){
        Object expectedValue = null;
        Object returnValue = node.first();

        assertEquals(expectedValue, returnValue);
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

    @Test
    @DisplayName("Comprueba que last() devuelve nulo si la lista esta vacia")
    public void last_EmptyList_CheckNull(){
        Object expectedValue = null;
        Object returnValue = node.last();

        assertEquals(expectedValue, returnValue);
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

    @Test 
    @DisplayName("Comprueba que size() devuelve correctamente el tamaño actual de la lista")
    public void size_EmptyList_ReturnZero(){
        int expectedValue = 0;
        int returnValue = node.size();

        assertEquals(expectedValue, returnValue);
    }

}
