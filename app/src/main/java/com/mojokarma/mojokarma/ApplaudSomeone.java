package com.mojokarma.mojokarma;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class ApplaudSomeone extends ActionBarActivity implements View.OnClickListener {

    ImageButton a,b,c,d,e,f,g,h,send,done;

    String name,msg ;
    int p,q,r,s,t,u,w,x;
    private MobileServiceTable<Applaud> mToDoTable;
    private MobileServiceClient mClient;
    private Applaud mAdapter;

    Globals gb = Globals.getInstance();

    int[] images1={R.mipmap.colloboration,R.mipmap.ccoll,};
    int[] images2={R.mipmap.innovation,R.mipmap.cinnov,};
    int[] images3={R.mipmap.integrity,R.mipmap.cintegtr,};
    int[] images4={R.mipmap.technicalexc,R.mipmap.ctechx,};
    int[] images5={R.mipmap.nimble,R.mipmap.cnimble,};
    int[] images6={R.mipmap.passion,R.mipmap.cpassion,};
    int[] images7={R.mipmap.nimble,R.mipmap.cnimble,};
    int[] images8={R.mipmap.passion,R.mipmap.cpassion,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applaud_someone);
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        send = (ImageButton) findViewById(R.id.ib1);
        done = (ImageButton) findViewById(R.id.ib2);
        done.setVisibility(View.GONE);


        a=(ImageButton)findViewById(R.id.imgb1);
        a.setOnClickListener(this);
        b=(ImageButton)findViewById(R.id.imgb2);
        b.setOnClickListener(this);
        c=(ImageButton)findViewById(R.id.imgb3);
        c.setOnClickListener(this);
        d=(ImageButton)findViewById(R.id.imgb4);
        d.setOnClickListener(this);
        e=(ImageButton)findViewById(R.id.imgb5);
        e.setOnClickListener(this);
        f=(ImageButton)findViewById(R.id.imgb6);
        f.setOnClickListener(this);
        g=(ImageButton)findViewById(R.id.imgb7);
        g.setOnClickListener(this);
        h=(ImageButton)findViewById(R.id.imgb8);
        h.setOnClickListener(this);



        TextView username;
        username= (TextView) findViewById(R.id.txtusername);


        SharedPreferences prefs = getSharedPreferences("login anme", MODE_PRIVATE);
        name = prefs.getString("name", "");
       // username.setText(name);
        username.setText(gb.getValue());

        String designation=getIntent().getExtras().getString("designation");
        TextView designa= (TextView) findViewById(R.id.designationtxt2);
        designa.setText(designation);



        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.kristy);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, 200, 200, false);
        Bitmap conv_bm=getCircleBitmap(resizedBitmap, 100);
        // set circle bitmap
        ImageView mImage = (ImageView) findViewById(R.id.profile_image);
        mImage.setImageBitmap(conv_bm);

        final EditText box=(EditText)findViewById(R.id.edttxtme);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Applaud item = new Applaud();

                msg=box.getText().toString();

                item.setContent(msg);
                item.setComplete(false);
                item.setTo(gb.getValue());
                item.setFrom(name);

                addItem(v, item);
                send.setVisibility(View.GONE);
                done.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addItem(View view, final Applaud item) {

        try {
// Create the Mobile Service Client instance, using the provided
			// Mobile Service URL and key
			mClient = new MobileServiceClient(
                    "https://apploud.azure-mobile.net/",
                    "ekaeTCOfAomLFAWiZtuwXltIneSuxo",
					this);

            // Get the Mobile Service Table instance to use
			mToDoTable = mClient.getTable(Applaud.class);
		} catch (MalformedURLException e) {
			//createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
            e.printStackTrace();
		}
        // Create a new item


//		TODO Uncomment the the following code when using a mobile service
        // Insert the new item
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mToDoTable.insert(item).get();
                   /** if (!item.isComplete()) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                mAdapter.add(item);
                            }
                        });
                    }**/
                } catch (Exception exception) {
                   // createAndShowDialog(exception, "Error");
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();

    }


    private Bitmap getCircleBitmap(Bitmap bitmap , int pixels) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(100,100, 88, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.applaudcreater, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imgb1:

                p++;
                p = p % images1.length;
                a.setImageResource(images1[p]);
                Toast toast=Toast.makeText(getApplicationContext(), "colloboration", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                break;

            case R.id.imgb2:

                q++;
                q=q%images2.length;
                b.setImageResource(images2[q]);
                Toast toast2=Toast.makeText(getApplicationContext(), "innovation", Toast.LENGTH_SHORT);
                toast2.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast2.show();
                break;


            case R.id.imgb3:

                r++;
                r=r%images3.length;
                c.setImageResource(images3[r]);
                Toast toast3=Toast.makeText(getApplicationContext(), "integrity", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();
                break;

            case R.id.imgb4:

                s++;
                s=s%images4.length;
                d.setImageResource(images4[s]);
                Toast toast4=Toast.makeText(getApplicationContext(), "technicalexc", Toast.LENGTH_SHORT);
                toast4.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast4.show();
                break;

            case R.id.imgb5:

                t++;
                t=t%images5.length;
                e.setImageResource(images5[t]);
                Toast toast5=Toast.makeText(getApplicationContext(), "nimble", Toast.LENGTH_SHORT);
                toast5.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast5.show();
                break;


            case R.id.imgb6:
                u++;
                u=u%images6.length;
                f.setImageResource(images6[u]);
                Toast toast6=Toast.makeText(getApplicationContext(), "passion", Toast.LENGTH_SHORT);
                toast6.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast6.show();
                break;

            case R.id.imgb7:

                w++;
                w=w%images7.length;
                g.setImageResource(images7[w]);
                Toast toast7=Toast.makeText(getApplicationContext(), "nimble", Toast.LENGTH_SHORT);
                toast7.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast7.show();
                break;

            case R.id.imgb8:

                x++;
                x = x % images8.length;
                h.setImageResource(images8[x]);
                Toast toast8=Toast.makeText(getApplicationContext(), "passion", Toast.LENGTH_SHORT);
                toast8.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast8.show();
                break;

        }
    }

}
