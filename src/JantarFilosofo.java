import java.util.concurrent.Semaphore;

public class JantarFilosofo {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Filosofo[] filosofos = new Filosofo[numFilosofos];
        Semaphore[] garfos = new Semaphore[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Semaphore(1);
        }

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % numFilosofos]);
            filosofos[i].start();
        }
    }
}
