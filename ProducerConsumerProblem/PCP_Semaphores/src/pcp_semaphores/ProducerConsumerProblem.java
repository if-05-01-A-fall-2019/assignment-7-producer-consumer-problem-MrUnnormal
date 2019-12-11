package pcp_semaphores;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumerProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        System.out.println("Starting");
        PCP_Thread thread1 = new PCP_Thread(semaphore, "producer");
        PCP_Thread thread2 = new PCP_Thread(semaphore, "consumer");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
    
}
