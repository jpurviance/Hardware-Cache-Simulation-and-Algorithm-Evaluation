/**
 * Created by John Purviance on 1/15/16.
 * Cache that supports storing memory address in linked list. Values are evicted from the cache with
 * a Least Recently Used eviction policy. The node next to the head is the most recently used, the node
 * near the tail is the least recently used.
 * This class does not track access, reads, writes... because it is never directly instantiated as a cache.
 */
public class AssociativeCache implements Cache {

    private CacheWordNode head; // does not store data
    private CacheWordNode tail; // does not store data
    private int cache_length;
    private int size;

    /**
     * Construct a new Associative cache for integers.
     *
     * @param size maximum number of items the cache can hold.
     */
    public AssociativeCache(int size) {
        this.head = new CacheWordNode();
        this.tail = new CacheWordNode();
        this.head.set_next_word(this.tail);
        this.tail.set_previous_word(this.head);
        this.cache_length = 0;
        this.size = size;
    }

    /*
    This class is never directly instantiated, children will overwrite this method
     */
    @Override
    public int get_total_cache_accesses() {
        return 0;
    }

    /*
    This class is never directly instantiated, children will overwrite this method
     */
    @Override
    public int get_total_cache_reads() {
        return 0;
    }

    /*
    This class is never directly instantiated, children will overwrite this method
     */
    @Override
    public int get_total_cache_writes() {
        return 0;
    }

    /**
     * Determine if the cache is storing data for the give address
     *
     * @param mem_address address to check
     * @return if the address if in the cache.
     */
    @Override
    public boolean is_in_cache(int mem_address) {
        if (this.cache_length == 0) { // no data in cache
            return false;
        }
        if (this.validate_address(mem_address)) {
            CacheWordNode at = this.head.get_next_word();
            while (at != null) { // check each node in the cache
                if (at.is_valid() && at.get_mem_address() == mem_address) {
                    return true;
                }
                at = at.get_next_word();
            }
            return false;
        } else {
            return false;
        }
    }


