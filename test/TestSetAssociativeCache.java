import org.junit.*;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by John on 1/13/16.
 */
public class TestSetAssociativeCache {
    private SetAssociativeCache test_cache;

    @Before
    public void setUp(){
        this.test_cache = new SetAssociativeCache();
    }

    @Test
    public void test_validate_address(){
        assertTrue(this.test_cache.validate_address(5));
        assertTrue(this.test_cache.validate_address(0));
        assertTrue(this.test_cache.validate_address(32));
        assertFalse(this.test_cache.validate_address(33));
        assertFalse(this.test_cache.validate_address(-1));
    }


    @Test
    public void test_get_index(){
        assertEquals(this.test_cache.compute_index(22), 3);
        assertEquals(this.test_cache.compute_index(26), 1);
        assertEquals(this.test_cache.compute_index(16), 0);
        assertEquals(this.test_cache.compute_index(3), 1);
        assertEquals(this.test_cache.compute_index(18), 1);
    }

    @Test // not in cache, misses
    public void test_not_in_cache(){
        assertTrue(!this.test_cache.is_in_cache(22));
        assertTrue(!this.test_cache.is_in_cache(26));
        assertTrue(!this.test_cache.is_in_cache(16));
        assertTrue(!this.test_cache.is_in_cache(3));
        assertTrue(!this.test_cache.is_in_cache(18));
    }

    @Test
    public void test_in_cache(){
        assertTrue(!this.test_cache.is_in_cache(22));
        this.test_cache.set_data(22, 98);
        assertEquals(this.test_cache.get_data(22), 98);
        assertTrue(this.test_cache.is_in_cache(22));

        assertTrue(!this.test_cache.is_in_cache(16));
        this.test_cache.set_data(16, -5);
        assertEquals(this.test_cache.get_data(16), -5);
        assertTrue(this.test_cache.is_in_cache(16));
    }

    @Test
    public void test_fill_sets_with_same_least_sig_bits() {
        this.test_cache.set_data(16, 16);
        this.test_cache.set_data(24, 24);

        this.test_cache.set_data(18, 18);
        this.test_cache.set_data(26, 26);

        this.test_cache.set_data(20, 20);
        this.test_cache.set_data(28, 28);

        this.test_cache.set_data(22, 22);
        this.test_cache.set_data(30, 30);

        assertTrue(this.test_cache.is_in_cache(16));
        assertTrue(this.test_cache.is_in_cache(24));
        assertTrue(this.test_cache.is_in_cache(18));
        assertTrue(this.test_cache.is_in_cache(26));
        assertTrue(this.test_cache.is_in_cache(20));
        assertTrue(this.test_cache.is_in_cache(28));
        assertTrue(this.test_cache.is_in_cache(22));
        assertTrue(this.test_cache.is_in_cache(30));

        assertEquals(this.test_cache.get_data(16), 16);
        assertEquals(this.test_cache.get_data(24), 24);
        assertEquals(this.test_cache.get_data(18), 18);
        assertEquals(this.test_cache.get_data(26), 26);
        assertEquals(this.test_cache.get_data(20), 20);
        assertEquals(this.test_cache.get_data(28), 28);
        assertEquals(this.test_cache.get_data(22), 22);
        assertEquals(this.test_cache.get_data(30), 30);
    }

    @Test
    public void test_evict_from_set(){
        this.test_cache.set_data(16, 16);
        this.test_cache.set_data(24, 24);
        assertTrue(this.test_cache.is_in_cache(16));
        assertTrue(this.test_cache.is_in_cache(24));

        assertEquals(this.test_cache.get_data(16), 16);
        assertEquals(this.test_cache.get_data(24), 24);

        this.test_cache.set_data(0, 0);
        assertTrue(this.test_cache.is_in_cache(0));
        assertTrue(!this.test_cache.is_in_cache(16));
        assertTrue(this.test_cache.is_in_cache(24));

        this.test_cache.set_data(8, 8);
        assertTrue(this.test_cache.is_in_cache(8));
        assertTrue(!this.test_cache.is_in_cache(24));
        assertTrue(!this.test_cache.is_in_cache(16));


        assertEquals(this.test_cache.get_data(8), 8);
        assertEquals(this.test_cache.get_data(0), 0);

        assertEquals(this.test_cache.get_data(16), 8); // this is because we do not check if the value is in the cache before getting it
        assertEquals(this.test_cache.get_data(24), 0); // " "

    }
}
