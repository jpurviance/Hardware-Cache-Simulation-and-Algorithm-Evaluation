/**
 * Created by John on 12/28/15.
 */

import org.junit.*;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
public class TestRam {
    private Ram DRam;

    @Before
    public void setUp(){
        this.DRam = new Ram(32); // address 0 to 31.
    }
}
