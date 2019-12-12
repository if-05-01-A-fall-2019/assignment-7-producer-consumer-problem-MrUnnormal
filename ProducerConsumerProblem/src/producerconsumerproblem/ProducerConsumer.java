package producerconsumerproblem;

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
            Integer item = random.nextInt(5000);
            if(currentItems == MAX_ITEMS) {
                wait(); // Only works in synchronized block; throws IllegalMonitorStateException
            }
            System.out.println("Produced " + item);
            buffer.push(item);
            currentItems++;
            if(currentItems == 1) {
                notify();   // Only works in synchronized block; throws IllegalMonitorStateException
            }   
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            if(currentItems == 0) {
                wait(); // Only works in synchronized block; throws IllegalMonitorStateException
            }
            item = (int) buffer.pop();
            System.out.println("Consumed " + item);
            currentItems--;
            if(currentItems == MAX_ITEMS - 1) {
                notify();   // Only works in synchronized block; throws IllegalMonitorStateException
            }
        }  
    }
}
