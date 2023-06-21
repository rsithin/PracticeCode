import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithms {
    /*
     * This program creates an array randomly. It outputs the random array, then 
     * runs each sorting algorithm and outputs the sorted result, reshuffling
     * the array again after each sort before running the next algorithm. If successful,
     * each sort outputs the exact same sorted array sequence.
     */
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        // SecureRandom r = new SecureRandom();

        int size = 50;
        int[] a = new int[size];

        for (int i = 0; i < a.length; i++)
            a[i] = r.nextInt(100);

        printout("Random data: ", a);

        insertionSort(a);
        printout("Insertion Sort: ", a);

        shuffle(a);
        //printout("Shuffled data: ", a);

        bubbleSort(a);
        printout("Bubble Sort data: ", a);

        shuffle(a);
        //printout("Shuffled data: ", a);

        mergeSort(a, 0, a.length - 1);
        printout("Merge Sort data: ", a);

    }

    /*
     * This method is the implementation insertion sort algorithm
     * to sort the array
     */
    public static void insertionSort(int[] a) {

        // The variables as indexes of values we will be comparing are initialized
        int i = 0;
        int j = 0;

        // The key is what we are comparing
        int key;

        // Loop to check key and value to compare
        for (j = 1; j < a.length; j++) {
            key = a[j];
            i = j - 1;

            // If value at i is greater than key, move value at i in front of key and decrement
            while (i >= 0 && a[i] > key) {
                a[i + 1] = a[i];
                i -= 1;
            }
            // If value at i is less than key, put key after value at i
            a[i + 1] = key;

        }

    }

    /*
     * This method is the basic bubble sort algorithm
     */
    public static void bubbleSort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }

        }

    }

    /*
     * This method merges the array in ascending order
     */
    public static void merge(int[] a, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = a[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = a[q + j + 1];
        }

        int i = 0, j = 0;

        int temp = p;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[temp] = L[i];
                i++;
            } else {
                a[temp] = R[j];
                j++;
            }
            temp++;
        }

        while (i < n1) {
            a[temp] = L[i];
            i++;
            temp++;
        }

        while (j < n2) {
            a[temp] = R[j];
            j++;
            temp++;
        }

    }

    /*
     * This method splits the array, recursively calling mergeSort
     * until the array is separated out into single element arrays
     * and then merges them back in ascending order with the merge method
     */
    public static void mergeSort(int[] a, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }

    }

    /*
     * This method outputs the array passed to it with a new line
     */
    public static void printOutput(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println("");
    }


    /*
     * This method shuffles the array passed to it.
     */
    public static void shuffle(int[] ar) {
        Random r = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    /*
     * This method prints out string passed to it with padding
     * to line up the string output
     */
    public static void printout(String s, int[] a) {
        System.out.printf("%25s", s);
        printOutput(a);
    }
}
