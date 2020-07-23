import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Counter {

    public static void main(String... args) {

        // useSynchronization = 1 -> run with synchronization
        // useSynchronization = 0 -> run without synchronization
        int useSynchronization = Integer.parseInt(args[0]);

        System.out.println("Program started.");
        IntegerWrapper sum = new IntegerWrapper();
        System.out.println("Initial Sum : " + sum.getSum());

        int THREAD_COUNT = 1000;

        // list used to keep references of running threads
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            if (useSynchronization == 1){
                // instanciate thread
                Thread workerThread = new WorkerThreadSynchronized(sum);
                threads.add(workerThread);
                // start thread
                workerThread.start();
            }
            else if (useSynchronization == 0){
                // instanciate thread
                Thread workerThread = new WorkerThread(sum);
                threads.add(workerThread);
                // start thread
                workerThread.start();
            }
            else{
                // incorrect command line argument
                System.out.println("Command line argument must be either 0 or 1.");
                break;
            }
        }

        // Wait until all the worker threads have finished executing
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final Sum : " + sum.getSum());

        // write output into a file
        try{
            if (useSynchronization == 1){
                // output file if ran with synchronization
                FileWriter fileWriter = new FileWriter("output_with_synchronization.txt");
                fileWriter.write("Final Sum : " + sum.getSum());
                fileWriter.close();
            }
            else if (useSynchronization == 0){
                // output file if ran without synchronization
                FileWriter fileWriter = new FileWriter("output_without_synchronization.txt");
                fileWriter.write("Final Sum : " + sum.getSum());
                fileWriter.close();
            }

        }catch(Exception exception){
            System.out.println(exception);
        }
        System.out.println("Program finished.");
    }
}

