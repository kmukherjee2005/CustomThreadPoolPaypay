import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    public  int nThread;
    public  Worker[] threads;
    public  LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int nThread){
        nThread = nThread;
        queue = new LinkedBlockingQueue();
        threads = new Worker[nThread];

        for (int i = 0;i<nThread;i++){
            threads[i] = new Worker();
            threads[i].start();
        }


    }

    public void execute(Runnable task){
        synchronized (queue){
            queue.add(task);
            queue.notify();
        }
    }

    private  class  Worker extends Thread{
        @Override
        public void run(){
            Runnable task;

            while(true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            queue.wait();
                        }catch (InterruptedException e){
                            System.out.println("An error has occurred");
                        }
                    }
                    task = queue.poll();
                }
                try {
                    task.run();
                }catch (RuntimeException e){
                    System.out.println("ThreadPool interrupted ");
                }
            }
        }
    }


}


