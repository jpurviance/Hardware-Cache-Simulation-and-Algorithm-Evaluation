import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by John Purviance on 12/30/15.
 * Walks though user generated configuration file generate simulation and cache configurations. Parses
 * JSON text files and matches simulations to caches.
 */
public class ConfigParser {
    // TODO need to not allow multiple caches with the same name.
    private String file_path;
    private String json_string;
    private JSONObject json;
    private ArrayList<CacheConfiguration> cache_configurations;
    private ArrayList<SimConfiguration> sim_configurations;


    /**
     * Parse the given configuration file.
     *
     * @param file_path File to parse
     * @throws InvalidConfigurationException If the user generates an invalid configuration.
     */
    public ConfigParser(String file_path) throws InvalidConfigurationException {
        this.file_path = file_path;
        this.json_string = "";
        this.cache_configurations = new ArrayList<>();
        this.sim_configurations = new ArrayList<>();
        this.make_json();
        this.validate_config();
        this.retrieve_caches();
        this.retrieve_sims();
        this.sync();
    }

    /*
    walk though all of the cache and simulation configurations and valid them.
    Throw new InvalidCacheException if not all caches are valid
    Throw new InvalidSimulationException if not al simulations are valid.
     */
    private void validate_config() throws InvalidConfigurationException {
        for (int i = 0; i < ((JSONArray) this.json.get("Caches")).length(); i++) {
            if (!this.is_valid_cache((JSONObject) ((JSONArray) this.json.get("Caches")).get(i))) {
                throw new InvalidCacheException(((JSONArray) this.json.get("Caches")).get(i).toString());
            }
        }
        for (int i = 0; i < ((JSONArray) this.json.get("Simulations")).length(); i++) {
            if (!this.is_valid_sim((JSONObject) ((JSONArray) this.json.get("Simulations")).get(i))) {
                throw new InvalidSimulationException(((JSONArray) this.json.get("Simulations")).get(i).toString());
            }
        }
    }

    /*
    Find a cache for the simulation.
     */
    private boolean cache_sync(SimConfiguration sim) {
        for (int i = 0; i < this.cache_configurations.size(); i++) {
            if (this.cache_configurations.get(i).get_name().equals(sim.get_run_on_cache())) {
                sim.set_test_cache(this.cache_configurations.get(i));
                return true;
            }
        }
        return false;
    }

    /*
    Sync all of the simulations with caches
    Throws new CacheNotFoundException if no cache exists for a simulation.
     */
    private void sync() throws InvalidConfigurationException {
        for (int i = 0; i < this.sim_configurations.size(); i++) {
            if (!this.cache_sync(this.sim_configurations.get(i))) {
                throw new CacheNotFoundException((String) ((JSONArray) this.json.get("Simulations")).get(i));
            }
        }
    }


    /*
    Parse out caches and them to the configuration list.
    Throw new InvalidCacheException if a cache does not have a type.
     */
    private void retrieve_caches() throws InvalidCacheException {
        for (int i = 0; i < ((JSONArray) this.json.get("Caches")).length(); i++) {
            JSONObject cache = (JSONObject) (((JSONArray) this.json.get("Caches")).get(i));
            CacheType type;
            switch ((String) cache.get("Cache Type")) {
                case "DirectMap":
                    type = CacheType.DIRECT_MAP;
                    break;
                case "SetAssociative":
                    type = CacheType.SET_ASSOCIATIVE;
                    break;
                case "FullyAssociative":
                    type = CacheType.FULLY_ASSOCIATIVE;
                    break;
                default:
                    throw new InvalidCacheException((String) cache.get("Cache Type") + "is not a valid cache");
            }
            this.cache_configurations.add(new CacheConfiguration((Integer) cache.get("Memory Size"),
                    type, (String) cache.get("Name")));
        }
    }

    /*
    Parse out simulations from text file.
    *** The default simulation type is quick sort if not valid configuation is found. ***
     */
    private RunnableSim get_sim_type(JSONObject sim) {
        String type = (String) sim.get("Type");

        switch (type) {
            case "InsertionSortSim":
                return RunnableSim.InsertionSort;
            case "BubbleSortSim":
                return RunnableSim.BubbleSort;
            case "BinarySearchSim":
                return RunnableSim.BinarySearch;
            case "HeapSortSim":
                return RunnableSim.HeapSort;
            case "LinearReadSim":
                return RunnableSim.LinearRead;
            case "QuickSortSim":
                return RunnableSim.QuickSort;
            default:
                return RunnableSim.QuickSort; // TODO should throw exception.
        }
    }

    /*
    Parse out simulations
     */
    private void retrieve_sims() {
        for (int i = 0; i < ((JSONArray) this.json.get("Simulations")).length(); i++) {
            JSONObject sim = (JSONObject) (((JSONArray) this.json.get("Simulations")).get(i));
            this.sim_configurations.add(new SimConfiguration(this.get_sim_type(sim),
                    (Integer) sim.get("Runs"), (String) sim.get("Cache")));

        }
    }

    /**
     * Get the parsed simulations from the file.
     *
     * @return An ArrayList of the simulations.
     */
    public ArrayList<SimConfiguration> get_simulations() {
        return this.sim_configurations;
    }

    /*
    Determine if the JSON cache object is a valid cache.
     */
    private boolean is_valid_cache(JSONObject cache_object) {
        return (cache_object.has("Name") &&
                (cache_object.get("Name") instanceof String) &&
                cache_object.has("Memory Size") &&
                (cache_object.get("Memory Size") instanceof Integer) &&
                cache_object.has("Cache Type") &&
                (cache_object.get("Cache Type") instanceof String) &&
                (cache_object.length() == 3));


    }

    /*
    Determin if the JSON simulation object is a valid simulation.
     */
    private boolean is_valid_sim(JSONObject sim_object) {
        return (sim_object.has("Type") &&
                (sim_object.get("Type") instanceof String) &&
                sim_object.has("Runs") &&
                (sim_object.get("Runs") instanceof Integer) &&
                sim_object.has("Cache") &&
                (sim_object.get("Cache") instanceof String) &&
                (sim_object.length() == 3));

    }

    /*
    Turn the plan text file into a JSON object for parsing.
     */
    private void make_json() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file_path)));
            String line = br.readLine();
            while (line != null) {
                this.json_string = this.json_string + line + "\n";
                line = br.readLine();
            }
            this.json = new JSONObject(this.json_string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
