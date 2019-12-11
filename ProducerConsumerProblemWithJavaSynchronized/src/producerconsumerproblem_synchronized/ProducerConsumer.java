package producerconsumerproblem_synchronized;

import java.util.Random;
import java.util.Stack;

/**
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumer {
    Random random = new Random();
    Stack buffer = new Stack<Integer>();
    final int MAX_ITEMS = 5;
    int currentItems = 0;
    
    public void produce() throws InterruptedException {
        while(true) {
            synchronized(this) {
                Integer item = random.nextInt(5000);
                if(currentItems == MAX_ITEMS) {
                    wait();
                }
                System.out.println("Produced " + item + " Count: " + currentItems);
                buffer.push(item);
                currentItems++;
                if(currentItems == 1) {
                    notify();
                } 
                Thread.sleep(1000); // Can´t read otherwise
            }
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            synchronized(this) {
                if(currentItems == 0) {
                    wait();
                }
                item = (int) buffer.pop();
                System.out.println("Consumed " + item + " Count: " + currentItems);
                currentItems--;
                if(currentItems == MAX_ITEMS - 1) {
                    notify();
                }
                Thread.sleep(1000); // Can´t read otherwise
            }
        }  
    }
}
