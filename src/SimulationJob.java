/**
 * Created by John Purviance on 12/22/15.
 * All simulation jobs mut conform to thsi interface.
 */
public interface SimulationJob {
    void run(Memory storage);
    void prime(Memory storage);

}
