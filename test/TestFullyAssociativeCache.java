import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by John Purviance on 1/6/16.
 * Test FullyAssociative functionality and with cache conflicts
 * TODO cache needs more comprehensive testing.
 */
public class TestFullyAssociativeCache {
    private FullyAssociativeCache test_cache;

    /**
     * make a new cache before each run.
     */
    @Before
    public void setUp() {
        this.test_cache = new FullyAssociativeCache(8);
    }

    /**
     * verify that only certain address are valid.
     */
    @Test
    public void test_validate_address() {
        assertTrue(this.test_cache.validate_address(5));
        assertTrue(this.test_cache.validate_address(0));
        assertTrue(this.test_cache.validate_address(31));
        assertFalse(this.test_cache.validate_address(32));
        assertFalse(this.test_cache.validate_address(-1));
    }

    /**
     * Test on a empty cache that none of these address are cached.
     */
    @Test
    public void test_not_in_cache() {
        assertTrue(!this.test_cache.is_in_cache(22));
        assertTrue(!this.test_cache.is_in_cache(26));
        assertTrue(!this.test_cache.is_in_cache(16));
        assertTrue(!this.test_cache.is_in_cache(3));
        assertTrue(!this.test_cache.is_in_cache(18));
    }

    /**
     * test that items are being properly stored in the cache.
     */
    @Test
    public void test_in_cache() {
        assertTrue(!this.test_cache.is_in_cache(22));
        this.test_cache.set_data(22, 98);
        assertTrue(this.test_cache.is_in_cache(22));
        assertEquals(this.test_cache.get_data(22), 98);
        this.test_cache.set_data(7, 100);
        assertTrue(this.test_cache.is_in_cache(7));
        assertEquals(this.test_cache.get_data(7), 100);

        assertTrue(!this.test_cache.is_in_cache(16));
        this.test_cache.set_data(16, -5);
        assertEquals(this.test_cache.get_data(16), -5);
        assertTrue(this.test_cache.is_in_cache(16));
    }

    /**
     * Test cache conflicts and need to do a write back.
     */
    @Test
    public void test_get_write_back_address() {
        this.test_cache.set_data(0, 0);
        this.test_cache.set_data(1, 1);
        this.test_cache.set_data(2, 2);
        this.test_cache.set_data(3, 3);
        try {
            assertEquals(this.test_cache.get_write_back_address(8), 0); // there is still space in cache.
        } catch (CacheColdMissException e) {
            assertThat(e, instanceOf(CacheColdMissException.class));
        }
        this.test_cache.set_data(4, 4);
        this.test_cache.set_data(5, 5);
        this.test_cache.set_data(6, 6);
        this.test_cache.set_data(7, 7);
        try {
            assertEquals(this.test_cache.get_write_back_address(8), 0); // there is no more space in cache.
        } catch (CacheColdMissException e) {
            fail();
        }
        this.test_cache.set_data(8, 8);
        try {
            // compute write back address, its one because it was the last item used in the cache (LRU)
            assertEquals(this.test_cache.get_write_back_address(9), 1);
        } catch (CacheColdMissException e) {
            fail();
        }

        // fill the entire cache with all possible address.
        for (int i = 0; i < this.test_cache.size(); i++) {
            this.test_cache.set_data(i, i);
        }

        try {
            // the last address in the cache is 7 address before the last, it should be removed.
            assertEquals(this.test_cache.get_write_back_address(0), this.test_cache.size() - 8);
        } catch (CacheColdMissException e) {
            fail();
        }
    }


    /**
     * Fill the entire cache and test that all values are in cache.
     */
    @Test
    public void test_fill_cache() {
        for (int i = 0; i < this.test_cache.size(); i++) {
            this.test_cache.set_data(i, i);
        }
        for (int i = 0; i < this.test_cache.size(); i++) {
            assertTrue(this.test_cache.get_data(i) == i);
        }

        // assert values alues are in cache and the have them removed by adding new values.
        for (int i = this.test_cache.size(); i < 32; i++) {
            if (i < 32 - this.test_cache.size()) {
                assertTrue(this.test_cache.is_in_cache(i - this.test_cache.size()));
                this.test_cache.set_data(i, i);
                assertFalse(this.test_cache.is_in_cache(i - this.test_cache.size()));

            }
        }
    }

    /**
     * rewrite all the values in the cache.
     */
    @Test
    public void test_refresh_of_cache() {
        for (int i = 0; i < this.test_cache.size(); i++) {
            this.test_cache.set_data(i, -1);
        }
        for (int i = 0; i < this.test_cache.size(); i++) {
            assertTrue(this.test_cache.get_data(i) == -1);
        }

        for (int i = 0; i < this.test_cache.size(); i++) {
            this.test_cache.set_data(i, i);
        }
        for (int i = 0; i < this.test_cache.size(); i++) {
            assertTrue(this.test_cache.get_data(i) == i);

        }
    }
}
