# Hardware Cache Simulation and Algorithm Evaluation (HCSAE)

Hardware caches accelerate execution time by reducing the total amount of time that processors wait while data is being transferred from system memory to the registers. Fewer clock cycles are required to access a value stored in the system cache compared the much larger system memory. Hardware Cache Simulation and Algorithm Evaluation (HCSAE) simulates several cache structures with a write-back policy. Four sorting algorithms and two search algorithms are provided as simulation jobs for testing cache efficiency and cache safe code. 

## Cache Information
### Direct Map:
Each memory address can be stored in only one location of the cache. Addresses are hashed to a cache index. Old values are evicted ass soon as a new value needs that cache index. 
Advantages: Fast read and write times.
Disadvantages: Values are evicted as soon as another address that hashes to the same index is to be cached. 

### Fully Associative:
Every address in stored in the same cache set. Multiple addresses that would hash to the same index in a direct map cache can be stored at the same time in the cache. Advantages: Cache values are not immediately evicted from the cache when new values need to be stored. Disadvantages: Searching through cache sets is more costly then direct hash implementations. 

### Set Associative:
Address are hashed into sets where multiple memory address values are stored. Read operations are less costly then Full Associative caches due to the smaller pool of cache words. 
Advantages: Values are evicted from the cache less than direct map caches. Read operations take less time then Fully associative caches. Disadvantages:  Direct map caches require less time to retrieve cached values. Fully associative caches evict values less than set associative caches. 
