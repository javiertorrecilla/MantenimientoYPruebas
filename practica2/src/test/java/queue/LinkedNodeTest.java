package queue;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LinkedNodeTest {

    private Object item = new Object();
    private LinkedNode<Object> node = new LinkedNode<>(item, null, null);

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
    @DisplayName("Tests para metedos Previous")
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
        @DisplayName("Se comprueba que el metodo getNext como no tiene siguiente devovlera nulo")
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
    @DisplayName("Test para metodos de nodos iniciales")
    class firstNode{
        
        @Test
        @DisplayName("Comprueba que como solo hay un nodo es el inicial")
        public void isFirstNode_OneNode_returnsTrue(){
            boolean returnValue = node.isFirstNode();

            assertTrue(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el ultimo nodo linkeado no es inicial")
        public void isFirstNode_MoreThanOneNode_returnsFalse(){
            LinkedNode<Object> post = new LinkedNode(item, null, null);
            post.setPrevious(node);
            node.setNext(post);

            boolean returnValue = post.isFirstNode();

            assertFalse(returnValue);
        }

    }


    @Nested 
    @DisplayName("Test para metodos del primer nodo")
    class firstNode{
        
        @Test
        @DisplayName("Comprueba que como solo hay un nodo, es el final")
        public void isLastNode_returnsTrue(){
            boolean returnValue = node.isLastNode();

            assertTrue(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el primer nodo linkeado no es final")
        public void isFirstNode_MoreThanOneNode_returnsFalse(){
            LinkedNode<Object> pre = new LinkedNode(item, null, null);
            pre.setNext(node);
            node.setPrevious(pre);

            boolean returnValue = pre.isLastNode();

            assertFalse(returnValue);
        }
    }

    @Nested
    @DisplayName("Test para metodos de nodos intermedios")
    class notATerminalNode{
        
        @Test
        @DisplayName("Comprueba que el nodo inicial de una serie de nodos linkeados, no es no terminal")
        public void isNotATerminalNode_FirstNode_returnsFalse(){
            LinkedNode<Object> previous = new LinkedNode<Object>(item, null, null);
            previous.setNext(node);
            LinkedNode<Object> next = new LinkedNode<Object>(item, null, null);
            next.setPrevious(node);
            node.setPrevious(previous);
            node.setNext(next);

            boolean returnValue = pre.isNotATerminalNode();

            assertFalse(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el nodo final de una serie de nodos linkeados, no es no terminal")
        public void isNotATerminalNode_LastNode_returnsFalse(){
            LinkedNode<Object> previous = new LinkedNode<Object>(item, null, null);
            previous.setNext(node);
            LinkedNode<Object> next = new LinkedNode<Object>(item, null, null);
            next.setPrevious(node);
            node.setPrevious(previous);
            node.setNext(next);

            boolean returnValue = next.isNotATerminalNode();

            assertFalse(returnValue);
        }

        @Test
        @DisplayName("Comprueba que el nodo intermedio de una serie de nodos linkeados, es no terminal")
        public void isNotATerminalNode_returnsTrue(){
            LinkedNode<Object> previous = new LinkedNode<Object>(item, null, null);
            previous.setNext(node);
            LinkedNode<Object> next = new LinkedNode<Object>(item, null, null);
            next.setPrevious(node);
            node.setPrevious(previous);
            node.setNext(next);

            boolean returnValue = node.isNotATerminalNode();

            assertTrue(returnValue);
        }

    }

}
