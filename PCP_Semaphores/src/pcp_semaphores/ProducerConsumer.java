package pcp_semaphores;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumer {
    Semaphore semaphore;
    Random random = new Random();
    Stack buffer = new Stack<Integer>();
    final int MAX_ITEMS = 5;
    int currentItems = 0;

    public ProducerConsumer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    
    public void produce() throws InterruptedException {
        while(true) {
            if(currentItems < MAX_ITEMS) {
                semaphore.acquire();
            }
            Integer item = random.nextInt(5000);
            if(currentItems >= MAX_ITEMS) {
                semaphore.release();
                return;
            }
            System.out.println("Produced: " + item + " Count: " + currentItems);
            buffer.push(item);
            currentItems++;
            Thread.sleep(1000); // Can´t read otherwise
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            if(currentItems >= MAX_ITEMS) {
                semaphore.acquire();
            }
            while(currentItems > 0) {
                item = (int) buffer.pop();
                System.out.println("Consumed: " + item + " Count: " + currentItems);
                currentItems--;
            }
            semaphore.release();
            Thread.sleep(1000); // Can´t read otherwise
        }  
    }
}
