import java.util.ArrayList;

/**
 * Created by John Purviance on 1/14/16.
 * Consumes Memory Utilization information. Provides comprehensive selection of methods for generating strings
 * containing information about memory and cache utilization for past simulations.
 */
public class AnalyticsEngine {
    private ArrayList<MemoryUtilization> usages;
    private RunnableSim sim_type;

    /**
     * Instantiate AnalyticsEngine for a particular type of simulation.
     *
     * @param simulation_type The simulation that is to be studied.
     */
    public AnalyticsEngine(RunnableSim simulation_type) {
        this.usages = new ArrayList<>();
        this.sim_type = simulation_type;
    }

    /**
     * Add a utilization to consider in when computing usage of Memory and Caches.
     *
     * @param new_utilization The next memory utilization to consider.
     */
    public void add_run_utilization(MemoryUtilization new_utilization) {
        this.usages.add(new_utilization);
    }

    /**
     * Find the minimum ram usage and return its value in a human readable string.
     *
     * @return Line with type of simulation and the minimum amount of ram accesses
     */
    public String compute_ram_min_access() {
        // prime
        int min = this.usages.get(0).get_total_ram_access(); // assumes a size of at least 1;
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_access() < min) {
                min = usages.get(i).get_total_ram_access();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of ram accesses was " + min;
    }

