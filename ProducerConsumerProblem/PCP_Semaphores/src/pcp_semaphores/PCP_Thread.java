/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcp_semaphores;

import java.util.concurrent.Semaphore;


/**
 *
 * @author Jan Kaufmann 3AHIF
 */
public class PCP_Thread extends Thread{
    Semaphore semaphore;
    String name;
    
    public PCP_Thread(Semaphore semaphore, String name) {
        super(name);
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        ProducerConsumer problem = new ProducerConsumer(semaphore);
        if(name.equals("producer")) {
            try {
                problem.produce();
            } catch (InterruptedException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else {
            try {
                problem.consume();
            } catch (InterruptedException ex) {
                System.out.println("Error " + ex.getMessage());}
            }
        }
    }
