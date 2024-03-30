//Realizado por Javier Torrecilla Reyes y Sandra Vázquez Pérez

package queue;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LinkedNodeTest {

    private Object item;
    private LinkedNode<Object> node;
    private LinkedNode<Object> previous;
    private LinkedNode<Object> next;

    @BeforeEach
    public void init(){
        item = new Object();
        previous = null;
        next = null;
        node = new LinkedNode<Object>(item, previous, next);
    }

    @Nested
    @DisplayName("Tests para metodos Item")
    class items{

        @Test
        @DisplayName("Se comprueba que el metodo getItem te devuelve el item correctamente")
        public void getItem_Check(){
            Object expectedValue = item;
            Object returnValue = node.getItem();

            assertEquals(expectedValue, returnValue);
        }
    
        @Test
        @DisplayName("Se comprueba que el metodo setItem modifica el Item correctamente")
        public void setItem_Check(){
            Object newItem = new Object();
            node.setItem(newItem);

            Object expectedValue = newItem;
            Object returnValue = node.getItem();

            assertEquals(expectedValue, returnValue);
        }

    }

    @Nested
    @DisplayName("Tests para metodos Previous")
    class previous{
        
        @Test
        @DisplayName("Se comprueba que el metodo getPrevious devuelve null si hay un unico nodo")
        public void getPrevious_UnUnicoNodo_CheckNull(){
            //inicialmente solo hay un nodo, el anterior es nulo
            Object expectedValue = null;
            Object returnValue = node.getPrevious();

            assertEquals(expectedValue, returnValue);
        }
    
        @Test
        @DisplayName("Se comprueba que el metodo setItem modifica el Item previo correctamente")
        public void setPrevious_Check(){
            Object pre = new Object();
            LinkedNode<Object> previo = new LinkedNode<>(pre, null, node);
            node.setPrevious(previo);
    
            Object expectedValue = pre;
            Object returnValue = node.getPrevious().getItem(); //se compara con el objeto item

            assertEquals(expectedValue, returnValue);
        }

    }

    @Nested
    @DisplayName("Tests para metodos Next")
    class next{

        @Test
        @DisplayName("Se comprueba que el metodo getNext como no tiene siguiente devolvera nulo")
        public void getNext_NoHaySiguiente_CheckNull(){
            Object expectedValue = null;
            Object returnValue = node.getNext();

            assertEquals(expectedValue, returnValue);
        }
    
        @Test
        @DisplayName("Se comprueba que el metodo setNext modifica correctamente el Nodo siguiente")
        public void setNext_Check(){
            Object post = new Object();
            LinkedNode<Object> next = new LinkedNode<Object>(post, node, null);
            node.setNext(next);
            
            Object expectedValue = next;
            Object returnValue = node.getNext();

            assertEquals(expectedValue, returnValue);
        }

    }

    @Nested 
    @DisplayName("Test para el metodo isFirstNode")
    class isFirstNodeTest{
        
        @Test
        @DisplayName("Comprueba que como solo hay un nodo es el inicial")
        public void isFirstNode_OneNode_returnsTrue(){
            boolean returnValue = node.isFirstNode();

            assertTrue(returnValue);
        }

        @Test
        @DisplayName("Comprueba que si añado un nodo al final no es inicial")
        public void isFirstNode_MoreThanOneNode_returnsFalse(){
            Object post = new Object();
            LinkedNode<Object> next = new LinkedNode(post, node, null);
            node.setNext(next);

            boolean returnValue = next.isFirstNode();

            assertFalse(returnValue);
        }

    }


    @Nested 
    @DisplayName("Test para el metodo isLastNode")
    class isLastNodeTest{
        
        @Test
        @DisplayName("Comprueba que como solo hay un nodo, es el final")
        public void isLastNode_returnsTrue(){
            boolean returnValue = node.isLastNode();

            assertTrue(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el primer nodo linkeado no es final")
        public void isFirstNode_MoreThanOneNode_returnsFalse(){
            Object nuevo = new Object();
            LinkedNode<Object> pre = new LinkedNode(nuevo, null, node);
            node.setPrevious(pre);

            boolean returnValue = pre.isLastNode();

            assertFalse(returnValue);
        }
    }

    @Nested
    @DisplayName("Test para metodos de nodos intermedios")
    class isNotATerminalNodeTest{
        
        @Test
        @DisplayName("Comprueba que el nodo inicial es terminal")
        public void isNotATerminalNode_FirstNode_returnsFalse(){
            boolean returnValue = node.isNotATerminalNode();

            assertFalse(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el nodo final es terminal")
        public void isNotATerminalNode_LastNode_returnsFalse(){
            Object post = new Object();
            LinkedNode<Object> next = new LinkedNode<Object>(post, node, null);
            node.setNext(next);

            boolean returnValue = next.isNotATerminalNode();

            assertFalse(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el nodo intermedio de una serie de nodos linkeados, es no terminal")
        public void isNotATerminalNode_returnsTrue(){
            Object ob1 = new Object();
            LinkedNode<Object> previous = new LinkedNode<Object>(ob1, null, node);
            node.setPrevious(previous);
            Object ob2 = new Object();
            LinkedNode<Object> next = new LinkedNode<Object>(ob2, node, null);
            node.setNext(next);

            boolean returnValue = node.isNotATerminalNode();

            assertTrue(returnValue);
        }

    }

}
