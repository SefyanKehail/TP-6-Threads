package presentation;


import metier.Sommeur;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        // tableau d'entier d'une façon dynamique list [rangeMin, ... , rangeMax] step = 1
        // choisir range min (inclus) et range max (exclus)
        int rangeMin = 0;
        int rangeMax = 10000;

        List<Integer> entiers = IntStream.range(rangeMin, rangeMax) // hna we're working with primitive ints
                .boxed() // nbadlouha Stream<Integers>
                .toList();


        // plages d'entiers d'une façon dynamique
        // choisir le nombre de subLists voulu / nombre de threads
        int nombrePlages = 100;

        Random random = new Random();
        SortedSet<Integer> indicesSet = IntStream.generate(() -> random.nextInt(rangeMin + 1, rangeMax))
                .limit(nombrePlages - 1)
                .boxed().collect(Collectors.toCollection(TreeSet::new));

        indicesSet.add(rangeMin);
        indicesSet.add(rangeMax);

        List<Integer> indices = new ArrayList<>(indicesSet);
        ExecutorService executorService = Executors.newFixedThreadPool(nombrePlages);
        int sommeTotale = 0;

        for (int i = 0, j = 1; i < indices.size() - 1; i ++ , j++) {
            Sommeur sommeur = new Sommeur(entiers, indices.get(i), indices.get(j));
            sommeTotale += sommeur.getSomme();
            executorService.submit(sommeur);
        }

        executorService.shutdown();


        // somme totale

        try {
            if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                int sommeControle = entiers.stream().mapToInt(Integer::intValue).sum();
                System.out.println("\nThreads execution finished, Somme totale: " + sommeTotale + ", Verification: "+ sommeControle);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
