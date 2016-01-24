/**
 * Created by John Purviance on 12/22/15.
 * Object that represents a valid cache configuration. Used when parsing configuration file.
 * Contains ram size, cache type and name of the cache configuration.
 */
public class CacheConfiguration {
    private int ram_size;
    private CacheType type;
    private String name;

    /**
     * Init object with all three fields
     *
     * @param ram_size The size of the ram that should be cached.
     * @param type     Type of the cache: Direct Map, Fully Associative and Set Associative.
     * @param name     Name given to cache configuration by user.
     */
    public CacheConfiguration(int ram_size, CacheType type, String name) {
        this.ram_size = ram_size;
        this.type = type;
        this.name = name;
    }

    /**
     * Retrieve size of the ram that is being cached.
     *
     * @return the size of the ram.
     */
    public int get_ram_size() {
        return this.ram_size;
    }

    /**
     * Retrieve the type of cache
     *
     * @return Enum: DIRECT_MAP, SET_ASSOCIATIVE, FULLY_ASSOCIATIVE
     */
    public CacheType get_type() {
        return this.type;
    }

    /**
     * Retrieve the name given to the cache configuration by the user.
     *
     * @return Name of cache configuration.
     */
    public String get_name() {
        return this.name;
    }

}
