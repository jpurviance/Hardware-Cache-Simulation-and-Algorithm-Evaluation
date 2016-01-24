/**
 * Created by John Purviance on 12/30/15.
 * Types of simulations supported: BubbleSort, BinarySearch, HeapSort, InsertionSort, LinearRead, QuickSort;
 */
public enum RunnableSim {
    BubbleSort, BinarySearch,
    HeapSort, InsertionSort,
    LinearRead, QuickSort;

    /**
     * String representation of the enum.
     * @return Human readable string.
     */
    @Override
    public String toString() {
        switch (this){
            case BubbleSort:
                return "Bubble Sort";
            case BinarySearch:
                return "Binary Search";
            case HeapSort:
                return "Heap Sort";
            case InsertionSort:
                return "Insertion Sort";
            case LinearRead:
                return "linear Read";
            case QuickSort:
                return "Quick Sort";
        }
        return "Test Fail";

    }
}
