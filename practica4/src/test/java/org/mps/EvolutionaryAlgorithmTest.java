package org.mps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

public class EvolutionaryAlgorithmTest {
    
    private TournamentSelection selectionOperator;
    private SwapMutation mutationOperator;
    private OnePointCrossover crossoverOperator;
    private EvolutionaryAlgorithm evolutionaryAlgorithm;

    @Nested
    @DisplayName("Clase que comprueba la inicializacion de EvolutionaryAlgorithm")
    public class EvolutionaryAlgorithmTest_init{

        @Test
        @DisplayName("Test que comprueba que si selectionOperator es nulo, al inicializar EvolutionaryAlgorithm lanza una excepcion")
        public void evolutionaryAlgorithm_selectionOperatorNull_throwsError(){
            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm=new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
        }

        @Test
        @DisplayName("Test que comprueba que si mutationOperator es nulo, al inicializar EvolutionaryAlgorithm lanza una excepcion")
        public void evolutionaryAlgorithm_mutationOperatorNull_throwsError() throws EvolutionaryAlgorithmException{
            selectionOperator = new TournamentSelection(1);
            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm=new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
        }

        @Test
        @DisplayName("Test que comprueba que si crossoverOperator es nulo, al inicializar EvolutionaryAlgorithm lanza una excepcion")
        public void evolutionaryAlgorithm_crossoverOperatorNull_throwsError() throws EvolutionaryAlgorithmException{
            selectionOperator = new TournamentSelection(1);
            mutationOperator = new SwapMutation();
            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm=new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
        }
    }

    @Nested
    @DisplayName("Tests para getter y setter de MutationOperator, SelectionOperator y CrossoverOperator")
    public class getterYSetterTest{

        @BeforeEach
        public void init() throws EvolutionaryAlgorithmException{
            selectionOperator = new TournamentSelection(1);
            mutationOperator = new SwapMutation();
            crossoverOperator = new OnePointCrossover();
            evolutionaryAlgorithm = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        }

        @Test
        @DisplayName("Test que comprueba que getMutationOperator te devuelve correctamente el objeto")
        public void getMutationOperator_returnObject_assertEquals(){
            MutationOperator expectedValue = mutationOperator;
            MutationOperator returnedValue = evolutionaryAlgorithm.getMutationOperator();

            assertEquals(expectedValue, returnedValue);
        }

        @Test
        @DisplayName("Test que comprueba que setMutationOperator te cambia el objeto correctamente")
        public void setMutationOperator_validObject_assertEquals(){
            SwapMutation mutationNew = new SwapMutation();

            evolutionaryAlgorithm.setMutationOperator(mutationNew);
            MutationOperator expectedValue = mutationNew;
            MutationOperator returnedValue = evolutionaryAlgorithm.getMutationOperator();

            assertEquals(expectedValue, returnedValue);
        }

        @Test
        @DisplayName("Test que comprueba que getSelectionOperator te devuelve correctamente el objeto")
        public void getSelectionOperator_returnObject_assertEquals(){
            SelectionOperator expectedValue = selectionOperator;
            SelectionOperator returnedValue = evolutionaryAlgorithm.getSelectionOperator();

            assertEquals(expectedValue, returnedValue);
        }

        @Test
        @DisplayName("Test que comprueba que setSelectionOperator te cambia el objeto correctamente")
        public void setSeelctionOperator_validObject_assertEquals() throws EvolutionaryAlgorithmException{
            TournamentSelection selectionNew = new TournamentSelection(2);

            evolutionaryAlgorithm.setSelectionOperator(selectionNew);
            SelectionOperator expectedValue = selectionNew;
            SelectionOperator returnedValue = evolutionaryAlgorithm.getSelectionOperator();

            assertEquals(expectedValue, returnedValue);
        }

        @Test 
        @DisplayName("Test que comprueba que getCrossoverOperator te devuelve el objeto correctamente")
        public void getCrossoverOperator_validObject_assertEquals(){
            CrossoverOperator expectedValue = crossoverOperator;
            CrossoverOperator returnedValue = evolutionaryAlgorithm.getCrossoverOperator();

            assertEquals(expectedValue, returnedValue);
        }

        @Test 
        @DisplayName("Test que comprueba que setCrossoverOperator te cambia el objeto correctamente")
        public void setCrossoverOperator_validObject_assertEquals(){
            OnePointCrossover crossoverNew = new OnePointCrossover();

            CrossoverOperator expectedValue = crossoverNew;
            evolutionaryAlgorithm.setCrossoverOperator(crossoverNew);
            CrossoverOperator returnedValue = evolutionaryAlgorithm.getCrossoverOperator();

            assertEquals(expectedValue, returnedValue);
        }
    }

    @Nested 
    @DisplayName("Tests para el metodo optimize")
    public class optimizeTest{
        @BeforeEach
        public void init() throws EvolutionaryAlgorithmException{
            selectionOperator = new TournamentSelection(1);
            mutationOperator = new SwapMutation();
            crossoverOperator = new OnePointCrossover();
            evolutionaryAlgorithm = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        }

        @Test 
        @DisplayName("Test que comprueba que si la poblacion es nula lanza una excepcion")
        public void optimize_populationNull_throwsError(){
            int[][] population = null;

            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm.optimize(population));
        }

        @Test 
        @DisplayName("Test que comprueba que si el numero de filas de la poblacion es cero lanza una excepcion")
        public void optimize_populationRowsZero_ThrowsError(){
            int[][] population = {};

            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm.optimize(population));
        }

        @Test 
        @DisplayName("Test que comprueba que si las columnas son nulas lanza una excepcion")
        public void optimize_populationColumnNull_ThrowsError(){
            int[][] population = {
                null
            };

            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm.optimize(population));
        }

        @Test 
        @DisplayName("Test que comprueba que si la longitud de las columnas es cero lanza una excepcion")
        public void optimize_populationLengthColumnZero_ThrowsError(){
            int[][] population = {
                {}
            };

            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm.optimize(population));
        }

        @Test 
        @DisplayName("Test que comprueba que si la longitud de la poblacion no es par lanza un error")
        public void optimize_populationLengthImpar_ThrowsError(){
            int[][] population = {
                {1,2},
                {2,4},
                {5,6}
            };

            assertThrows(EvolutionaryAlgorithmException.class, ()->evolutionaryAlgorithm.optimize(population));
        }

        @Test
        @DisplayName("Test que comprueba que si la poblacion no es nula y tiene mas de ceros filas y columnas se aplicara un algoritmo de optimizacion donde el devuelvo tendra el mismo numero de elementos")
        public void optimize_validPopulation_assertEquals() throws EvolutionaryAlgorithmException{
            int[][] population = {
                {1,2},
                {2,4}
            };

            int expectedLengthRows = population.length;
            int expectedLengthColumns = population[0].length;
            evolutionaryAlgorithm.optimize(population);
            int returnedLengthRows = population.length;
            int returnedLengthColumns = population[0].length;

            assertEquals(expectedLengthRows, returnedLengthRows);
            assertEquals(expectedLengthColumns, returnedLengthColumns);
        }
        
    }

}
