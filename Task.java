public class Task implements Runnable {
    private int num;
    private Task(int n){
        num=n;
    }
    @Override
    public void run(){
        System.out.println("Task " + num + " is running.");
    }

    public static void main(String[]args){
        ThreadPool pool =new ThreadPool(7);

        for (int i=0;i<5;i++){
            Task task = new Task(i);
            pool.execute(task);
        }
    }
}