    /*
    helper method for get_data(int mem_address). returns false if the cache word is found.
     */
    private boolean cache_word_not_found(CacheWordNode node, int mem_address) {
        if (node.is_valid() && node.get_mem_address() == mem_address) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * USERS ARE EXPECTED TO VERIFY THAT THE DATA IS IN THE CACHE BEFORE RETRIEVING IT.
     * METHOD WILL RETURN WRONG VALUE IF THE MEMORY ADDRESS IS NOT BEING CACHED.
     * Get the cached data for a particular memory address.
     *
     * @param mem_address The cached memory address.
     * @return The data being cached for the memory address.
     */
    @Override
    public int get_data(int mem_address) { // TODO have all usages of this method check for data in cache.
        // TODO test further
        CacheWordNode current = this.head;

        do {
            current = current.get_next_word();

        } while (current.get_next_word() != this.tail && this.cache_word_not_found(current, mem_address));

        // cut out
        current.get_previous_word().set_next_word(current.get_next_word());
        current.get_next_word().set_previous_word(current.get_previous_word());

        // put at front
        current.set_next_word(this.head.get_next_word());
        this.head.get_next_word().set_previous_word(current);
        current.set_previous_word(this.head);
        this.head.set_next_word(current);
        return current.get_data();

    }

    /**
     * Size of cache
     *
     * @return Return size of cache.
     */
    public int size() {
        return this.size;
    }

    /**
     * When there is a cache conflict old data needs to be writen back to the cache.
     * Use this method to determine what memory address should be written back.
     *
     * @param mem_address The new memory address to be stored in the cache.
     * @return The memory address that data should be written back to.
     * @throws CacheColdMissException There is still space in the cache.
     */
    @Override
    public int get_write_back_address(int mem_address) throws CacheColdMissException {
        if (this.cache_length < this.size) {
            throw new CacheColdMissException(); // there is space in the cache
        } else {
            return this.tail.get_previous_word().get_mem_address(); // always evict the last one. (LRU)
        }
    }

    /**
     * Insert data into the cache.
     *
     * @param mem_address memory address to be cached.
     * @param data        data to be cached.
     */
    @Override
    public void set_data(int mem_address, int data) {
        CacheWordNode current = this.head.get_next_word();

        // Look to see if the memory address is already being cached.
        while (current != this.tail) {
            if (current.get_mem_address() == mem_address && current.is_valid()) {

                // Move word to front of list since it is now the most recely used word
                // TODO this could be a method. (start TODO)
                current.get_previous_word().set_next_word(current.get_next_word());
                current.get_next_word().set_previous_word(current.get_previous_word());
                current.set_next_word(this.head.get_next_word());
                this.head.get_next_word().set_previous_word(current);
                current.set_previous_word(this.head);
                this.head.set_next_word(current);
                // TODO (end TODO)

                current.set_data(data); // Since the word was found update the data.
                return;

            }
            current = current.get_next_word();
        }
        if (this.cache_length < this.size) { // The cache may still have free space.

            // Place a new word at the front of the list (this is now the most recently used).
            this.cache_length++;
            CacheWordNode new_word = new CacheWordNode();
            new_word.set_data(data);
            new_word.set_validity(true);
            new_word.set_mem_address(mem_address);

            CacheWordNode temp = this.head.get_next_word();
            this.head.set_next_word(new_word);
            new_word.set_next_word(temp);
            temp.set_previous_word(new_word);
            new_word.set_previous_word(this.head);

        } else {
            // Move the last peice of data up from the end of the list (evict the least recently used word)
            CacheWordNode move_up = this.tail.get_previous_word();
            this.tail.set_previous_word(move_up.get_previous_word());
            move_up.get_previous_word().set_next_word(this.tail);

            this.head.get_next_word().set_previous_word(move_up);
            move_up.set_next_word(this.head.get_next_word());
            this.head.set_next_word(move_up);
            move_up.set_previous_word(this.head);

            // Update this word to be the new cache word.
            move_up.set_mem_address(mem_address);
            move_up.set_data(data);

        }
    }

    /**
     * Use the method to get data from cache when analytics do not matter.
     *
     * @param mem_address memory address to retirve data from.
     * @return data at that the given memory address.
     */
    @Override
    public int no_analytics_get_data(int mem_address) {

        return this.get_data(mem_address); // since this class does not track analytics for cache usage just use standard get_data()
    }

    /**
     * Validate that the memory address ia a valid address for caching.
     *
     * @param mem_address Address to verify
     * @return if the address is valid.
     */
    @Override
    public boolean validate_address(int mem_address) {
        if (mem_address > 31 || mem_address < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Inner class for cache words. Stores if the node is valid, the memory address it is caching and the data for that address.
     */
    protected class CacheWordNode {

        private CacheWordNode next_word;
        private CacheWordNode prev_word;
        private int data;
        private boolean valid;
        private int address;

        public CacheWordNode() {
            this.next_word = null;
            this.prev_word = null; // TODO this was commented out. why?
            this.data = 0;
            this.valid = false;
            this.address = 0;
        }

        /**
         * Retrieve data from node.
         *
         * @return data stroed in node.
         */
        public int get_data() {
            return this.data;
        }

        /**
         * Set data for node.
         *
         * @param data the data to be stored.
         */
        public void set_data(int data) {
            this.data = data;
        }

        /**
         * Determines if the data in the node is valid.
         *
         * @return if the data is valid.
         */
        public boolean is_valid() {
            return this.valid;
        }

        /**
         * Get the next word in the cache, the next word has always been accessed later than this one.
         *
         * @return the next word in the cache.
         */
        public CacheWordNode get_next_word() {
            return this.next_word;
        }

        /**
         * Set the next word in the cache, that is to say the node after this one is older.
         *
         * @param next_word The node to set.
         */
        public void set_next_word(CacheWordNode next_word) {
            this.next_word = next_word;
        }

        /**
         * Get the node that has been accessed more recently than this node.
         *
         * @return the newer node in the cache.
         */
        public CacheWordNode get_previous_word() {
            return this.prev_word;
        }

        /**
         * Set the previous word in the cache, that is to say the node before this one has been accesses more recently.
         *
         * @param previous_word Node to be set before this one.
         */
        public void set_previous_word(CacheWordNode previous_word) {
            this.prev_word = previous_word;
        }

        /**
         * Get the memory address that this node is caching.
         *
         * @return Memory address that the node is caching.
         */
        public int get_mem_address() {
            return this.address;
        }

        /**
         * Set the memory address that this node should store.
         *
         * @param mem_address address to store.
         */
        public void set_mem_address(int mem_address) {
            this.address = mem_address;
        }

        /**
         * Set the boolean value that represents if this cache word value is valid.
         *
         * @param set if the word is valid.
         */
        public void set_validity(boolean set) {
            this.valid = set;
        }
    }
}
