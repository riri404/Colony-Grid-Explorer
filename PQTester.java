import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.File;
import java.util.Scanner;

/***
 * Driver program to run various tests
 */
public class PQTester {
    public static void main(String[] args) {
        int [] N = {10, 100, 1000, 10000, 100000, 1000000};

        String fileName = "elements_test_file";
        String outFileName = "pqtestrun.txt";
        String line = "---------------------------------------------\n";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outFileName);
        } catch(FileNotFoundException e) {
            System.out.println("Cannot write to " + outFileName);
        }

        System.out.println("Running tests");
        for(int i = 0; i < N.length; i++) {
            String fileToUse = fileName;
            if(N[i] < 10000) {
                fileToUse = fileName + 1;
            } else if(N[i] <= 100000) {

                fileToUse = fileName + 2;
            } else if(N[i] <= 1000000) {
                
                fileToUse = fileName + 3;
            }else {
            	return;
            }

            fileToUse += ".txt";
            String [] dataStrings = {"MyPQUnsortedArray",
                "MyPQUnsortedList",
                "MyPQSortedArray",
                "MyPQSortedList"
            };

            Long [] insertTimes = new Long[4], removeTimes = new Long[4];
            System.out.println("\tTesting " + N[i]);
            if(!testOn(N[i], fileToUse, insertTimes, removeTimes)) {
                System.out.printf("\tError in testing " + N[i] + "\n");
                break;
            }
            System.out.println("\tCompleted " + N[i] + "\n");
            // write data (tabulated)
            writer.write(line);
            writer.format("%-25s | %-20s | %-20s\n", "N = " + N[i], "Insert(k, v) (ms)", "RemoveMin(k, v) (ms)");
            for(int idx = 0; idx < dataStrings.length; idx++) {
                writer.format("%-25s | %-20s | %-20s\n", dataStrings[idx], insertTimes[idx], removeTimes[idx]);
            }
            writer.write("\n\n");
        }

        writer.close();
        System.out.println("Completed tests");
    }

    /***
     * Open file and read in N strings from it.
     * Assign random integers from [1, N] for each string and
     * insert the <Integer, String> pair into each of the priority queues.
     * @param N - number of strings to read from
     * @param fileToUse - file to get data from
     * @param insertTimes - time in ms to insert each pair
     * @param removeTimes - time in ms to remove each pair
     * @return
     */
    public static boolean testOn(
            int N, String fileToUse, Long[] insertTimes, Long[] removeTimes
    ) {
        // Create random keys
        Integer [] randomKeys = new Integer[N];
        populate(N, randomKeys);

        // Create instances of each priority queue
        MyPQUnsortedArray<Integer, String> pq0 = new MyPQUnsortedArray<Integer, String>();
        MyPQUnsortedList<Integer, String> pq1 = new MyPQUnsortedList<Integer, String>();
        MyPQSortedArray<Integer, String> pq2 = new MyPQSortedArray<Integer, String>();
        MyPQSortedList<Integer, String> pq3 = new MyPQSortedList<Integer, String>();

        // test all of them
        if(!testPQ(N, fileToUse, randomKeys, insertTimes, removeTimes, 0, pq0) ||!testPQ(N, fileToUse, randomKeys, insertTimes, removeTimes, 1, pq1)|| !testPQ(N, fileToUse, randomKeys, insertTimes, removeTimes, 2, pq2)||!testPQ(N, fileToUse, randomKeys, insertTimes, removeTimes, 3, pq3)) {
            return false;
        }

        return true;
    }

    /***
     * Populate keys with integers from 1, N
     * @param N - upper bound
     * @param keys - all keys
     */
    public static void populate(int N, Integer[] keys) {
        Random random = new Random();
        for(int i = 0; i < keys.length; i++) {
            keys[i] = random.nextInt(N) + 1;
        }
    }

    /***
     * Insert N Strings with given generated random keys into priority queue pq
     * @param N - number of strings to read from file
     * @param fileToUse - file name to process on
     * @param randomKeys - keys pre-generated
     * @param insertTimes - will populate total time (ms) needed to insert
     * @param removeTimes - will populate total time (ms) needed to remove
     * @param timeIdx - index into arrays
     * @param pq - priority queue to operate on
     * @return - true if success, false on failure
     */
    public static boolean testPQ(
        int N, String fileToUse, Integer[] randomKeys, Long[] insertTimes, Long[] removeTimes,
        int timeIdx, MyPQ<Integer, String> pq
    ) {
        System.out.printf("\t\tOn %d/%d\n", timeIdx, insertTimes.length - 1);
        // First, open the file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileToUse));
        } catch(FileNotFoundException e) {
            System.out.printf("\t\tCouldn't find %s\n", fileToUse);
            return false;
        }

        int idx = 0;
        Long totalInsertionTime = 0L, totalRemoveTime = 0L;
        Long startTime = 0L, endTime = 0L;
        System.out.println("\t\tPerforming insertions...");
        for(; idx < N && scanner.hasNextLine(); idx++) {
            String string = scanner.nextLine();
            startTime = System.currentTimeMillis();
            pq.insert(randomKeys[idx], string);
            endTime = System.currentTimeMillis();
            totalInsertionTime += (endTime - startTime);
        }
        scanner.close();

        if(idx < N) {
            // didn't read enough
            System.out.println("\t\tDidn't read enough");
            return false;
        }

        System.out.println("\t\tPerforming removals...");
        Pairs<Integer, String> lastPair = null;
        while(!pq.isEmpty()) {
            // time for only the removal
            startTime = System.currentTimeMillis();
            Pairs<Integer, String> now = pq.removeMin();
            endTime = System.currentTimeMillis();
            totalRemoveTime += endTime - startTime;
            if(now == null) {
                System.out.println("\t\tCouldn't remove");
                return false;
            }
        }
           
        insertTimes[timeIdx] = totalInsertionTime;
        removeTimes[timeIdx] = totalRemoveTime;
        System.out.printf("\t\tCompleted %d/%d\n", timeIdx, insertTimes.length - 1);
        return true;
        

}
}