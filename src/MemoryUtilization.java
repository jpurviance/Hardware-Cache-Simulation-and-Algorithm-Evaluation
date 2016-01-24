/**
 * Created by John Purviance on 1/13/16.
 * Storage object for System memory, cache and rame usage.
 */
public class MemoryUtilization {
    private int total_ram_access;
    private int total_ram_reads;
    private int total_ram_writes;

    private int total_cache_accesses;
    private int total_cache_writes;
    private int total_cache_reads;

    private int total_cache_misses;
    private int total_cache_hits;
    private int total_cache_read_hits;
    private int total_cache_read_misses;
    private int total_cache_write_hits;
    private int total_cache_write_misses;
    private int total_cache_cold_read_misses;
    private int total_cache_cold_write_hits;
    private int total_cache_write_backs;

    private int total_accesses;
    private int total_reads;
    private int total_writes;

    /**
     * Assumes that the user will at least want to know the total utilization. These values are required.
     *
     * @param total_accesses total times that the system memory was accessed.
     * @param total_reads    total times that the system memory was read from.
     * @param total_writes   total times that the system memory was written to.
     */
    public MemoryUtilization(int total_accesses, int total_reads, int total_writes) {
        this.total_accesses = total_accesses;
        this.total_reads = total_reads;
        this.total_writes = total_writes;
    }

    /**
     * Set the number of times that the cache was accessed.
     *
     * @param cache_access           number of tiems the cache was accessed.
     * @param cache_reads            number of times that the cache was read from.
     * @param cache_writes           number of times that the cache was read from.
     * @param cache_misses           number of times that a operation resulted in a cache miss.
     * @param cache_hits             number of times that a operation resulted in a cache hit.
     * @param cache_read_hits        number of times that a read value was in the cache.
     * @param cache_read_misses      number of times that a read value was not in the cache.
     * @param cache_write_hits       number of times that a value could be directly written into the cache.
     * @param cache_write_misses     number of times that a write was unable to happen becasue the cache was full.
     * @param cache_cold_read_misses number of times that the value was not in the cache because the cache was cold.
     * @param cache_cold_write_hits  number of time that a value could be written into
     *                               the cache because not value was being stored.
     * @param cache_write_backs      number of times a write back operation was preformed.
     */
    public void set_cache_utilization(int cache_access, int cache_reads, int cache_writes, int cache_misses,
                                      int cache_hits, int cache_read_hits, int cache_read_misses, int cache_write_hits,
                                      int cache_write_misses, int cache_cold_read_misses, int cache_cold_write_hits,
                                      int cache_write_backs) {
        this.total_cache_accesses = cache_access;
        this.total_cache_writes = cache_reads;
        this.total_cache_reads = cache_writes;
        this.total_cache_misses = cache_misses;
        this.total_cache_hits = cache_hits;
        this.total_cache_read_hits = cache_read_hits;
        this.total_cache_read_misses = cache_read_misses;
        this.total_cache_write_hits = cache_write_hits;
        this.total_cache_write_misses = cache_write_misses;
        this.total_cache_cold_read_misses = cache_cold_read_misses;
        this.total_cache_cold_write_hits = cache_cold_write_hits;
        this.total_cache_write_backs = cache_write_backs;

    }

    /**
     * Store the ram access information
     *
     * @param ram_accesses total number of times that ram was accessed
     * @param ram_reads    total number of times that the ram was read from.
     * @param ram_writes   total number of time that the ram was written to.
     */
    public void set_set_ram_utilization(int ram_accesses, int ram_reads, int ram_writes) {
        this.total_ram_access = ram_accesses;
        this.total_ram_reads = ram_reads;
        this.total_ram_writes = ram_writes;
    }


    public int get_total_ram_access() {
        return total_ram_access;
    }

    public int get_total_ram_reads() {
        return total_ram_reads;
    }

    public int get_total_ram_writes() {
        return total_ram_writes;
    }

    public int get_total_cache_writes() {
        return total_cache_writes;
    }

    public int get_total_cache_accesses() {
        return total_cache_accesses;
    }

    public int get_total_cache_reads() {
        return total_cache_reads;
    }

    public int get_total_cache_misses() {
        return total_cache_misses;
    }

    public int get_total_cache_hits() {
        return total_cache_hits;
    }

    public int get_total_cache_read_hits() {
        return total_cache_read_hits;
    }

    public int get_total_cache_write_hits() {
        return total_cache_write_hits;
    }

    public int get_total_cache_read_misses() {
        return total_cache_read_misses;
    }

    public int get_total_cache_write_misses() {
        return total_cache_write_misses;
    }

    public int get_total_cache_cold_read_misses() {
        return total_cache_cold_read_misses;
    }

    public int get_total_cache_cold_write_hits() {
        return total_cache_cold_write_hits;
    }

    public int get_total_cache_write_backs() {
        return total_cache_write_backs;
    }

    public int get_total_accesses() {
        return total_accesses;
    }

    public int get_total_reads() {
        return total_reads;
    }

    public int get_total_writes() {
        return total_writes;
    }
}
