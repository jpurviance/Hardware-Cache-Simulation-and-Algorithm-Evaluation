import java.util.Random;

/**
 * TODO class not ready to simulation usage.
 * Created by John Purviance on 12/22/15.
 * Simulates preforming a binary search over a cache compared.
 * TODO: need to be able to take in user dictated search values.
 */
public class BinarySearchSim implements SimulationJob { // TODO finish implementing run and test class.
    private Memory storage;

    public BinarySearchSim() {
        this.storage = new Memory(CacheType.DIRECT_MAP); // TODO this class should take in a memory configuration to run on.
    }

    @Override
    public void run(Memory storage) {
        // TODO when finished this should be able to search for user dictated values.
        this.search(10, 0, this.storage.get_size() - 1);
    }

    private int search(int find, int low_index, int high_index) {
        if (high_index < low_index) {
            return -1;
        } else {
            int compare_index = (low_index + high_index) / 2;
            int compare_to = this.storage.get_at_address(compare_index);
            if (find == compare_to) {
                return compare_index;
            } else if (find < compare_to) {
                return this.search(find, low_index, compare_index - 1);
            } else {
                return this.search(find, compare_index + 1, high_index);
            }
        }
    }

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
