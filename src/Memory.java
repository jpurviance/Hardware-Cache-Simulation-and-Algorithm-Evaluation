/**
 * Created by John Purviance on 12/22/15.
 * System memory configuration with write_back cache scheme for writing values
 * back into ram when evictions need to happen
 */
//useful link http://web.cs.iastate.edu/~prabhu/Tutorial/CACHE/interac.html
public class Memory {
    private Ram ram;
    private Cache cache;
    private int total_accesses;
    private int total_reads;
    private int total_writes;

    private int total_cache_misses;
    private int total_cache_hits;
    private int total_cache_read_hits;
    private int total_cache_read_misses;
    private int total_cache_write_hits;
    private int total_cache_write_misses;
    private int total_cache_cold_read_misses;
    private int total_cache_cold_write_hits;
    private int total_cache_write_backs;
    private int size;

    /**
     * New system memory with a single layer of caches.
     * THE DEFAULT VALUE FOR SYSTEM CACHE IS DIRECT MAP.
     *
     * @param cache_type Type of cache that should be used for system ram.
     */
    public Memory(CacheType cache_type) {
        switch (cache_type) {
            case FULLY_ASSOCIATIVE:
                this.cache = new FullyAssociativeCache(8);
                break;
            case SET_ASSOCIATIVE:
                this.cache = new SetAssociativeCache();
                break;
            default:
                this.cache = new DirectMapCache();
                break;

        }
        this.size = 32;
        this.ram = new Ram(32); // TODO nove to construte as argument then pass values to ram and caches for size.
        this.total_accesses = 0;
        this.total_reads = 0;
        this.total_writes = 0;
        this.total_cache_hits = 0;
        this.total_cache_read_hits = 0;
        this.total_cache_read_misses = 0;
        this.total_cache_misses = 0;
        this.total_cache_cold_read_misses = 0;
        this.total_cache_write_hits = 0;
        this.total_cache_cold_write_hits = 0;
        this.total_cache_write_backs = 0;
        this.total_cache_write_misses = 0;
    }

    /**
     * Use to retrieve usage information for memory, cache and system ram.
     *
     * @return Memory usage object with detailed information about memory, cache and ram accesses and conflicts.
     */
    public MemoryUtilization get_memory_utilization() {
        MemoryUtilization mem_util = new MemoryUtilization(this.total_accesses, this.total_reads, this.total_writes);
        mem_util.set_set_ram_utilization(this.ram.get_ram_accesses(), this.ram.get_ram_reads(), this.ram.get_ram_writes());
        mem_util.set_cache_utilization(this.cache.get_total_cache_accesses(), this.cache.get_total_cache_reads(),
                this.cache.get_total_cache_writes(), this.total_cache_misses, this.total_cache_hits,
                this.total_cache_read_hits, this.total_cache_read_misses, this.total_cache_write_hits,
                this.total_cache_write_misses, this.total_cache_cold_read_misses, this.total_cache_cold_write_hits,
                this.total_cache_write_backs);
        return mem_util;
    }

    /**
     * Retrieve data form the system memeory. Tries to access values from cache first before going to system ram.
     * Uses Write-Back cache scheme. Evicts values when there is no space in the cache.
     *
     * @param address
     * @return
     */
    public int get_at_address(int address) {
        this.total_reads++;
        this.total_accesses++;
        if (this.cache.is_in_cache(address)) { // The value is in the cache. Retrieve it from there.
            int ret = this.cache.get_data(address);
            this.total_cache_hits++;
            this.total_cache_read_hits++;
            return ret;
        } else { // The value is not in the cache, try to remove another value and place a new one in the spot.
            int requested = this.ram.get_at_address(address);
            this.total_cache_misses++;
            this.total_cache_read_misses++;
            try {
                int write_back_address = this.cache.get_write_back_address(address);
                //int write_back_data = this.cache.get_data(write_back_address);
                int write_back_data = this.cache.write_back_get_data(write_back_address);
                this.ram.set_at_address(write_back_address, write_back_data);
                this.cache.set_data(address, requested);
            } catch (CacheColdMissException e) { // Did not need to remove a value from the cache, there is an open spot in the cache.
                cache.set_data(address, requested);
                this.total_cache_cold_read_misses++;
            }
            return requested;
        }


    }

