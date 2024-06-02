// package cp3.lab04.expandablearray;
import cp3.lab04.expandablearray.UnsafeExpandableArray;
import cp3.lab04.expandablearray.UnsafeArrayModifier;

public class UnsafeExpandableArrayDriver {
    public static void main(String[] args) {
        UnsafeExpandableArray ea = new UnsafeExpandableArray(1);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new UnsafeArrayModifier(ea, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final size: " + ea.size());

        // Display some elements to observe potential corruption
        for (int i = 0; i < 100; i++) {
            System.out.print(ea.get(i) + " ");
        }
    }
}
