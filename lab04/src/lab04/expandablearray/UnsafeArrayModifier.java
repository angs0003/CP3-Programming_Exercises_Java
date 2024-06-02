package cp3.lab04.expandablearray;

public class UnsafeArrayModifier implements Runnable {
    private final UnsafeExpandableArray expandableArray;
    private final int threadNumber;

    public UnsafeArrayModifier(UnsafeExpandableArray expandableArray, int threadNumber) {
        this.expandableArray = expandableArray;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            expandableArray.add(threadNumber * 1000 + i);
        }
    }
}
