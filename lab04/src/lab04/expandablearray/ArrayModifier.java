package cp3.lab04.expandablearray;


public class ArrayModifier implements Runnable {
    private ExpandableArray array;
    private int id;

    public ArrayModifier(ExpandableArray array, int id) {
        this.array = array;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                array.add(id * 10 + i);
                Thread.sleep(10); // Small delay to increase chance of concurrent access issues
            }
        } catch (Exception e) {
            System.out.println("Exception in thread " + id + ": " + e.getMessage());
        }
    }
}
