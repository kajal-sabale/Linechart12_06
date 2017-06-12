package studytutorial.in.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import android.bluetooth.BluetoothSocket;

import java.util.ArrayList;



import java.util.ArrayList;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.R.color;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.bluetooth.BluetoothSocket;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ReciveActivity extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {
    /**
     * Using MpAndroidChart Library, we can draw various graph but focus of this tutorial will be on Line chart in your Android Application.
     * A line chart or line graph is a type of chart which displays information as a series of data points called ‘markers’
     * connected by straight line segments. It is a basic type of chart common in many fields.
     * The Library works for Android API 8 and above but if we are using animations, it works on API 11 and above.
     */
    private LineChart mChart;

    /**
     *
     */
    ArrayList<Entry> entries= new ArrayList();
    /**
     *
     */

    ArrayList<Axis> al=new ArrayList<Axis>();

    static BluetoothSocket bt;
    public static final int MESSAGE_READ = 0;

    private static final String TAG = "MY_APP_DEBUG_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        mChart = (LineChart) findViewById(R.id.chart);

        bt=StaticVariables.getSocket();
        DataRecieving dr=new DataRecieving(bt,handler);


    }

    public boolean validateBluetooth(String name)
    {
        if(name=="kajal")
            return true;
        else
            return false;
    }
  /*  private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));

        return yVals;
    }*/

    /**
     *
     * @param cord -The cord is the value of data passsed from sender side to receiver side.
     *              It returns the value of x co-ordinate that is co-ordinate that is .
     * @return int
     */
    public int calX_axis(String cord)
    {
        System.out.println(cord);
        String x_Axis=cord.substring(1, cord.indexOf(","));
        System.out.println("sub string wala data before return"+x_Axis);

        Integer i=new Integer(x_Axis);
        int xxxx=i.intValue();
        System.out.println("x data parced successfully");
        return xxxx;
    }

    /**
     *
     * @param cord The cord is the value of data passsed from sender side to receiver side.
     *              It returns the value of y co-ordinate that is co-ordinate that is.
     * @return int
     */
    public int calY_axis(String cord)
    {
        System.out.println(cord);
        String y_Axis=cord.substring(cord.indexOf(",")+1,cord.indexOf(")"));
        System.out.println("sub string wala data before return"+y_Axis);
        Integer k=new Integer(y_Axis);
        int yyyy=k.intValue();
        return yyyy;
    }

    /**
     * Here, we set touchEnabled for  moving the graph forward and backward direction.
     */

    private void setData() {

        mChart.setOnChartGestureListener(this);

        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawGridBackground(false);

        mChart.setDescription("");

        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        LimitLine upper_limit = new LimitLine(180f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);


        mChart.animateX(0, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();


        Thread tt=null;
        tt=new Thread(){

            public void run() {
                System.out.println("inside run of addEntryR()");

                ArrayList al1=new ArrayList();
                if(al.size()==1)
                {
                    System.out.println("Arraylist side 1");
                    entries.clear();
                    entries.add(new Entry(al.get(0).getX(), 0));
                    entries.add(new Entry(0, 1));
                    entries.add(new Entry(0f, 2));
                    entries.add(new Entry(0f, 3));

                    LineDataSet dataset = new LineDataSet(entries, "# of Calls");
                    ArrayList<String> labels = new ArrayList<String>();

                    labels.add(Integer.toString(0));
                    labels.add(Integer.toString(1));
                    labels.add(Integer.toString(2));
                    labels.add(Integer.toString(3));
                    LineData data = new LineData(labels, dataset);
                    mChart.setData(data);

                }
                else if(al.size()==2)
                {
                    System.out.println("Arraylist side 2");
                    entries.clear();
                    entries.add(new Entry(al.get(0).getX(), 0));
                    entries.add(new Entry(al.get(1).getX(), 1));
                    entries.add(new Entry(0f, 2));
                    entries.add(new Entry(0f, 3));

                    LineDataSet dataset = new LineDataSet(entries, "# of Calls");
                    ArrayList<String> labels = new ArrayList<String>();

                    labels.add(Integer.toString(0));
                    labels.add(Integer.toString(1));
                    labels.add(Integer.toString(2));
                    labels.add(Integer.toString(3));
                    LineData data = new LineData(labels, dataset);
                    mChart.setData(data);
                }

                else if(al.size()==3)
                {
                    System.out.println("Arraylist side 3");
                    entries.clear();
                    entries.add(new Entry(al.get(0).getX(), 0));
                    entries.add(new Entry(al.get(1).getX(), 1));
                    entries.add(new Entry(al.get(2).getX(), 2));
                    entries.add(new Entry(0f, 3));

                    LineDataSet dataset = new LineDataSet(entries, "# of Calls");
                    ArrayList<String> labels = new ArrayList<String>();

                    labels.add(Integer.toString(0));
                    labels.add(Integer.toString(1));
                    labels.add(Integer.toString(2));
                    labels.add(Integer.toString(3));
                    LineData data = new LineData(labels, dataset);
                    mChart.setData(data);

                }

                else if(al.size()==4)
                {
                    System.out.println("Arraylist side 4");
                    entries.clear();
                    entries.add(new Entry(al.get(0).getX(), 0));
                    entries.add(new Entry(al.get(1).getX(), 1));
                    entries.add(new Entry(al.get(2).getX(), 2));
                    entries.add(new Entry(al.get(3).getX(), 3));

                    LineDataSet dataset = new LineDataSet(entries, "# of Calls");
                    ArrayList<String> labels = new ArrayList<String>();

                    labels.add(Integer.toString(al.get(0).getY()));
                    labels.add(Integer.toString(al.get(1).getY()));
                    labels.add(Integer.toString(al.get(2).getY()));
                    labels.add(Integer.toString(al.get(3).getY()));

                    LineData data = new LineData(labels, dataset);
                    mChart.setData(data);
                }



                mChart.postInvalidate();


            };
        };
        tt.start();


    }



    private Handler handler = new Handler(new Callback() {



        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG,"inside handler call back method  "+msg.what);

            switch (msg.what) {


                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;

                    String readMessage = new String(readBuf, 0, msg.arg1);
                    //	chatArrayAdapter.add(connectedDeviceName + ":  " + readMessage);
                    Log.i(TAG,"the required msg is "+readMessage);
                    //	et.setText(readMessage);

                    Log.i(TAG,"chk data is recieved successfully");
                    int x=calX_axis(readMessage);
                    int y=calY_axis(readMessage);
                    System.out.println("data parced successfully");
                    Axis ax = new Axis(y,x);
                    if(al.size()<4)
                    {
                        System.out.println("inside if condtn of handler");
                        al.add(ax);
                    }
                    else
                    {
                        System.out.println("inside else condtn of handler");
                        al.remove(0);
                        al.add(ax);
                    }

                    setData();


                    break;

            }
            return false;
        }
    });





    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
