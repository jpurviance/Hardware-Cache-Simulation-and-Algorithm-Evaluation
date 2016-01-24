import java.util.Random;

/**
 * Created by John Purviance on 12/27/15.
 *
 * Simulates Sorting all of the elements in memory with bubble sort.
 */
public class BubbleSortSim implements SimulationJob {

    /**
     * Makes a new test instance.
     */
    public BubbleSortSim() {
    }


    /**
     * Run the simulation on the given memory configuration.
     *
     * @param storage the memory configuration to sort.
     */
    @Override
    public void run(Memory storage) {
        this.sort(storage);
        storage.flush_caches_to_ram(); // have the ram reflect the values in the cache for testing.
    }

    /**
     * Sort all of the elements in the system memory with bubble sort.
     *
     * @param storage The system memory to sort.
     */
    private void sort(Memory storage) {
        boolean swapped = true;
        int j = 0;
        int temp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < storage.get_size() - j; i++) {
                if (storage.get_at_address(i) > storage.get_at_address(i + 1)) {
                    temp = storage.get_at_address(i);
                    storage.set_at_address(i, storage.get_at_address(i + 1));
                    storage.set_at_address(i + 1, temp);
                    swapped = true;
                }
            }
        }
    }

    /**
     * Fill the system memory with random integers for sorting.
     *
     * @param storage The system memory to fill with integers.
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
