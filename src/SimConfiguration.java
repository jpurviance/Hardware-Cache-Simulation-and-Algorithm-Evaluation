/**
 * Created by John Purviance on 12/30/15.
 * Configuration for a simulation run, includes number of runs, type of simulation and cache.
 */
public class SimConfiguration {
    private RunnableSim type;
    private int runs;
    private String cache_name;
    private CacheConfiguration test_cache;

    /**
     * New simulation configuration.
     *
     * @param type       type of simulation
     * @param num_runs   how man times to run simulation
     * @param cache_name name that was given to cache.
     */
    public SimConfiguration(RunnableSim type, int num_runs, String cache_name) {
        this.type = type;
        this.runs = num_runs;
        this.cache_name = cache_name;
        this.test_cache = null;
    }

    /**
     * Set the cache that the simulations should be run on.
     *
     * @param test cache to be tested.
     */
    public void set_test_cache(CacheConfiguration test) {
        this.test_cache = test;
    }

    /**
     * How many times to run a simulation
     *
     * @return number so simulation runs.
     */
    public int get_number_of_runs() {
        return this.runs;
    }

    /**
     * Get the name of the cache being run on.
     *
     * @return name of cache.
     */
    public String get_run_on_cache() {
        return this.cache_name;
    }

    /**
     * Get the type of simulation.
     *
     * @return type of simulation to run.
     */
    public RunnableSim get_sim_type() {
        return this.type;
    }

    /**
     * Get type of cache
     *
     * @return type of cache to be tested.
     */
    public CacheType get_cache_type() {
        return this.test_cache.get_type();
    }
}
