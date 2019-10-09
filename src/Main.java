import java.util.Scanner;

class MyThread implements Runnable {
    //**constructor to for range and number of threads**
    //blablabla this is test

    //countdown from 5 to 1 for each thread
    @Override
    public void run() {
        //print thread start
        System.out.println("Thread " + Thread.currentThread().getId() + ": Started");
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } //pause

//        int sum = 0;

        //cumulative sum
        for (int i=5; i>0; i--){
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + i);
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } //pause
        }
        System.out.println(Thread.currentThread().getId() + " Exiting"); //print thread exit
//        System.out.println("Thread " + Thread.currentThread().getId() + " sum = " + sum); //print sum of each thread
    }
}

public class Main {
    public static void main (String[] args) throws InterruptedException {

//        //get starting range from user
//        System.out.print("Enter the starting number: ");
//        int start = new Scanner(System.in).nextInt();
//
//        //get end range from user
//        System.out.print("Enter the ending number: ");
//        int end = new Scanner(System.in).nextInt();
//
//        //populate array range
//        int range = ((end - start) + 1); //calculate range
//        int count = 0;
//        int[] arr = new int[range];
//        for (int i=start; i<=end; i++) {
//            arr[count] = i;
//            count++;
//        }

        //get number of threads from user
        System.out.print("Enter the number of threads: ");
        int n = new Scanner(System.in).nextInt();

        Thread t = new Thread();

        //create threads
        for (int i=1; i<=n; i++) {
            t = new Thread(new MyThread());
            t.start();
            Thread.sleep(500);
        }

        t.join();
        System.out.println("End");
    }
}
