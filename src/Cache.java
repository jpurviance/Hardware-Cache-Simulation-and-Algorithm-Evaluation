/**
 * Created by John Purviance on 12/22/15.
 *
 * Interface that all caches must abide by so that write-back and write-though cache schemes can be easily implemented on all caches.
 */
public interface Cache {
    int get_total_cache_accesses();

    int get_total_cache_reads();

    int get_total_cache_writes();


    boolean is_in_cache(int mem_address);

    int get_data(int mem_address);

    int get_write_back_address(int mem_address) throws CacheColdMissException; // TODO what happens when you pass the address that should be written back?

    void set_data(int mem_address, int data);

    int no_analytics_get_data(int mem_address); // test

    boolean validate_address(int mem_address);

    int size();

    int write_back_get_data(int mem_address);


}
