import java.util.Random;

/**
 * Created by John Purviance on 12/28/15.
 * Simulates soring integers in system memory with quicksort. Flushes value to ram after sorting.
 */
public class QuickSortSim implements SimulationJob {

    /**
     * new sort object
     */
    public QuickSortSim() {
    }


    /**
     * Sort values in system memory and then flush values to ram.
     *
     * @param storage memory to have values sorted.
     */
    @Override
    public void run(Memory storage) {
        this.sort(storage, 0, storage.get_size() - 1);
        storage.flush_caches_to_ram();
    }

    /*
    sort the values in the system memory, low index is bottom end of the range to sort, high is the top end.
     */
    private void sort(Memory storage, int low, int high) {
        int pivot = storage.get_at_address(low + (high - low) / 2);
        int i = low;
        int j = high;

        while (i <= j) {
            while (storage.get_at_address(i) < pivot) {
                i++;
            }
            while (storage.get_at_address(j) > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = storage.get_at_address(i);
                storage.set_at_address(i, storage.get_at_address(j));
                storage.set_at_address(j, temp);

                i++;
                j--;
            }
        }
        if (low < j) {
            this.sort(storage, low, j);
        }
        if (i < high) {
            this.sort(storage, i, high);
        }
    }


    /**
     * Fill the system memory with random integers between 1 and 100 (inclusive).
     *
     * @param storage memory to be filled with random values.
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