    /**
     * Find the max ram usage and return its value in a human readable string.
     *
     * @return Line with type of simulation and the maximum amount of ram accesses.
     */
    public String compute_ram_max_access() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_access() > max) {
                max = usages.get(i).get_total_ram_access();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of ram accesses was " + max;
    }

    /**
     * Find the average ram usage and return its value in a human readable string.
     *
     * @return Line with the type of simulation and the average amount of ram accesses.
     */
    public String compute_ram_average_access() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_ram_access();
        }
        return "For " + this.sim_type.toString() + " the average number of ram accesses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum ram reads and return its value in a human readable string.
     *
     * @return Line with the type of simulation and the minimum amoutn of ram reads.
     */
    public String compute_ram_min_reads() {
        int min = this.usages.get(0).get_total_ram_reads();
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_reads() < min) {
                min = usages.get(i).get_total_ram_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of ram reads was " + min;
    }

    /**
     * Find the max amount of ram reads and return its value in a human readable string.
     *
     * @return Line with the type of simulation and the max amount of ram reads.
     */
    public String compute_ram_max_reads() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_reads() > max) {
                max = usages.get(i).get_total_ram_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of ram reads was " + max;
    }

    /**
     * Find the average amount of ram reads and return its value in a human readable string.
     *
     * @return Line with the type of simulation and the average amount of ram reads.
     */
    public String compute_ram_average_reads() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + usages.get(i).get_total_ram_reads();
        }
        return "For " + this.sim_type.toString() + " the average number of ram reads was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum amount of ram writes and returns its value in a human readable string.
     *
     * @return Line with the type of simulation and the minimum amount of ram writes.
     */
    public String compute_ram_min_writes() {
        int min = this.usages.get(0).get_total_ram_writes();
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_writes() < min) {
                min = usages.get(i).get_total_ram_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of ram writes was " + min;
    }

    /**
     * Find the maximum amount of ram writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum amount of ram writes.
     */
    public String compute_ram_max_writes() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_ram_writes() > max) {
                max = usages.get(i).get_total_ram_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of ram writes was " + max;
    }

    /**
     * Find the average amount of ram writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average amount of ram writes.
     */
    public String compute_ram_average_writes() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + usages.get(i).get_total_ram_writes();
        }
        return "For " + this.sim_type.toString() + " the average number of ram writes was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum amount of memory accesses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of memory accesses.
     */
    public String compute_memory_min_access() {
        // prime
        int min = this.usages.get(0).get_total_accesses(); // assumes a size of at least 1;
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_accesses() < min) {
                min = usages.get(i).get_total_accesses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of memory accesses was " + min;
    }

    /**
     * Find the maximum number of memory accesses and return its value as a human readable string
     *
     * @return Line with the simulation type and the maximum number of memory accesses.
     */
    public String compute_memory_max_access() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_accesses() > max) {
                max = usages.get(i).get_total_accesses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of memory accesses was " + max;
    }

    /**
     * Find the average number of memory access and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of memory accesses.
     */
    public String compute_memory_average_access() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_accesses();
        }
        return "For " + this.sim_type.toString() + " the average number of memory accesses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of memory reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of memory reads.
     */
    public String compute_memory_min_reads() {
        int min = this.usages.get(0).get_total_reads();
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_reads() < min) {
                min = usages.get(i).get_total_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of memory reads was " + min;
    }

    /**
     * Find the maximum number of memory reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of memory reads.
     */
    public String compute_memory_max_reads() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (max < usages.get(i).get_total_reads()) {
                max = usages.get(i).get_total_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of memory reads was " + max;
    }

    /**
     * Find the average number of memory reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of memory reads.
     */
    public String compute_memory_average_reads() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_reads();
        }
        return "For " + this.sim_type.toString() + " the average number of memory reads was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of memory writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of memory writes.
     */
    public String compute_memory_min_writes() {
        int min = this.usages.get(0).get_total_writes();
        for (int i = 1; i < this.usages.size(); i++) {
            if (min > this.usages.get(i).get_total_writes()) {
                min = this.usages.get(i).get_total_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of memory writes was " + min;
    }

    /**
     * Find the maximum number of memory writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of memory writes.
     */
    public String compute_memory_max_writes() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (max < this.usages.get(i).get_total_writes()) {
                max = this.usages.get(i).get_total_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of memory writes was " + max;
    }

    /**
     * Find the average number of memory writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of memory writes.
     */
    public String compute_memory_average_writes() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_writes();
        }
        return "For " + this.sim_type.toString() + " the average number of memory writes was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache accesses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache accesses.
     */
    public String compute_cache_min_accesses() {
        int min = this.usages.get(0).get_total_cache_accesses();
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_cache_accesses() < min) {
                min = usages.get(i).get_total_cache_accesses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache accesses was " + min;
    }

    /**
     * Find the maximum number of cache accesses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache accesses.
     */
    public String compute_cache_max_accesses() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_cache_accesses() > max) {
                max = usages.get(i).get_total_cache_accesses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache accesses was " + max;
    }

    /**
     * Find the average number of cache accesses and return its value in a human readable string.
     *
     * @return Line with the simulation type and average number of cache accesses.
     */
    public String compute_cache_average_accesses() { //TODO this will integer wrap
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_accesses();
        }
        return "For " + this.sim_type.toString() + " the average number of cache accesses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache writes.
     */
    public String compute_cache_min_writes() {
        int min = this.usages.get(0).get_total_cache_writes();
        for (int i = 1; i < this.usages.size(); i++) {
            if (usages.get(i).get_total_cache_writes() < min) {
                min = usages.get(i).get_total_cache_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache writes was " + min;
    }

    /**
     * Find the maximum number of cache writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache writes.
     */
    public String compute_cache_max_writes() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_writes() > max) {
                max = this.usages.get(i).get_total_cache_writes();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache writes was " + max;
    }

    /**
     * Find the average number of cache writes and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache writes.
     */
    public String compute_cache_average_writes() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_writes();
        }
        return "For " + this.sim_type.toString() + " the average number of cache writes was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache reads.
     */
    public String compute_cache_min_reads() {
        int min = this.usages.get(0).get_total_cache_reads();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_reads() < min) {
                min = this.usages.get(i).get_total_cache_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache reads was " + min;
    }

    /**
     * Find the maximum number of cache reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache reads.
     */
    public String compute_cache_max_reads() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_reads() > max) {
                max = this.usages.get(i).get_total_cache_reads();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache reads was " + max;
    }

    /**
     * Find the average number of cache reads and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache reads.
     */
    public String compute_cache_average_reads() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_reads();
        }
        return "For " + this.sim_type.toString() + " the average number of cache reads was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache misses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache misses.
     */
    public String compute_cache_min_misses() {
        int min = this.usages.get(0).get_total_cache_misses();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_misses() < min) {
                min = this.usages.get(i).get_total_cache_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache misses was " + min;
    }

    /**
     * Find the maximum number of cache misses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache misses.
     */
    public String compute_cache_max_misses() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_misses() > max) {
                max = this.usages.get(i).get_total_cache_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache misses was " + max;
    }

    /**
     * Find the average number of cache misses and return its value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache misses.
     */
    public String compute_cache_average_misses() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_misses();
        }
        return "For " + this.sim_type.toString() + " the average number of cache misses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache hits.
     */
    public String compute_cache_min_hits() {
        int min = this.usages.get(0).get_total_cache_hits();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_hits() < min) {
                min = this.usages.get(i).get_total_cache_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache hits was " + min;
    }

    /**
     * Find the maximum number of cache hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache hits.
     */
    public String compute_cache_max_hits() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_hits() > max) {
                max = this.usages.get(i).get_total_cache_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache hits was " + max;
    }

    /**
     * Find the average number of cache hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache hits.
     */
    public String compute_cache_average_hits() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_hits();
        }
        return "For " + this.sim_type.toString() + " the average number of cache hits was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache read hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache read hits.
     */
    public String compute_cache_min_read_hits() {
        int min = this.usages.get(0).get_total_cache_read_hits();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_read_hits() < min) {
                min = this.usages.get(i).get_total_cache_read_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache read hits was " + min;
    }

    /**
     * Find the maximum number of cache read hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache read hits.
     */
    public String compute_cache_max_read_hits() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_read_hits() > max) {
                max = this.usages.get(i).get_total_cache_read_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache read hits was " + max;
    }

    /**
     * Find the average number of cache read hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache read hits.
     */
    public String compute_cache_average_read_hits() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_read_hits();
        }
        return "For " + this.sim_type.toString() + " the average number of cache read hits was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache write hits and return the value as a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache write hits.
     */
    public String compute_cache_min_write_hits() {
        int min = this.usages.get(0).get_total_cache_write_hits();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_hits() < min) {
                min = this.usages.get(i).get_total_cache_write_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache write hits was " + min;
    }

    /**
     * Find the maximum number of cache write hits and return the value in a human readable string.
     *
     * @return Line with teh simulation type and the maximum number of cache write hits.
     */
    public String compute_cache_max_write_hits() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_hits() > max) {
                max = this.usages.get(i).get_total_cache_write_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache write hits was " + max;
    }

    /**
     * Find the average number of cache write hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache write hits.
     */
    public String compute_cache_average_write_hits() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_write_hits();
        }
        return "For " + this.sim_type.toString() + " the average number of cache write hits was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache read misses and return the value in a human readable string.
     *
     * @return Line with the simulation tyoe and the minimum number of cache read misses.
     */
    public String compute_cache_min_read_misses() {
        int min = this.usages.get(0).get_total_cache_read_misses();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_read_misses() < min) {
                min = this.usages.get(i).get_total_cache_read_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache read misses was " + min;
    }

    /**
     * Find the maximum number of cache read misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache read misses.
     */
    public String compute_cache_max_read_misses() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_read_misses() > max) {
                max = this.usages.get(i).get_total_cache_read_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache read misses was " + max;
    }

    /**
     * Find the average number of cache read misses and return the value in a humand readable string.
     *
     * @return Line with the simulation type and the average number of cache read misses.
     */
    public String compute_cache_average_read_misses() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_read_misses();
        }
        return "For " + this.sim_type.toString() + " the average number of cache read misses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache write misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache write misses.
     */
    public String compute_cache_min_write_misses() {
        int min = this.usages.get(0).get_total_cache_write_misses();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_misses() < min) {
                min = this.usages.get(i).get_total_cache_write_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache write misses was " + min;
    }

    /**
     * Find the maximum number of cache write misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache write misses.
     */
    public String compute_cache_max_write_misses() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_misses() > max) {
                max = this.usages.get(i).get_total_cache_write_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache write misses was " + max;
    }

    /**
     * Find the average number of cache write misses and return the value in a human readable string.
     *
     * @return Line with teh simulation type and the average number of cache write misses.
     */
    public String compute_cache_average_write_misses() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_write_misses();
        }
        return "For " + this.sim_type.toString() + " the average number of cache write misses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache cold read misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache cold read misses.
     */
    public String compute_cache_min_cold_read_misses() {
        int min = this.usages.get(0).get_total_cache_cold_read_misses();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_cold_read_misses() < min) {
                min = this.usages.get(i).get_total_cache_cold_read_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cold cache read misses was " + min;
    }

    /**
     * Find the maximum number of cache cold read misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache cold read misses.
     */
    public String compute_cache_max_cold_read_misses() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_cold_read_misses() > max) {
                max = this.usages.get(i).get_total_cache_cold_read_misses();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cold cache read misses was " + max;
    }

    /**
     * Find the average number of cache cold read misses and return the value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache cold read misses.
     */
    public String compute_cache_average_cold_read_misses() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_cold_read_misses();
        }
        return "For " + this.sim_type.toString() + " the average number of cold cache read misses was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache cold write hits and reaturn the value in a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache cold write hits.
     */
    public String compute_cache_min_cold_write_hits() {
        int min = this.usages.get(0).get_total_cache_cold_write_hits();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_cold_write_hits() < min) {
                min = this.usages.get(i).get_total_cache_cold_write_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cold cache write hits was " + min;
    }

    /**
     * Find the maximum number of cache cold write hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache cold write hits.
     */
    public String compute_cache_max_cold_write_hits() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_cold_write_hits() > max) {
                max = this.usages.get(i).get_total_cache_cold_write_hits();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cold cache write hits was " + max;
    }

    /**
     * Find the average number of cache cold write hits and return the value in a human readable string.
     *
     * @return Line with the simulation type and the average number of cache cold write hits.
     */
    public String compute_cache_average_cold_write_hits() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_cold_write_hits();
        }
        return "For " + this.sim_type.toString() + " the average number of cold cache write hits was " + (double) total / this.usages.size();
    }

    /**
     * Find the minimum number of cache write backs and return the value as a human readable string.
     *
     * @return Line with the simulation type and the minimum number of cache write backs.
     */
    public String compute_cache_min_write_backs() {
        int min = this.usages.get(0).get_total_cache_write_backs();
        for (int i = 1; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_backs() < min) {
                min = this.usages.get(i).get_total_cache_write_backs();
            }
        }
        return "For " + this.sim_type.toString() + " the minimum number of cache write backs was " + min;
    }

    /**
     * Find the maximum number of cache write backs and return the value as a human readable string.
     *
     * @return Line with the simulation type and the maximum number of cache write backs.
     */
    public String compute_cache_max_write_backs() {
        int max = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            if (this.usages.get(i).get_total_cache_write_backs() > max) {
                max = this.usages.get(i).get_total_cache_write_backs();
            }
        }
        return "For " + this.sim_type.toString() + " the maximum number of cache write backs was " + max;
    }

    /**
     * Find the average number of cache write backs and return the value as a human readable string.
     *
     * @return LIne with the simulation type and the average number of cache write backs.
     */
    public String compute_cache_average_write_backs() {
        int total = 0;
        for (int i = 0; i < this.usages.size(); i++) {
            total = total + this.usages.get(i).get_total_cache_write_backs();
        }
        return "For " + this.sim_type.toString() + " the average number of cache write backs was " + (double) total / this.usages.size();
    }
}
