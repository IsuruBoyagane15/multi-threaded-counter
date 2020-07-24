public class SynchronizedThread extends Thread {

    private final WrappedInteger sumInThread;

    public SynchronizedThread(WrappedInteger value){
        sumInThread = value;
    }

    @Override
    public void run() {
        increment();
    }

    // use synchronized block in increment method for synchronization
    public void increment() {
        synchronized (sumInThread) {
            // read sum
            int oldSum = sumInThread.getInt();
            // increment
            int newSum = oldSum + 1;
            // write sum
            sumInThread.setInt(newSum);
        }
    }
}
