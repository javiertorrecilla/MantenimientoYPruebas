package org.mps;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import org.junit.jupiter.api.*;
import org.mps.selection.TournamentSelection;

public class TournamentSelectionTest {

    private TournamentSelection tournament;
    private int tournamentSize = 3;
    
    @BeforeEach
    public void setUp() throws EvolutionaryAlgorithmException{
        tournament = new TournamentSelection(tournamentSize);
    }

    @Test
    @DisplayName("Comprueba que si se crea un torneo de tamaño 0 da error")
    public void tournamentSelection_SizeError_ThrowsError() throws EvolutionaryAlgorithmException{
        int size = 0;

        assertThrows(EvolutionaryAlgorithmException.class, ()-> new TournamentSelection(size));
    }

    @Test
    @DisplayName("Comprueba que si la poblacion es nula, salta una excepcion")
    public void select_PopulationNull_ThrowsError() throws EvolutionaryAlgorithmException{
        int[] population = null;

        assertThrows(EvolutionaryAlgorithmException.class, ()->tournament.select(population));
    }

    @Test
    @DisplayName("Comprueba que si la poblacion es 0, salta una excepcion")
    public void select_PopulationZero_ThrowsError() throws EvolutionaryAlgorithmException{
        int[] population = {};

        assertThrows(EvolutionaryAlgorithmException.class, ()->tournament.select(population));
    }

    @Test
    @DisplayName("Comprueba que cuando la población es válida el resultado que devuelve no es nulo")
    public void select_PopulationOk_Check() throws EvolutionaryAlgorithmException{
        int[] population = {1,2,3,4,5};
        
        int[] selected = tournament.select(population);

        assertNotNull(selected);
    }

    @Test
    @DisplayName("Comprueba que el tamaño del resultado es igual al de la población")
    public void select_PopulationSize_Check() throws EvolutionaryAlgorithmException{
        int[] population = {1,2};
        
        int[] selected = tournament.select(population);
        int expectedValue = population.length;
        int returnValue = selected.length;

        assertEquals(expectedValue,returnValue);
    }

    @Test
    @DisplayName("Comprueba que cuando el tamaño de la población es 1, el resultado será el array de la población")
    public void select_PopulationSize1_Check() throws EvolutionaryAlgorithmException{
        int[] population = {1};
        
        int[] selected = tournament.select(population);
        String expectedValue = Arrays.toString(population);
        String returnValue = Arrays.toString(selected);

        assertEquals(expectedValue,returnValue);
    }
    

}
