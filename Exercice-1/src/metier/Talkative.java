package metier;

public class Talkative implements Runnable{
    private int entier;

    public Talkative(int entier) {
        this.entier = entier;
    }

    @Override
    public void run() {
        System.out.println("Executing thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++){
            System.out.println(this.entier);
        }
        System.out.println("End of thread: " + Thread.currentThread().getName());
    }
}
