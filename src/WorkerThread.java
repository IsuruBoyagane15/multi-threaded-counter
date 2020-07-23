public class WorkerThread extends Thread{
    private final IntegerWrapper localSum;

    public WorkerThread(IntegerWrapper sum){
        localSum = sum;
    }

    @Override
    public void run() {
        // read sum, increment by 1 and write
        localSum.setSum(localSum.getSum() + 1);
    }
}
