package metier;

import java.util.List;

public class Sommeur implements Runnable {
    private List<Integer> entiers;
    private int debut;
    private int fin;

    public Sommeur(List<Integer> entiers, int debut, int fin) {
        this.entiers = entiers;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        System.out.println("Thread: "+ Thread.currentThread().getName() +", Debut: "+ this.debut + ", Fin: "+ (this.fin - 1) + ", Somme: "+ this.getSomme());
    }

    public int getSomme() {
        return this.entiers.subList(this.debut, this.fin)
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
