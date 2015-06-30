package com.mojokarma.mojokarma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class ApplaudSomeone extends ActionBarActivity {

    ImageButton a,b,c,d,e,f,g,h;
    int p,q,r,s,t,u,w,x;
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
        buttonclick1();
        buttonclick2();
        buttonclick3();
        buttonclick4();
        buttonclick5();
        buttonclick6();
        buttonclick7();
        buttonclick8();

    }


    public void buttonclick1(){

        a=(ImageButton)this.findViewById(R.id.imgb1);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p++;
                p = p % images1.length;
                a.setImageResource(images1[p]);


            }
        });
    }


    public void buttonclick2(){

        b=(ImageButton)this.findViewById(R.id.imgb2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                q++;
                q=q%images2.length;
                b.setImageResource(images2[q]);

            }
        });
    }



    public void buttonclick3(){

        c=(ImageButton)this.findViewById(R.id.imgb3);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                r++;
                r=r%images3.length;
                c.setImageResource(images3[r]);

            }
        });
    }



    public void buttonclick4(){

        d=(ImageButton)this.findViewById(R.id.imgb4);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                s++;
                s=s%images4.length;
                d.setImageResource(images4[s]);


            }
        });
    }



    public void buttonclick5(){

        e=(ImageButton)this.findViewById(R.id.imgb5);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                t++;
                t=t%images5.length;
                e.setImageResource(images5[t]);


            }
        });
    }


    public void buttonclick6(){

        f=(ImageButton)this.findViewById(R.id.imgb6);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u++;
                u=u%images6.length;
                f.setImageResource(images6[u]);


            }
        });
    }


    public void buttonclick7(){

        g=(ImageButton)this.findViewById(R.id.imgb7);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                w++;
                w=w%images7.length;
                g.setImageResource(images7[w]);

            }
        });
    }



    public void buttonclick8(){

        h=(ImageButton)this.findViewById(R.id.imgb8);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                x++;
                x=x%images8.length;
                h.setImageResource(images8[x]);

            }
        });
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
        if (id == R.id.back) {

            Intent i;
            i=new Intent(ApplaudSomeone.this,Profile.class);
            startActivity(i);
            return true;
        }

        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