    public String cache_to_string(){
       return this.cache.toString();
    }


    /**
     * Set data in the system memory, will write values into cache. If no space is available in memory,
     * evict data in the new values spot back to the cache. Uses a Write-Back cache scheme.
     *
     * @param address location to write the value
     * @param value   value to be written into the cache.
     * @return if the data was set
     */
    public boolean set_at_address(int address, int value) { // valiate you own address.
        if (address >= 0 && address < this.size) {
            this.total_writes++;
            this.total_accesses++;
            if (this.cache.is_in_cache(address)) { // its in the cache, lets just write there.
                this.total_cache_hits++;
                this.total_cache_write_hits++;
                this.cache.set_data(address, value);
            } else { // we assume that we need to do a write back
                try {
                    int write_back_address = this.cache.get_write_back_address(address);
                    this.ram.set_at_address(write_back_address, this.cache.write_back_get_data(write_back_address));
                    this.cache.set_data(address, value);
                    this.total_cache_write_backs++;
                    this.total_cache_misses++;
                    this.total_cache_write_misses++;
                } catch (CacheColdMissException e) { // it was cold, so we do have space for it.
                    this.cache.set_data(address, value);
                    this.total_cache_cold_write_hits++;
                    this.total_cache_write_hits++;
                    this.total_cache_hits++;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Size of system memory.
     *
     * @return size of systme memeory.
     */
    public int get_size() {
        return this.size;
    }

    public boolean is_address_in_cache(int mem_address){
        return this.cache.is_in_cache(mem_address);
    }

    /**
     * Push all values from the caches into the ram. Forces ram to reflect the status of the cache.
     */
    public void flush_caches_to_ram() {
        for (int i = 0; i < 32; i++) {
            if (this.cache.is_in_cache(i)) {
                int data = this.cache.no_analytics_get_data(i);
                this.ram.no_analytics_set_at_address(i, data);
            }
        }
    }


    /**
     * IF THE VALUES IN THE CACHE HAVE NOT BEEN FLUSHED THE RETURN VALUE FROM THIS
     * METHOD MAY NOT REFLECT THE VALUE IN THE CACHE.
     * Get value from system RAM skipping the caches.
     *
     * @param address the address to retrieve a value form .
     * @return value at address.
     */
    public int no_analytics_get_from_RAM(int address) {
        if (address >= 0 && address < this.size) {
            return ram.no_analytics_get_at_address(address);
        } else {
            return 0;
        }
    }

    /**
     * GET A VALUE FROM THE CACHE, THIS VALUE MAY NOT RELFECT THE VALUES IN THE SYSTEM RAM IF THE CACHES HAVE NOT
     * BEEN FLUSHED. USERS ARE EXPECTED TO VALIDATE THAT THE VALUE IS IN THE CACHE.
     *
     * @param address memory address to retrieve the value from.
     * @return the value at that address.
     */
    public int no_analytics_get_from_cache(int address) {
        return this.cache.no_analytics_get_data(address);
    }

    /**
     * SKIPS THE CACHE AND SETS A VALUE DIRECTLY IN THE RAM. CACHE MAY BECOME OUT OF SYNC WITH THE RAM.
     * Set a value in the system ram.
     *
     * @param address address to set the value at.
     * @param value   value to be stored.
     * @return if the value was stored.
     */
    public boolean no_analytics_set_at_address_in_RAM(int address, int value) {
        if (address >= 0 && address < this.size) {
            return ram.no_analytics_set_at_address(address, value);
        } else {
            return false;
        }
    }

    /**
     * Retrieve total number of times that the system ram was accesses.
     *
     * @return total number of times that the system ram was accesses.
     */
    public int get_ram_accesses() {
        return this.ram.get_ram_accesses();
    }
}
