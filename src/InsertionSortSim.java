import java.util.Random;

/**
 * Created by John Purviance on 12/22/15.
 * Simulate soring integers on system memory with Insertion Sort.
 */
public class InsertionSortSim implements SimulationJob {

    /**
     * New instance of Insertion Sort Simulation
     */
    public InsertionSortSim() {
    }

    /**
     * Sort the integers in the storage with insertions sort and then flush values to ram.
     *
     * @param storage
     */
    @Override
    public void run(Memory storage) {
        this.sort(storage);
        storage.flush_caches_to_ram();

    }

    /*
    sort the stage with insertion sort.
     */
    private void sort(Memory storage) {
        for (int i = 1; i < storage.get_size(); i++) {
            for (int j = i; (j > 0) && (storage.get_at_address(j) < storage.get_at_address(j - 1)); j--) {
                int temp = storage.get_at_address(j);
                storage.set_at_address(j, storage.get_at_address(j - 1));
                storage.set_at_address(j - 1, temp);
            }
        }
    }


    /**
     * Fill the storage with integers from 1 to 100 (inclusive)
     *
     * @param storage
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
