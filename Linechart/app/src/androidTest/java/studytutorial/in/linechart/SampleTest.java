package studytutorial.in.linechart;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

/**
 * Created by a634941 on 31-05-2017.
 */

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class SampleTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    @TargetApi(Build.VERSION_CODES.FROYO)
    public SampleTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testEditTest()
    {
        Button bt=(Button)getActivity().findViewById(R.id.button1);
        assertNotNull(bt);
    }
}

