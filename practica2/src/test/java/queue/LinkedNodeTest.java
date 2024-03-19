package queueTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTest {

    LinkedNode<T> ln;
    LinkedNode<T> pre;
    LinkedNode<T> next;

    @BeforeEach
    @DisplayName("Se crea un linked node antes de cada test")
    public void linkedNodeCreate(){
        pre = new LinkedNode(8, null, ln);
        next = new LinkedNode(12, ln, null);
        ln = new LinkedNode(10, pre, next);
    }

    @Test
    @DisplayName("Se comprueba que el metodo getItem se hace correctamente")
    public void getItem_Check(){
        T expectedValue = 10;
        T returnValue = ln.getItem();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Se comprueba que el metodo setItem se hace correctamente")
    public void setItem_Check(){
        T newItem = 15;
        ln.setItem(newItem);
        T expectedValue = newItem;
        T returnValue = ln.getItem();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Se comprueba que el metodo getPrevious se hace correctamente")
    public void getPrevious_Check(){
        LinkedNode<T> expectedValue = pre;
        LinkedNode<T> returnValue = ln.getPrevious();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Se comprueba que el metodo setItem se hace correctamente")
    public void setPrevious_Check(){
        LinkedNode<T> newPrevious = new LinkedNode(9,null,null);
        ln.setPrevious(newPrevious);
        LinkedNode<T> expectedValue = newPrevious;
        LinkedNode<T> returnValue = ln.getPrevious();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Se comprueba que el metodo getNext se hace correctamente")
    public void getNext_Check(){
        LinkedNode<T> expectedValue = next;
        LinkedNode<T> returnValue = ln.getNext();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Se comprueba que el metodo setNext se hace correctamente")
    public void setNext_Check(){
        LinkedNode<T> newNext = new LinkedNode(11,null,null);
        ln.setPrevious(newNext);
        LinkedNode<T> expectedValue = newNext;
        LinkedNode<T> returnValue = ln.getPrevious();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo inicial, el metodo isFirstNode es verdadero")
    public void isFirstNode_returnsTrue(){
        boolean returnValue = pre.isFirstNode();
        assertTrue(returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo no inicial, el metodo isFirstNode es falso")
    public void isFirstNode_returnsFalse(){
        boolean returnValue = ln.isFirstNode();
        assertFalse(returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo final, el metodo isLastNode es verdadero")
    public void isLastNode_returnsTrue(){
        boolean returnValue = next.isLastNode();
        assertTrue(returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo no final, el metodo isLastNode es falso")
    public void isLastNode_returnsFalse(){
        boolean returnValue = ln.isLastNode();
        assertFalse(returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo no terminal, el metodo iNotATerminalNode es verdadero")
    public void isNotATerminalNode_returnsTrue(){
        boolean returnValue = ln.isNotATerminalNode();
        assertTrue(returnValue);
    }

    @Test
    @DisplayName("Comprueba que para un nodo terminal, el metodo isNotATerminalNode es falso")
    public void isNotATerminalNode_returnsFalse(){
        boolean returnValue = pre.isNotATerminalNode();
        assertFalse(returnValue);
    }

}
