import java.util.Scanner;

class MyThread implements Runnable {
    private int start, end;

    //constructor to pass start and end number per thread
    public MyThread(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public void run() {
        //print thread start
        System.out.println("Thread " + Thread.currentThread().getId() + ": Started");
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } //pause

        int sum = 0;
        int total = 0;

        //main summation
        for (int i = start; i <= end; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + " on: " + i);
            sum = sum + i;
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); } //pause
            total += sum;
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " sum = " + sum); //print sum of each thread
        System.out.println(Thread.currentThread().getId() + " Exiting"); //print thread exit
    }

}

public class Main {
    public static void main (String[] args) throws InterruptedException {

        //get starting range from user
        System.out.print("Enter the starting number: ");
        int start = new Scanner(System.in).nextInt();

        //get end range from user
        System.out.print("Enter the ending number: ");
        int end = new Scanner(System.in).nextInt();

        //get number of threads from user
        System.out.print("Enter the number of threads: ");
        int n = new Scanner(System.in).nextInt();

        int range = ((end - start) + 1); //calculate range
        int sub_range = range/n; //divides range into sub-ranges by number of threads

        Thread t = new Thread();

        //create threads
        for (int i=0; i<n; i++) {
            t = new Thread(new MyThread(start, start + sub_range - 1)); //passes start number to end number divided by the number of threads
            t.start();
            Thread.sleep(500);
            start += sub_range; //starts on the number after the last end number
        }

        t.join(); //join threads before displaying end message
        System.out.println("\nAll threads completed."); //end message

        //**display total sum here**
    }
}
