package info.maizz.lifebetterkit.lang.concurrency;

/**
 * Created by Lucas on 2017-11-02.
 */
public class ConsumerProducerModel2 {
    volatile  boolean exists = false;
    Object monitor = new Object();
    Thread consumer = new Thread(()->{
        while(true){
            synchronized (monitor) {
                try {
                    if (exists) {
                        System.out.println("Consuming");
                        exists=false;
                    } else {
                        System.out.println("Waiting");
                        monitor.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    });

    Thread producer = new Thread(()->{
        while(true){
                try {
                    System.out.println("Producing");
                    Thread.sleep(1000 * 10);
                    synchronized (monitor) {
                        System.out.println("Notify");
                        monitor.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                exists = true;
        }
    });
    public static void main(String[] args) {
        ConsumerProducerModel2 cpm = new ConsumerProducerModel2();
        cpm.consumer.start();
        cpm.producer.start();
    }
}
