package pcp_semaphores;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumer {
    Semaphore semaphore;
    Random rand = new Random();
    Stack buffer = new Stack<Integer>();
    final int MAX_ITEMS = 5;
    int currentItems = 0;

    public ProducerConsumer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    
    public void produce() throws InterruptedException {
        while(true) {
            Integer item = rand.nextInt(50000);;
            if(currentItems == MAX_ITEMS) {
                semaphore.release();
            }
            System.out.println("Produced: " + item + " Count: " + currentItems);
            buffer.push(item);
            currentItems++;
            if(currentItems == 1) {
                semaphore.release();
                consume();  // "wakeup" consumer
            }
            Thread.sleep(1000); // Can´t read otherwise
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            if(currentItems == 0) {
                semaphore.release();
                return;
            }
            item = (int) buffer.pop();
            System.out.println("Consumed: " + item + " Count: " + currentItems);
            currentItems--;
            if(currentItems == MAX_ITEMS - 1) {
                semaphore.acquire();
            }
            Thread.sleep(1000); // Can´t read otherwise
        }  
    }
}
