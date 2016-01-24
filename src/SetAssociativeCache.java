/**
 * Created by John Purviance on 1/4/16.
 * Cache composed of four sets, each set is associative. Cache keeps track of reads, writes and total accesses.
 */
public class SetAssociativeCache implements Cache { // uses least recently used eviction policy.
    private static final int INDEX_MASK = 6; // in the digit 11111 we want the bits (repesented by X) 11XX1
    private static final int INDEX_BIT_LENGHT = 3;
    private Set[] sets;
    private int total_cache_accesses;
    private int total_cache_reads;
    private int total_cache_writes;
    /**
     * New set associative cache with four sets
     */
    public SetAssociativeCache() {
        this.total_cache_accesses = 0;
        this.total_cache_reads = 0;
        this.total_cache_writes = 0;
        this.sets = new Set[4]; // twice as many memory address map to these now.
        for (int i = 0; i < this.sets.length; i++) {
            this.sets[i] = new Set(2);
        }
    }

    /**
     * Determine if the address is valid for the cache.
     *
     * @param mem_address validate
     * @return is valid
     */
    public boolean validate_address(int mem_address) {
        if (mem_address > 32 || mem_address < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * number of items that can be stored in the cache at once.
     *
     * @return number of items that can fit in cache.
     */
    public int size() {
        return this.sets.length * 2;
    }

    /**
     * Compute which set an address maps to.
     *
     * @param mem_address address to find index for
     * @return index.
     */
    public int compute_index(int mem_address) {
        return (mem_address & this.INDEX_MASK) / 2; // TODO test
    }

    /**
     * USERS ARE EXPECTED TO VALIDATE MEMORY ADDRESSES.
     * Place data in cache
     *
     * @param mem_address where data should be placed
     * @param data
     */
    @Override
    public void set_data(int mem_address, int data) {
        this.total_cache_writes++;
        this.total_cache_accesses++;
        int index = this.compute_index(mem_address);
        this.sets[index].set_data(mem_address, data);
    }

    /**
     * Get data from cache without tracking access or reads
     * USERS ARE EXPECTED TO VALIDATE ADDRESSES
     *
     * @param mem_address address to get from
     * @return value at address
     */
    @Override
    public int no_analytics_get_data(int mem_address) {
        return this.sets[this.compute_index(mem_address)].get_data(mem_address);
    }

    /**
     * Number of times cache was accessed
     *
     * @return number of accesses.
     */
    @Override
    public int get_total_cache_accesses() {
        return this.total_cache_accesses;
    }

    /**
     * Number of of times cache was read from.
     *
     * @return number of cache reads.
     */
    @Override
    public int get_total_cache_reads() {
        return this.total_cache_reads;
    }

    /**
     * Number of times cache was written to.
     *
     * @return number of cache writes.
     */
    @Override
    public int get_total_cache_writes() {
        return this.total_cache_writes;
    }

    /**
     * Determine if value is in cache.
     *
     * @param mem_address address of value
     * @return if value is in cache.
     */
    @Override
    public boolean is_in_cache(int mem_address) {
        int index = this.compute_index(mem_address);
        return this.sets[index].is_in_cache(mem_address);
    }

    /**
     * Get data from cache.
     * USERS ARE EXPECTED TO VALIDATE ADDRESSES.
     *
     * @param mem_address address to retrieve from.
     * @return value at address.
     */
    @Override
    public int get_data(int mem_address) {
        this.total_cache_accesses++;
        this.total_cache_reads++;
        return this.sets[this.compute_index(mem_address)].get_data(mem_address);
    }

    /**
     * Address of value that is to be written back to ram.
     *
     * @param mem_address the new value to be placed in cache.
     * @return the value of the address to be written back to.
     * @throws CacheColdMissException if there is space in the cache for the new memory address.
     */
    @Override
    public int get_write_back_address(int mem_address) throws CacheColdMissException {
        int index = this.compute_index(mem_address);
        return this.sets[index].get_write_back_address(mem_address);
    }

    //TODO there should be two sets.
    /*
    Each set is actually a fully associative cache.
     */
    private class Set extends AssociativeCache {

        /*
         * make a new set.
         */
        public Set(int size) {
            super(size);
        }
    }
}