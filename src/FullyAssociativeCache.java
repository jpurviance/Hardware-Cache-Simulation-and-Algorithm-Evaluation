/**
 * Created by John Purviance on 1/5/16.
 * Fully Associative cache for testing different algorithm efficiencies.
 * Stores a user defined amount of words.
 */
public class FullyAssociativeCache extends AssociativeCache {
    /*
    Many methods are inherited from the parent class AssociativeCache.
     */
    private int total_cache_accesses;
    private int total_cache_reads;
    private int total_cache_writes;

    /**
     * Create a new cache of a set size.
     *
     * @param size The size the cache should be.
     */
    public FullyAssociativeCache(int size) {
        super(size);
        this.total_cache_accesses = 0;
        this.total_cache_reads = 0;
        this.total_cache_writes = 0;
    }

    /**
     * Retrieve the total number of times that the cache is read from and written to.
     *
     * @return Number of cache accesses.
     */
    @Override
    public int get_total_cache_accesses() {
        return this.total_cache_accesses;
    }

    /**
     * Retrieve the total number of cache reads form the cache.
     *
     * @return number of cache reads
     */
    @Override
    public int get_total_cache_reads() {
        return this.total_cache_reads;
    }

    /**
     * Retrive number of cache writes.
     *
     * @return Number of times the cache is written to.
     */
    @Override
    public int get_total_cache_writes() {
        return this.total_cache_writes;
    }

    /**
     * USERS ARE EXPECTED TO VALIDATE MEMORY ADDRESSES. Put a value in the cache.
     *
     * @param mem_address memory address to be cached.
     * @param data        data to be cached.
     */
    @Override
    public void set_data(int mem_address, int data) {
        this.total_cache_writes++;
        this.total_cache_accesses++;
        super.set_data(mem_address, data);
    }

    //TODO doc
    @Override
    public int write_back_get_data(int mem_address){
        this.total_cache_accesses++;
        this.total_cache_reads++;
        return super.write_back_get_data(mem_address);
    }


    /**
     * USERS ARE EXPECTED TO VALIDATE MEMORY ADDRESS AND CHECK TO SEE IF THE VALUE IS IN THE CACHE.
     *
     * @param mem_address The cached memory address.
     * @return
     */
    @Override
    public int get_data(int mem_address) {
        this.total_cache_accesses++;
        this.total_cache_reads++;
        return super.get_data(mem_address);
    }
}
