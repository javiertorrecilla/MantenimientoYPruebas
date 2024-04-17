package org.mps;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.OnePointCrossover;


public class OnePointCrossoverTest{

    private OnePointCrossover onePointCrossover;

    @BeforeEach
    public void setUp() {
        onePointCrossover = new OnePointCrossover();
    }

    @Test
    @DisplayName("Comprobar que cuando se pasa un padre nulo, la funcion crossover lanza error")
    public void crossover_Parent2Null_ThrowsException() throws EvolutionaryAlgorithmException{
        int[] parent1 = {1, 2, 3};
        int[] parent2 = null;

        assertThrows(EvolutionaryAlgorithmException.class, ()->onePointCrossover.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Comprobar que cuando se pasa un padre nulo, la funcion crossover lanza error")
    public void crossover_Parent1Null_ThrowsException() throws EvolutionaryAlgorithmException{
        int[] parent2 = {1, 2, 3};
        int[] parent1 = null;

        assertThrows(EvolutionaryAlgorithmException.class, ()->onePointCrossover.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Comprobar que cuando se pasa un padre nulo, la funcion crossover lanza error")
    public void crossover_Parent1Length0_ThrowsException() throws EvolutionaryAlgorithmException{
        int[] parent2 = {1, 2, 3};
        int[] parent1 = {1};

        assertThrows(EvolutionaryAlgorithmException.class, ()->onePointCrossover.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Comprobar que cuando cuando las longitudes de los parents son distintas, la funcion crossover lanza error")
    public void crossover_ParentsDifferentLength_ThrowsException() throws EvolutionaryAlgorithmException{
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {1, 2};

        assertThrows(EvolutionaryAlgorithmException.class, ()->onePointCrossover.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Comprobar que cuando cuando las longitudes de los parents son iguales, se crea un offspring no nulo")
    public void crossover_ParentsEqualsLength_OffspringNotNull() throws EvolutionaryAlgorithmException{
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {4, 5, 6};

        int[][] offspring = onePointCrossover.crossover(parent1, parent2);

        assertNotNull(offspring);
    }

    @Test
    @DisplayName("Comprobar que cuando cuando las longitudes de los parents son iguales, se crea un offspring con las filas de igual length que los parents")
    public void crossover_ParentsEqualsLength_OffspringLengthEqualsParentsLength() throws EvolutionaryAlgorithmException{
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {4, 5, 6};

        int[][] offspring = onePointCrossover.crossover(parent1, parent2);

        assertEquals(parent1.length, offspring[0].length);
        assertEquals(parent2.length, offspring[1].length);
    }

    @Test
    @DisplayName("Comprobar que cuando los padres son válidos, las filas de la matriz son distintas a las de los padres")
    public void crossover_ParentsEqualsLength_OffspringDifferentOfParents() throws EvolutionaryAlgorithmException{
        int[] parent1 = {4, 7, 2, 8};
        int [] parent2 = {1, 9, 5, 3};
        int [] [] offspring = onePointCrossover.crossover(parent1, parent2);
        
        String expectedValue = Arrays.toString(parent1);
        String returnValue = Arrays.toString(offspring[0]);

        assertNotEquals(expectedValue, returnValue);
        
        expectedValue = Arrays.toString(parent2);
        returnValue = Arrays.toString(offspring[1]);

        assertNotEquals(expectedValue, returnValue);
    }
}

