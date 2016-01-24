import java.util.Random;

/**
 * Created by John Purviance on 12/29/15.
 * Simulates a Heap sort algorithm on memory.
 */
public class HeapSortSim implements SimulationJob {

    /**
     * Generate new Heap Sort Simulation instance.
     */
    public HeapSortSim() {
    }

    /**
     * Sorts the given memory configuration and flushes values to ram from caches.
     *
     * @param storage storage configuration to sort.
     */
    @Override
    public void run(Memory storage) {
        this.sort(storage);
        storage.flush_caches_to_ram();
    }

    /*
    Build a new heap from a give storage object and then sort that heap.
     */
    private void sort(Memory storage) {
        this.build_heap(storage);
        this.sort_heap(storage);
    }

    /*
    Transforms storage into a heap.
     */
    private void build_heap(Memory storage) {
        // Credit: Code obtained from Data Structures Abstraction and Design Using Java
        // By: Elliot Koffman and Paul Wolfgang
        int n = 1;
        while (n < storage.get_size()) {
            n++;
            int child = n - 1;
            int parent = (child - 1) / 2;
            while (parent >= 0 && (storage.get_at_address(parent) < storage.get_at_address(child))) {
                int temp = storage.get_at_address(child);
                storage.set_at_address(child, storage.get_at_address(parent));
                storage.set_at_address(parent, temp);
                child = parent;
                parent = (child - 1) / 2;
            }
        }

    }

    /*
    Sort the given heap by taken the value from the top of the heap and moving to the bottom, then reduce the size of
    the heap by one. Finalyy, rebuild the heap. When the heap has one item left in it then the storage has been sorted.
     */
    private void sort_heap(Memory storage) {
        // Credit: Code obtained from Data Structures Abstraction and Design Using Java
        // By: Elliot Koffman and Paul Wolfgang
        int left_to_sort = storage.get_size();
        while (left_to_sort > 0) {
            left_to_sort--;
            int temp = storage.get_at_address(0);
            storage.set_at_address(0, storage.get_at_address(left_to_sort));
            storage.set_at_address(left_to_sort, temp);

            int parent = 0;
            while (true) {
                int left_child = 2 * parent + 1;
                if (left_child >= left_to_sort) {
                    break;
                }
                int right_child = left_child + 1;
                int max_child = left_child;
                if (right_child < left_to_sort && storage.get_at_address(left_child) < storage.get_at_address(right_child)) {
                    max_child = right_child;
                }

                if (storage.get_at_address(parent) < storage.get_at_address(max_child)) {
                    int swap = storage.get_at_address(max_child);
                    storage.set_at_address(max_child, storage.get_at_address(parent));
                    storage.set_at_address(parent, swap);
                    parent = max_child;
                } else {
                    break;
                }

            }
        }
    }

    /**
     * Fill storage with random integers between 1 and 100 (inclusive).
     *
     * @param storage fill with random integers.
     */
    @Override
    public void prime(Memory storage) {
        Random number_generator = new Random();
        int random_int = number_generator.nextInt(100);
        random_int++; // make the int between 1 and 100 since nextInt(int) is exclusive.
        for (int i = 0; i < storage.get_size(); i++) {
            storage.no_analytics_set_at_address_in_RAM(i, random_int);
            random_int = number_generator.nextInt(100);
            random_int++;
        }
    }
}
