import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by John Purvaince on 12/25/15.
 * Test system Memory with different cache configurations.
 */
public class TestMemory {
    private Memory storage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Direct Map Cache testing.

    /**
     * Setup method for direct map caches. populate each index with its own value.
     */
    private void direct_map_setup() {
        this.storage = new Memory(CacheType.DIRECT_MAP); // the argument is ignored right now.
        for (int i = 0; i < this.storage.get_size(); i++) {
            this.storage.no_analytics_set_at_address_in_RAM(i, i);
        }
    }

    /**
     * Test the cache is cold at first and does not reflect the values in ram.
     */
    @Test
    public void test_direct_map_all_misses() {
        this.direct_map_setup();

        // test the the cache is full of all 0's, its "cold" state
        for (int i = 0; i < Math.pow(2, 3); i++) {
            assertEquals(this.storage.no_analytics_get_from_cache(i), 0);
        }

        // test pull the values from ram into the cache.
        assertEquals(this.storage.get_at_address(22), 22);
        assertEquals(this.storage.no_analytics_get_from_cache(22), 22);

        assertEquals(this.storage.get_at_address(26), 26);
        assertEquals(this.storage.no_analytics_get_from_cache(26), 26);

        assertEquals(this.storage.get_at_address(18), 18);
        assertEquals(this.storage.no_analytics_get_from_cache(18), 18);
        assertEquals(this.storage.no_analytics_get_from_RAM(22), 0);
    }

    /**
     * test address that conflict.
     */
    @Test
    public void test_direct_map_cache_conflict() {
        this.direct_map_setup();
        assertEquals(this.storage.get_at_address(26), 26);
        assertEquals(this.storage.no_analytics_get_from_cache(26), 26);

        assertEquals(this.storage.get_at_address(18), 18);
        assertEquals(this.storage.no_analytics_get_from_cache(18), 18);

        assertEquals(this.storage.get_at_address(26), 26);
        assertEquals(this.storage.no_analytics_get_from_cache(26), 26);
    }

    /**
     * A situation where the cache does not match the system ram because of the write back scheme.
     */
    @Test
    public void test_direct_map_write_back_with_cache_ram_mismatch() {
        this.direct_map_setup();
        assertTrue(this.storage.set_at_address(22, 99));
        assertEquals(this.storage.no_analytics_get_from_RAM(22), 22);
        assertEquals(this.storage.get_at_address(22), 99);

        assertTrue(this.storage.set_at_address(30, 105));
        assertEquals(this.storage.no_analytics_get_from_RAM(22), 99);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Fully Associative Cache Testing

    /**
     * Setup method for associative caches.
     */
    private void fully_associative_setup() {
        this.storage = new Memory(CacheType.FULLY_ASSOCIATIVE); // the argument is ignored right now.
        for (int i = 0; i < this.storage.get_size(); i++) {
            this.storage.no_analytics_set_at_address_in_RAM(i, i);
        }
    }

    /**
     * Verify that all of the address have the correct values.
     */
    @Test
    public void test_fully_associative_access_all() {
        this.fully_associative_setup();
        for (int i = 0; i < this.storage.get_size(); i++) {
            assertEquals(this.storage.get_at_address(i), i);
        }
    }

    /**
     * Test a value that is not in the cache.
     */
    @Test
    public void test_fully_associative_not_in_cache() {
        this.fully_associative_setup();
        for (int i = 0; i < 8; i++) {
            this.storage.set_at_address(i, 100);
        }
        assertEquals(this.storage.get_at_address(8), 8);
    }

    @Test
    public void test_fully_associative_flush_caches_to_ram() {
        this.fully_associative_setup();
        for (int i = 0; i < 8; i++) {
            this.storage.set_at_address(i * 2, i * 2);
        }
        for (int i = 0; i < this.storage.get_size(); i++) {
            assertEquals(this.storage.no_analytics_get_from_RAM(i), i); // get from ram
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(this.storage.get_at_address(i * 2), i * 2);
        }

        this.storage.flush_caches_to_ram();

        for (int i = 0; i < 8; i++) {
            assertEquals(this.storage.no_analytics_get_from_RAM(i * 2), i * 2);
        }
    }


}