import java.util.ArrayList;

/**
 * Created by John Purviance on 12/22/15.
 * Diver class for all simulations. Opens command file, runs simulations and analyses usage.
 */
public class RunSimulation {
    public static void main(String[] args) {
        try {
            // parse and retrieve simulations.
            ConfigParser parser = new ConfigParser(args[0]);
            ArrayList<SimConfiguration> configurations = parser.get_simulations();

            // determine simulation
            for (int i = 0; i < configurations.size(); i++) {
                SimulationJob test_sim;
                switch (configurations.get(i).get_sim_type()) {
                    case BubbleSort:
                        test_sim = new BubbleSortSim();
                        break;
                    case BinarySearch:
                        test_sim = new BinarySearchSim();
                        break;
                    case HeapSort:
                        test_sim = new HeapSortSim();
                        break;
                    case InsertionSort:
                        test_sim = new InsertionSortSim();
                        break;
                    case LinearRead:
                        test_sim = new LinearReadSim();
                        break;
                    case QuickSort:
                        test_sim = new QuickSortSim();
                        break;
                    default:
                        test_sim = new QuickSortSim();

                }
                AnalyticsEngine compute_utilization = new AnalyticsEngine(configurations.get(i).get_sim_type());

                // run simulations.
                for (int j = 0; j < configurations.get(i).get_number_of_runs(); j++) {
                    Memory system_memory = new Memory(configurations.get(i).get_cache_type());
                    test_sim.prime(system_memory);
                    test_sim.run(system_memory);
                    compute_utilization.add_run_utilization(system_memory.get_memory_utilization());
                }

                // Log results to console.
                System.out.println("Memory utilization");
                System.out.println(compute_utilization.compute_memory_min_access());
                System.out.println(compute_utilization.compute_memory_max_access());
                System.out.println(compute_utilization.compute_memory_average_access());
                System.out.println(compute_utilization.compute_memory_min_reads());
                System.out.println(compute_utilization.compute_memory_max_reads());
                System.out.println(compute_utilization.compute_memory_average_reads());
                System.out.println(compute_utilization.compute_memory_min_writes());
                System.out.println(compute_utilization.compute_memory_max_writes());
                System.out.println(compute_utilization.compute_memory_average_writes());


                System.out.println("\nRam utilization");
                System.out.println(compute_utilization.compute_ram_min_access());
                System.out.println(compute_utilization.compute_ram_max_access());
                System.out.println(compute_utilization.compute_ram_average_access());
                System.out.println(compute_utilization.compute_ram_min_reads());
                System.out.println(compute_utilization.compute_ram_max_reads());
                System.out.println(compute_utilization.compute_ram_average_reads());
                System.out.println(compute_utilization.compute_ram_min_writes());
                System.out.println(compute_utilization.compute_ram_max_writes());
                System.out.println(compute_utilization.compute_ram_average_writes());


                System.out.println("\nBasic cache utilization");
                System.out.println(compute_utilization.compute_cache_min_accesses());
                System.out.println(compute_utilization.compute_cache_max_accesses());
                System.out.println(compute_utilization.compute_cache_average_accesses());
                System.out.println(compute_utilization.compute_cache_min_reads());
                System.out.println(compute_utilization.compute_cache_max_reads());
                System.out.println(compute_utilization.compute_cache_average_reads());
                System.out.println(compute_utilization.compute_cache_min_writes());
                System.out.println(compute_utilization.compute_cache_max_writes());
                System.out.println(compute_utilization.compute_cache_average_writes());

                System.out.println("\nAdvanced cache utilization");
                System.out.println(compute_utilization.compute_cache_min_misses());
                System.out.println(compute_utilization.compute_cache_max_misses());
                System.out.println(compute_utilization.compute_cache_average_misses());
                System.out.println(compute_utilization.compute_cache_min_hits());
                System.out.println(compute_utilization.compute_cache_max_hits());
                System.out.println(compute_utilization.compute_cache_average_hits());
                System.out.println(compute_utilization.compute_cache_min_read_hits());
                System.out.println(compute_utilization.compute_cache_max_read_hits());
                System.out.println(compute_utilization.compute_cache_average_read_hits());
                System.out.println(compute_utilization.compute_cache_min_write_hits());
                System.out.println(compute_utilization.compute_cache_max_write_hits());
                System.out.println(compute_utilization.compute_cache_average_write_hits());
                System.out.println(compute_utilization.compute_cache_min_read_misses());
                System.out.println(compute_utilization.compute_cache_max_read_misses());
                System.out.println(compute_utilization.compute_cache_average_read_misses());
                System.out.println(compute_utilization.compute_cache_min_write_misses());
                System.out.println(compute_utilization.compute_cache_max_write_misses());
                System.out.println(compute_utilization.compute_cache_average_write_misses());
                System.out.println(compute_utilization.compute_cache_min_cold_read_misses());
                System.out.println(compute_utilization.compute_cache_max_cold_read_misses());
                System.out.println(compute_utilization.compute_cache_average_cold_read_misses());
                System.out.println(compute_utilization.compute_cache_min_cold_write_hits());
                System.out.println(compute_utilization.compute_cache_max_cold_write_hits());
                System.out.println(compute_utilization.compute_cache_average_cold_write_hits());
                System.out.println(compute_utilization.compute_cache_min_write_backs());
                System.out.println(compute_utilization.compute_cache_max_write_backs());
                System.out.println(compute_utilization.compute_cache_average_write_backs());

            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return;
    }
}
