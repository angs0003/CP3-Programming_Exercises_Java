package cp3.lab04.expandablearray;// package cp3.lab04.expandablearray;


/**
 *
 */
public class ExpandableArrayDriver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException
    {
        ExpandableArray ea = new ExpandableArray(1);

        // Create and start multiple threads
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ArrayModifier(ea, i));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final size: " + ea.size());

        // Print the contents of the array
        for (int i = 0; i < ea.size(); i++) {
            System.out.print(ea.get(i) + " ");
        }
        System.out.println();

        // System.out.println("size: " + ea.size());

        // for (int i = 0; i < 10; i++)
        // {
        //     ea.add(new Integer(i));
        // }


        // System.out.println("size: " + ea.size());

        // for (int i = 0; i < ea.size(); i++)
        // {
        //     System.out.print(ea.get(i) + " ");
        // }

        // System.out.println();

    }

}
