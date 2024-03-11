package presentation;


import metier.Talkative;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Runnable task = new Talkative(i);
            new Thread(task).start();
        }
    }


    // Q6
    // On constate que l'ordre d'éxecution est mélangé car ca dépends du machine comment il gére les threads.
}
