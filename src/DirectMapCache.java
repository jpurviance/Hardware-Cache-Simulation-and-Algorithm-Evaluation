/**
 * Created by John on 12/24/15.
 * Direct map cache for testing the affects of algorithms on caches.
 * Stores up to 8 words at a time. Monitors total reads, writes and accesses.
 */
public class DirectMapCache implements Cache {
    private static final int INDEX_BIT_LENGHT = 3;
    private static final int TAB_BIT_LENGHT = 2;
    private static final int BINARY_ADDRESS_BIT_LENGTH = 5;
    private static final int INDEX_MASK = 7;
    private static final int TAG_MASK = 24;
    private int data[];
    private boolean valid[];
    private int tag[];
    private int total_cache_accesses;
    private int total_cache_reads;
    private int total_cache_writes;
    private int size;

    public DirectMapCache() {
        this.size = (int) Math.pow(2, this.INDEX_BIT_LENGHT);
        this.data = new int[this.size];
        this.valid = new boolean[this.size];
        this.tag = new int[this.size];
        this.total_cache_accesses = 0;
        this.total_cache_reads = 0;
        this.total_cache_writes = 0;

        for (int i = 0; i < this.size; i++) {
            this.valid[i] = false;
            this.data[i] = 0;
            this.tag[i] = 0;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // No Analytics, this section does not count towards accesses, reads, writes, hits and misses.

    /**
     * Validates if the memory address is in the cache.
     *
     * @param mem_address the address to validate
     * @return if the address is in the cache.
     */
    public boolean validate_address(int mem_address) {
        if (mem_address > 31 || mem_address < 0) {
            return false;
        } else {
            return true;
        }
    }

    public int size() {
        return this.size;
    }

    /**
     * Computes an integer index that a memory address will map to in the cache. Does not validate if the memory address is in the cache.
     *
     * @param mem_address the address to find the index for
     * @return index address maps to in the cache.
     */
    public int compute_index(int mem_address) {
        return mem_address & this.INDEX_MASK;
    }


    /**
     * Computes a integer tag that a memory address maps to in the cache. Does not validate if the memory address is in the cache.
     *
     * @param mem_address the address to find the tag for.
     * @return an integer that the address will have in the cache.
     */
    public int compute_tag(int mem_address) {
        return (mem_address & this.TAG_MASK) >> this.INDEX_BIT_LENGHT;
    }

    public int get_total_cache_accesses() {
        return this.total_cache_accesses;
    }

    public int get_total_cache_reads() {
        return this.total_cache_reads;
    }

    public int get_total_cache_writes() {
        return this.total_cache_writes;
    }

    /**
     * Retrieve the tag at the particular index. Does not check if the index is valid for the cache.
     * Does not effect total cache reads, write, access...
     *
     * @param index the index to retrieve the tag from.
     * @return tag at that index.
     */
    public int no_analytics_get_tag(int index) {
        return this.tag[index];
    }

    /**
     * Get the data at a particular memory address. Does not check if the address is valid.
     * Does not effect total cache reads, write, access...
     *
     * @param mem_address the memory address to get data from.
     * @return the data at the memory address.
     */
    public int no_analytics_get_data(int mem_address) {
        return this.data[this.compute_index(mem_address)];
    }

    /**
     * Determine if the data at a particular index is valid.
     * Does not effect total cache reads, write, access...
     *
     * @param index the index to check
     * @return if the index is valid.
     */
    public boolean no_analytics_is_index_valid(int index) {
        return this.valid[index];
    }


    /**
     * Set the tag at a particular index. Does not check if the index is valid.
     * Does not effect total cache reads, write, access...
     *
     * @param index     the index to se the tag at.
     * @param tag_value the value for the tag.
     */
    public void no_analytics_set_tag(int index, int tag_value) {
        this.tag[index] = tag_value;
    }

    // takes a mem address for what the new data should be, based of this it finds what should be written back
    public int get_write_back_address(int mem_address) throws CacheColdMissException {
        int index = this.compute_index(mem_address);
        if (!is_cold_at_index(index)) {
            int address_tag = this.tag[index];
            address_tag = address_tag << this.INDEX_BIT_LENGHT;
            return index | address_tag;
        } else {
            throw new CacheColdMissException();
        }
    }

    public int no_analytics_get_data_at_index(int index) {
        return this.data[index];
    }


    /**
     * Used for setting data when cache reads, writes, accesses... should not be tracked. Does not update tag or validity
     *
     * @param index the index that the data should be set at.
     * @param data  the data to be put at the index.
     */
    public void no_analytics_set_data(int index, int data) {
        this.data[index] = data;
    }


    /**
     * Used for setting index validity when cache reads, writes, accesses... should not be tracked.
     * Does not check if the index is valid.
     *
     * @param index the index to set the value at.
     * @param set   the boolean value of if the data at index is valid.
     */
    public void no_analytics_set_validity(int index, boolean set) {
        this.valid[index] = set;
    }

    /**
     * On startup a cache is cold, this method returns a boolean for if the data is bad at an address because the cache is cold.
     *
     * @param index the index to check if data is cold.
     * @return if the cache is cold at address.
     */
    public boolean is_cold_at_index(int index) {
        return !this.valid[index];
    }


    /**
     * Determines if the memory address is being cached.
     *
     * @param mem_address the memory address to check.
     * @return if that memory address is being cached.
     */
    public boolean is_in_cache(int mem_address) {
        if (this.validate_address(mem_address)) {
            int index = this.compute_index(mem_address);
            if (!this.valid[index]) {
                return false;
            }
            if (this.tag[index] != this.compute_tag(mem_address)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // These are the methods for interfacing with the cache when analytics mater.

    /**
     * Set the data at a memory address, returns true if the data was set at that dress, false if it was not.
     * Will return false if the address is not a valid address for the cache.
     *
     * @param mem_address the memory address that should be cached.
     * @param data        the data that is to be cached for that particular memory address.
     */
    public void set_data(int mem_address, int data) { // validate by checking if set in cache
        int index = this.compute_index(mem_address);
        this.valid[index] = true;
        this.data[index] = data;
        this.tag[index] = this.compute_tag(mem_address);
        this.total_cache_accesses++;
        this.total_cache_writes++;
    }

    /**
     * USERS ARE EXPECTED TO VALIDATE ADDRESS
     * Use validate_address(int mem_address) to determine if the address is valid.
     *
     * @param mem_address the address to get cached data from.
     * @return the value at a cached memory address.
     */
    public int get_data(int mem_address) { // TODO all get_data usages should have is_in_cache checked.
        this.total_cache_accesses++;
        this.total_cache_reads++;
        return this.data[this.compute_index(mem_address)];
    }


}
