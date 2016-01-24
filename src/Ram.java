/**
 * Created by John Purviance on 12/22/15.
 * System ram for Memory implemented as linear storage.
 */
public class Ram {
    private int mem_array[];
    private int size;
    private int ram_access;
    private int ram_read;
    private int ram_write;

    /**
     * Generate new system ram.
     *
     * @param size size of system ram.
     */
    public Ram(int size) {
        this.mem_array = new int[size];
        this.size = size;
        this.ram_access = 0;
        this.ram_read = 0;
        this.ram_write = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // No Analytics, this section does not count towards accesses, reads and writes.

    /**
     * Determines if the address is a valid memory address.
     *
     * @param mem_address The memory address to validate.
     * @return if the address is valid.
     */
    public boolean valid_address(int mem_address) { // should be part of AddressStorage intrface.
        if (mem_address >= 0 && mem_address < this.size) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Size of system ram.
     *
     * @return size of sytem ram.
     */
    public int get_size() {
        return this.size;
    }


    /**
     * number of ram accesses
     *
     * @return times that ram was accessed.
     */
    public int get_ram_accesses() {
        return this.ram_access;
    }

    /**
     * number of ram reads.
     *
     * @return times ram was read.
     */
    public int get_ram_reads() {
        return this.ram_read;
    }

    /**
     * number of times ram was written to.
     *
     * @return number of ram writes.
     */
    public int get_ram_writes() {
        return this.ram_write;
    }


    /**
     * get from memory address without tracking accesses.
     *
     * @param address retrieve data from.
     * @return value at adress.
     */
    public int no_analytics_get_at_address(int address) {
        return this.mem_array[address];
    }

    /**
     * Set data at address without tracking ram accesses or writes.
     *
     * @param address address to set at
     * @param value   value to set
     * @return if the value was set.
     */
    public boolean no_analytics_set_at_address(int address, int value) {
        if (address >= 0 && address < this.size) {
            this.mem_array[address] = value;
            return true;
        } else {
            return false;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // These affect analytics.

    /**
     * Set value at memory address. Return value indicates if the value was successfully set.
     *
     * @param address the memory address to set the value at.
     * @param value   the data value to be set at that memeory address.
     * @return if the value was set.
     */
    public boolean set_at_address(int address, int value) {
        if (address >= 0 && address < this.size) {
            this.mem_array[address] = value;
            this.ram_access++;
            this.ram_write++;
            return true;
        } else {
            return false;
        }
    }


    /**
     * USERS ARE EXPECTED TO VALIDATE MEMORY ADDRESSES.
     * Get the value at a specific address in RAM.
     *
     * @param address where to get data from.
     * @return value at address.
     */
    public int get_at_address(int address) {
        this.ram_access++;
        this.ram_read++;
        return this.mem_array[address];
    }
}
