/**
 * Created by John on 12/22/15.
 */

import java.util.Random;
public class LinearReadSim implements SimulationJob {
    private Memory storage;

   public LinearReadSim(){
       this.storage = new Memory(CacheType.DIRECT_MAP);
   }


    @Override
    public void run(Memory storage) {
        for (int i = 0; i < storage.get_size(); i++){
            System.out.println(storage.get_at_address(i));
        }
        System.out.println("Ram was accesses: "+ this.get_ram_accesses()+" times");
    }

    public int get_ram_accesses() {
        return storage.get_ram_accesses();
    }

    @Override
    public void prime(Memory storage) {
        Random number_generator = new Random();
        int random_int = number_generator.nextInt(100);
        random_int++; // make the int between 1 and 100 since nextInt(int) is exclusive.
        for (int i = 0; i<storage.get_size(); i++){
            storage.no_analytics_set_at_address_in_RAM(i, random_int);
            random_int = number_generator.nextInt(100);
            random_int++;
        }
    }

}
