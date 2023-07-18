import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithms {
    /*
     * This program creates an array randomly. It outputs the random array, then
     * runs each sorting algorithm and outputs the sorted result, reshuffling
     * the array again after each sort before running the next algorithm. If
     * successful,
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
        System.out.println("");
        System.out.println("Sort Algorithms:");
        System.out.println("");

        insertionSort(a);
        printout("Insertion Sort: ", a);

        shuffle(a);
        // printout("Shuffled data: ", a);

        bubbleSort(a);
        printout("Bubble Sort data: ", a);

        shuffle(a);
        // printout("Shuffled data: ", a);

        mergeSort(a, 0, a.length - 1);
        printout("Merge Sort data: ", a);

        shuffle(a);
        int[] aSize = new int[a.length];
        for(int i = 0;i<aSize.length;i++) {
            aSize[i] = i;
        }

        System.out.println("");
        System.out.println("Search Algorithms:");
        System.out.println("");
        printout("Index: ", aSize);
        printout("Shuffled data: ", a);

        System.out.println("");

        int index = r.nextInt(a.length);
        int record = a[index];
        System.out.println("Index to search: " + index);
        System.out.println("Element at index: " + record);

        int result = linearSearch(a, record);
        printSearch(record, result);

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

            // If value at i is greater than key, move value at i in front of key and
            // decrement
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
     * This method performs a linear search for a record starting from index 0.
     * Data can be unsorted.
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int linearSearch(int arr[], int rec) {
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == rec) {
                return index;
            }
        }

        return -1;
    }

    /*
     * This method performs a binary search, a divide and conquer search algorithm.
     * Sorted data required. This can be implemented recursively or iteratively.
     */
    public static int iterativeBinarySearch(int arr[], int rec) {
        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;

            if (arr[middleIndex] == rec) {
                return middleIndex;
            }

            else if (arr[middleIndex] < rec) {
                firstIndex = middleIndex + 1;
            }

            else if (arr[middleIndex] > rec) {
                lastIndex = middleIndex - 1;
            }
        }

        return -1;
    }

    public static int recursiveBinarySearch(int arr[], int firstElement, int lastElement, int rec) {
        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            if (arr[mid] == rec) {
                return mid;
            }

            if (arr[mid] > rec) {
                return recursiveBinarySearch(arr, firstElement, mid - 1, rec);
            }

            return recursiveBinarySearch(arr, mid + 1, lastElement, rec);
        }
        return -1;
    }

    /*
     * This method outputs the array passed to it with a new line
     */
    public static void printOutput(int[] a) {
        for (int i : a)
            System.out.printf("%3s",i);
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

    public static void printSearch(int rec, int index) {
        if (index == -1) {
            System.out.println(rec + " not found.");
        } else {
            System.out.println(rec + " found at index: " + index);
        }
    }
}
