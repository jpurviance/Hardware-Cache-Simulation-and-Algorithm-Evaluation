import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by John on 12/28/15.
 */
public class TestQuickSortSim {
    private Memory test_memory;
    private QuickSortSim test_sim;

    @Before
    public void setUp(){
        this.test_sim = new QuickSortSim();
        this.test_memory = new Memory(CacheType.DIRECT_MAP);
    }


    @Test
    public void test_prime(){
        for (int i = 0; i < this.test_memory.get_size(); i++){
            this.test_memory.no_analytics_set_at_address_in_RAM(i, -1); // should be set at mem address.
        }
        for (int i = 0; i < this.test_memory.get_size(); i++){
            assertEquals(this.test_memory.no_analytics_get_from_RAM(i), -1);
        }

        test_sim.prime(this.test_memory);
        for (int i = 0; i < this.test_memory.get_size(); i++){
            assertNotEquals(this.test_memory.no_analytics_get_from_RAM(i), -1);
        }
    }

    @Test
    public void test_sort_ordering(){
        test_sim.prime(this.test_memory);
        test_sim.run(this.test_memory);

        for(int i = 0; i<this.test_memory.get_size(); i++){
            int compare = this.test_memory.get_at_address(i);
            if (i < test_memory.get_size()-1){
                assertTrue(compare <= this.test_memory.get_at_address(i+1));// assumes the lenght is at least 2
            } else {
                assertTrue(compare >= this.test_memory.get_at_address(i-1));
            }
        }
    }

    @Test
    public void test_sort_quantity_preservation(){
        this.test_sim.prime(this.test_memory);
        int[] pre_data = new int[101];
        int[] post_data = new int[101];

        for (int i = 0; i < this.test_memory.get_size(); i++){
            pre_data[i] = 0;
            post_data[i] = 0;
        }

        for (int i = 0; i < this.test_memory.get_size(); i++){
            pre_data[this.test_memory.no_analytics_get_from_RAM(i)] = pre_data[this.test_memory.no_analytics_get_from_RAM(i)] +1;
        }

        this.test_sim.run(this.test_memory);

        for (int i = 0; i < this.test_memory.get_size(); i++){
            post_data[this.test_memory.no_analytics_get_from_RAM(i)] = post_data[this.test_memory.no_analytics_get_from_RAM(i)] +1;
        }

        for (int i = 0; i < this.test_memory.get_size(); i++){
            assertEquals(pre_data[i], post_data[i]);
        }
    }
}
