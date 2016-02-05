import java.util.Random;

/**
 * Created by John Purviance on 2/5/16.
 * Simulates preforming a linear search over the system memory.
 */
public class LinearSearchSim implements SimulationJob{ // TODO make search interface

    public LinearSearchSim(){}

    @Override
    public void run(Memory storage) {
        Random number_generator = new Random();
        this.search(number_generator.nextInt(1000), storage);
    }

    public int run(Memory storage, int search_for){
        return this.search(search_for, storage);
    }
    private int search(int search_for, Memory storage){

        for (int i = 0; i < storage.get_size(); i++){
            if (storage.get_at_address(i) == search_for){
                return i;
            }
        }
        return -1;
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
