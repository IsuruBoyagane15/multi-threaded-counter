public class NormalThread extends Thread{
    private final WrappedInteger sumInThread;

    public NormalThread(WrappedInteger value){
        sumInThread = value;
    }

    @Override
    public void run() {
        increment();
    }

    public void increment(){
        // read sum
        int oldSum = sumInThread.getInt();
        // increment
        int newSum = oldSum + 1;
        // write sum
        sumInThread.setInt(newSum);
    }
}
