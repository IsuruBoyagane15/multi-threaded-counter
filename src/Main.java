import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 for synchronization; 0 for no synchronization");
        String response = scanner.nextLine();

        WrappedInteger sum = new WrappedInteger();
        System.out.println("Starting Sum : " + sum.getInt());

        // number of threads
        int threadCount = 1000;

        // list of running threads to be joined in the future
        List<Thread> threads = new ArrayList<>();

        // start 1000 thread
        int sync = Integer.parseInt(response);
        for (int i = 0; i < threadCount; i++) {
            if (sync == 1){
                Thread workerThread = new SynchronizedThread(sum);
                threads.add(workerThread);
                workerThread.start();
            }
            else if (sync == 0){
                Thread workerThread = new NormalThread(sum);
                threads.add(workerThread);
                workerThread.start();
            }
            else{
                // incorrect command line argument
                System.out.println("Wrong input. Please retry");
                break;
            }
        }

        // join threads before output the final sum
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ending Sum : " + sum.getInt());

        // write output into a file
        try{
            if (sync == 1){
                // output of thread synchronization
                FileWriter fileWriter = new FileWriter("sync_output.txt");
                fileWriter.write("Ending Sum : " + sum.getInt());
                fileWriter.close();
            }
            else if (sync == 0){
                // output without thread synchronization
                FileWriter fileWriter = new FileWriter("normal_output.txt");
                fileWriter.write("Ending Sum : " + sum.getInt());
                fileWriter.close();
            }

        }catch(Exception exception){
            System.out.println(exception);
        }
    }
}

