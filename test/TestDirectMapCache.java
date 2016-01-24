import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by John Purviance on 12/24/15.
 * Test direct map cache with reads, writes and conflicts.
 */
public class TestDirectMapCache {
    private DirectMapCache testCache;

    /**
     * Run before all tests, make a new cache for testing.
     */
    @Before
    public void setUp() {
        this.testCache = new DirectMapCache();
    }

    /**
     * test cache address verification.
     */
    @Test
    public void test_validate_address() {
        assertTrue(this.testCache.validate_address(5));
        assertTrue(this.testCache.validate_address(0));
        assertTrue(this.testCache.validate_address(31));
        assertFalse(this.testCache.validate_address(33));
        assertFalse(this.testCache.validate_address(-1));
    }

    /**
     * Test get tags for address. Tags are the most significant bits as a two bit int.
     * Tags are used for when multiple address hash to the same cache index.
     */
    @Test
    public void test_get_tag() {
        assertEquals(this.testCache.compute_tag(22), 2); // address: 10110, tag: 10
        assertEquals(this.testCache.compute_tag(26), 3); //address: 11010, tag: 11
        assertEquals(this.testCache.compute_tag(16), 2);
        assertEquals(this.testCache.compute_tag(3), 0);
        assertEquals(this.testCache.compute_tag(18), 2);
    }

    /**
     * Test generating an index for each address. Index is used to find location in cache to store data.
     * Multiple addresses have the same index.
     */
    @Test
    public void test_get_index() {
        assertEquals(this.testCache.compute_index(22), 6); // address: 10110, index: 110
        assertEquals(this.testCache.compute_index(26), 2); // address: 11010, tag: 010 or 10
        assertEquals(this.testCache.compute_index(16), 0);
        assertEquals(this.testCache.compute_index(3), 3);
        assertEquals(this.testCache.compute_index(18), 2);
    }

    /**
     * Test that each of these address are not stored in the cache.
     * The cache is cold so all address should return false.
     */
    @Test
    public void test_not_in_cache() {
        assertTrue(!this.testCache.is_in_cache(22));
        assertTrue(!this.testCache.is_in_cache(26));
        assertTrue(!this.testCache.is_in_cache(16));
        assertTrue(!this.testCache.is_in_cache(3));
        assertTrue(!this.testCache.is_in_cache(18));
    }


    /**
     * Test that valued are being placed into the cache.
     */
    @Test
    public void test_in_cache() {
        assertTrue(!this.testCache.is_in_cache(22)); // address is not cached
        this.testCache.set_data(22, 98); // cache this address
        assertEquals(this.testCache.get_data(22), 98); // see its in cache
        assertTrue(this.testCache.is_in_cache(22)); // test that its in cache.

        assertTrue(!this.testCache.is_in_cache(16));
        this.testCache.set_data(16, -5);
        assertEquals(this.testCache.get_data(16), -5);
        assertTrue(this.testCache.is_in_cache(16));
    }

    /**
     * Verify that cache behaves as expected when there is conflict and a value need to be overwritten.
     */
    @Test
    public void test_cache_conflict() {
        assertTrue(!this.testCache.is_in_cache(26)); // address is not cached.
        this.testCache.set_data(26, 67); // cache address
        assertEquals(this.testCache.get_data(26), 67); // get data from cache
        assertTrue(this.testCache.is_in_cache(26)); // verify in cache

        // there is data where this caches to, verify that this address is not cached since it shares the same index as 26
        assertTrue(!this.testCache.is_in_cache(18));

    }

    /**
     * Verify that the cache thows and exception when asking for a address when no write back is needed.
     */
    @Test
    public void test_cache_write_back_address() {
        try {
            this.testCache.get_write_back_address(0);
        } catch (CacheColdMissException e) {
            assertThat(e, instanceOf(CacheColdMissException.class));
        }
    }

}
