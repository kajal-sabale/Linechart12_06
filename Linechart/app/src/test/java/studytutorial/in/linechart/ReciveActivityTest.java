package studytutorial.in.linechart;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by a634941 on 29-05-2017.
 */
public class ReciveActivityTest {

    ReciveActivity ra= new ReciveActivity();
    @Test
    public void validateBluetoothTest() throws Exception {
        boolean actual= ra.validateBluetooth("kajal");
        boolean expected=true;
        assertEquals(actual,expected);

    }
    @Test
    public void calX_axisTest()
    {
        int actual= ra.calX_axis("(44,67)");
        int expected= 44;
        assertEquals(actual,expected);

    }
    @Test
    public void calY_axisTest()
    {
        int actual= ra.calY_axis("(44,67)");
        int expected= 67;
        assertEquals(actual,expected);

    }
    @Test
    public void calY_axisTest1()
    {
        int actual= ra.calY_axis("(44,2)");
        int expected= 0;
        assertNotEquals(actual,expected);

    }


}