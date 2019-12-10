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
            synchronized(this) {
                Integer item = (int) Math.random();
                if(currentItems == MAX_ITEMS) {
                    wait();
                }
                buffer.push(item);
                currentItems++;
                if(currentItems == 1) {
                    notify();
                }   
            }
        }
    }
    
    public void consume() throws InterruptedException {
        int item;
        while(true) {
            synchronized (this) {
                if(currentItems == 0) {
                    wait();
                }
                item = (int) buffer.pop();
                currentItems--;
                if(currentItems == MAX_ITEMS - 1) {
                    notify();
                }
            }
        }  
    }
}
