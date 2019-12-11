package pcp_semaphores;

import java.util.concurrent.Semaphore;

/**
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumerProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        System.out.println("Starting");
        ProducerConsumer problem = new ProducerConsumer(semaphore); 
        PCP_Thread thread1 = new PCP_Thread("producer", problem);
        PCP_Thread thread2 = new PCP_Thread("consumer", problem);
        thread2.start();
        thread1.start();
        thread1.join();
        thread2.join();
    }
    
}
