package producerconsumerproblem;

import java.util.Stack;

/**
 *
 * @author Jan Kaufmann 3AHIF
 */
public class ProducerConsumer {
    Stack buffer = new Stack<Integer>();
    final int MAX_ITEMS = 5;
    int currentItems = 0;
    
    public void produce() throws InterruptedException {
        while(true) {
            Integer item = (int) Math.random();
            if(currentItems == MAX_ITEMS) {
                wait();
            }
            System.out.println("Produced " + item);
            buffer.push(item);
            currentItems++;
            if(currentItems == 1) {
                notify();
            }   
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            if(currentItems == 0) {
                wait();
            }
            item = (int) buffer.pop();
            System.out.println("Consmed " + item);
            currentItems--;
            if(currentItems == MAX_ITEMS - 1) {
                notify();
            }
        }  
    }
}
