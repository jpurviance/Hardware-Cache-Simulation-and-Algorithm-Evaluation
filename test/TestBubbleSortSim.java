import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by John Purviance on 12/27/15.
 * Test class populates system memory with random integers and then runs bubble sort on memory.
 * Validates that integers are preserved.
 */
public class TestBubbleSortSim {
    private Memory test_memory;
    private BubbleSortSim test_sim;

    /**
     * Prep ran before every test. Make a new system memory with a direct map cache.
     * Makes new simulation instance.
     */
    @Before
    public void setUp() {
        //this.test_memory = new Memory(CacheType.DIRECT_MAP); // TODO refactor class to test all three cache types.
        this.test_sim = new BubbleSortSim();
    }


    public void direct_map_setup(){
        this.test_memory = new Memory(CacheType.DIRECT_MAP);
    }

    public void fully_associative_setup(){
        this.test_memory = new Memory(CacheType.FULLY_ASSOCIATIVE);
    }

    public void set_associative_setup(){
        this.test_memory = new Memory(CacheType.SET_ASSOCIATIVE);
    }

    @Test
    public void test_prime(){
        this.direct_map_setup();
        this.prime();
        this.fully_associative_setup();
        this.prime();
        this.set_associative_setup();
        this.prime();
    }

    @Test
    public void test_sort_ordering(){
        this.direct_map_setup();
        this.sort_ordering();
        this.fully_associative_setup();
        this.sort_ordering();
        this.set_associative_setup();
        this.sort_ordering();
    }

    @Test
    public void test_sort_quantity_preservation(){
        this.direct_map_setup();
        this.sort_quantity_preservation();
        this.fully_associative_setup();
        this.sort_quantity_preservation();
        this.set_associative_setup();
        this.sort_quantity_preservation();
    }

    /**
     * Verify that the system ram has random integers populated in it.
     */
    private void prime() {
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            this.test_memory.no_analytics_set_at_address_in_RAM(i, -1); // populate ram with -1
        }
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            assertEquals(this.test_memory.no_analytics_get_from_RAM(i), -1); // verify that all of ram is -1
        }

        test_sim.prime(this.test_memory);
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            assertNotEquals(this.test_memory.no_analytics_get_from_RAM(i), -1); // verify that ram has a new int
            assertTrue(this.test_memory.no_analytics_get_from_RAM(i) > 0); // lower bounds check for new int
            assertTrue(this.test_memory.no_analytics_get_from_RAM(i) < 101); // upper bounds check for new int
        }
    }

    /**
     * Run bubble sort and verify that values are sorted.
     */
    private void sort_ordering() {
        test_sim.prime(this.test_memory);
        test_sim.run(this.test_memory);

        for (int i = 0; i < this.test_memory.get_size(); i++) {
            int compare = this.test_memory.get_at_address(i);
            if (i < test_memory.get_size() - 1) {
                // check the next value is larger then current
                assertTrue(compare <= this.test_memory.get_at_address(i + 1));
            } else {
                // check that last value is larger then second to last.
                assertTrue(compare >= this.test_memory.get_at_address(i - 1));
            }
        }
    }

    /**
     * verify that the same number of ints is present before and after.
     * // TODO this test is by no means comprehensive. To be comprehensive each int would either need to be distinct or have a unique identifier.
     */
    private void sort_quantity_preservation() {
        this.test_sim.prime(this.test_memory);
        int[] pre_data = new int[101];
        int[] post_data = new int[101];

        for (int i = 0; i < this.test_memory.get_size(); i++) {
            pre_data[i] = 0;
            post_data[i] = 0;
        }

        // for each index, increment the amount of times that each int is present.
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            pre_data[this.test_memory.no_analytics_get_from_RAM(i)] = pre_data[this.test_memory.no_analytics_get_from_RAM(i)] + 1;
        }

        this.test_sim.run(this.test_memory);

        // for each index, increment the amount of times that each int is present.
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            post_data[this.test_memory.no_analytics_get_from_RAM(i)] = post_data[this.test_memory.no_analytics_get_from_RAM(i)] + 1;
        }

        // Compre the post and pre data to verify that they are the same.
        for (int i = 0; i < this.test_memory.get_size(); i++) {
            assertEquals(pre_data[i], post_data[i]);
        }
    }

}
