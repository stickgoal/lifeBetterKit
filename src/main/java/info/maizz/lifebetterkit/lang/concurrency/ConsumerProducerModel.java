package info.maizz.lifebetterkit.lang.concurrency;

/**
 * Created by Lucas on 2017-11-02.
 */
public class ConsumerProducerModel {
    volatile  boolean exists = false;
    Thread consumer = new Thread(()->{
        while(true){
            try {
                Thread.sleep(1000);
                if(exists){
                    System.out.println("Consuming");
                    exists=false;
                }else{
                    System.out.println("Waiting");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    Thread producer = new Thread(()->{
        while(true){
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exists=true;
        }
    });
    public static void main(String[] args) {
        ConsumerProducerModel cpm = new ConsumerProducerModel();
        cpm.consumer.start();
        cpm.producer.start();
    }
}
