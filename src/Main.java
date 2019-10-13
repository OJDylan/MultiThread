import java.util.Scanner;

class MyThread implements Runnable {
    private int start, end;
    private static int total;

    //constructor to pass start and end number per thread
    MyThread(int s, int e) {
        this.start = s;
        this.end = e;
    }

    //No argument constructor to call getSum method
    MyThread(){}

    @Override
    public void run() {
        //print thread start
        System.out.println("Thread " + Thread.currentThread().getId() + ": Started!");
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } //pause

        int sum = 0;

        //main summation
        for (int i = start; i <= end; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is on --> " + i);
            sum = sum + i;
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } //pause
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " sum = " + sum); //print sum of each thread
        total += sum; //store sum of each thread
        System.out.println("Thread " + Thread.currentThread().getId() + " Exiting"); //print thread exit
    }

    //method to return total sum of all threads
    int getSum() {
        return total;
    }
}

public class Main {
    public static void main (String[] args) throws InterruptedException {

        //get starting number from user
        System.out.print("Enter starting number: ");
        int start = new Scanner(System.in).nextInt();

        //get end number from user
        System.out.print("Enter ending number: ");
        int end = new Scanner(System.in).nextInt();

        if (start > end) {
            System.out.println("\nERROR: Starting number cannot be larger than the ending number, please try again");
        } else {
            //get number of threads from user
            System.out.print("Enter the number of threads: ");
            int numThreads = new Scanner(System.in).nextInt();

            System.out.println("==============================\n");

            int range = ((end - start) + 1); //calculate range
            int sub_range = range / numThreads; //divides range into sub-ranges by the number of threads

            Thread t = new Thread();

            if (range % numThreads == 0) {
                //create the threads
                for (int i = 0; i < numThreads; i++) {
                    t = new Thread(new MyThread(start, start + sub_range - 1)); //passes start and end number of a subrange
                    t.start();
                    Thread.sleep(500);
                    start += sub_range; //starts on number after the last end number
                }
                t.join(); //join threads before displaying end message
                System.out.println("\nAll threads completed."); //end message
                System.out.println("\n==============================");
                System.out.println("Total sum is: " + new MyThread().getSum()); //print total sum
                System.out.println("==============================");
            } else {
                System.out.println(
                        "ERROR: The range of numbers cannot be equally divided among the number of threads, please try again");
            }
        }
    }
}
