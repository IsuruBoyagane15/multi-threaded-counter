public class WorkerThreadSynchronized extends Thread {

    private final IntegerWrapper localSum;

    public WorkerThreadSynchronized(IntegerWrapper sum){
        localSum = sum;
    }

    @Override
    public void run() {
        // use synchronized block to achive mutual excluiton of threads
        synchronized (localSum) {
            // read sum, increment by 1 and write
            localSum.setSum(localSum.getSum() + 1);
        }
    }
}
