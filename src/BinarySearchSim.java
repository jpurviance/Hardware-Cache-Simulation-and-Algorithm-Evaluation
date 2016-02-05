import java.util.Random;

/**
 * Created by John Purviance on 12/22/15.
 * Simulates preforming a binary search over the system memory.
 */
public class BinarySearchSim implements SimulationJob {

    public BinarySearchSim() {}

    @Override
    public void run(Memory storage){
        Random number_generator = new Random();
        this.search(storage, number_generator.nextInt(1000), 0, storage.get_size() - 1); // search between 0 and 999
    }

    public int run(Memory storage, int search){
        return this.search(storage, search, 0, storage.get_size() - 1);
    }


    private int search(Memory storage, int find, int low_index, int high_index) {
        if (high_index < low_index) {
            return -1;
        } else {
            int compare_index = (low_index + high_index) / 2;
            int compare_to = storage.get_at_address(compare_index);
            if (find == compare_to) {
                return compare_index;
            } else if (find < compare_to) {
                return this.search(storage, find, low_index, compare_index - 1);
            } else {
                return this.search(storage, find, compare_index + 1, high_index);
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
