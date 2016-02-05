import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by John on 2/4/16.
 */
public class TestBinarySeachSim {
    private Memory test_memory;
    private BinarySearchSim test_sim;

    @Before
    public void setUp(){
        this.test_sim = new BinarySearchSim();
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
    public void test_search(){
        for (int i = 0; i < this.test_memory.get_size(); i++){
            this.test_memory.no_analytics_set_at_address_in_RAM(i, i);
        }

        assertEquals(10, this.test_sim.run(this.test_memory, 10));
        assertNotEquals(9, this.test_sim.run(this.test_memory, 10));
        assertEquals(-1, this.test_sim.run(this.test_memory, 100));
    }

}
