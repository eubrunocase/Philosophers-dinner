import java.util.concurrent.Semaphore;


    class Filosofo extends Thread {
        private int id;
        private Semaphore garfoesquerdo;
        private Semaphore garfodireito;

        public Filosofo(int id, Semaphore garfoesquerdo, Semaphore garfodireito) {
            this.id = id;
            this.garfoesquerdo = garfoesquerdo;
            this.garfodireito = garfodireito;
        }

        public void run() {
            try {
                while (true) {
                    pensar();
                    pegueGarfos();
                    comer();
                    abaixarGarfos();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void pensar() throws InterruptedException {
            System.out.println("Filosofo " + id + " está pensando.");
            Thread.sleep((long) (Math.random() * 1000));
        }

        private void pegueGarfos() throws InterruptedException {
            double escolha = Math.random();

            if (escolha < 0.5) {
                garfoesquerdo.acquire();
                System.out.println("Filósofo " + id + " pega o garfo esquerdo.");
                garfodireito.acquire();
                System.out.println("Filósofo " + id + " pega o garfo direito.");
            } else {
                garfodireito.acquire();
                System.out.println("Filósofo " + id + " pega o garfo direito.");
                garfoesquerdo.acquire();
                System.out.println("Filósofo " + id + " pega o garfo esquerdo.");
            }
        }

        private void comer() throws InterruptedException {
            System.out.println("Filosofo " + id + " está comendo.");
            Thread.sleep((long) (Math.random() * 1000));
        }

        private void abaixarGarfos() {
            double escolha = Math.random();
            if (escolha < 0.5) {
                garfoesquerdo.release();
                System.out.println("Filósofo " + id + " abaixa o garfo esquerdo.");
                garfodireito.release();
                System.out.println("Filósofo " + id + " abaixa o garfo direito.");
            } else {
                garfodireito.release();
                System.out.println("Filósofo " + id + " abaixa o garfo direito.");
                garfoesquerdo.release();
                System.out.println("Filósofo " + id + " abaixa o garfo esquerdo.");
            }
        }
    }

