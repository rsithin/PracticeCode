
public class ThreadTest implements Runnable {

    public void run() {
        System.out.println("Now the thread is running...");
    }

    public static void main(String args[]){
        Runnable r1 = new ThreadTest();

        Thread t1 = new Thread(r1, "My new thread");

        t1.start();

        String str = t1.getName();
        System.out.println(str);
    }

}
