package pcp_semaphores;

import java.util.concurrent.Semaphore;


    /**
 * @author Jan Kaufmann 3AHIF
 */
public class PCP_Thread extends Thread{
    String name;
    ProducerConsumer problem;
    
    public PCP_Thread(String name, ProducerConsumer prod) {
        super(name);
        this.name = name;
        this.problem = prod;
    }

    @Override
    public void run() {
        if(this.getName().equals("producer")) {
            try {
                problem.produce();
            } catch (InterruptedException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else {
            System.out.println("yeet");
            try {
                problem.consume();
            } catch (InterruptedException ex) {
                System.out.println("Error " + ex.getMessage());}
            }
        }
    }
