
package com.example.trackbuddy.walkthrough;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Html;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.viewpager.widget.ViewPager;

        import com.example.trackbuddy.LoginActivity;
        import com.example.trackbuddy.R;

public class WalkThrough_reg extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotlayout;
    Button backbtn,nextbtn,skipbtn;

    TextView[] Dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through_reg);


        backbtn = findViewById(R.id.backButton);
        nextbtn = findViewById(R.id.nextButton);
        skipbtn = findViewById(R.id.skipButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getitem(0)>0)
                {
                    mSlideViewPager.setCurrentItem(getitem(-1),true);
                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getitem(0)<3)
                {
                    mSlideViewPager.setCurrentItem(getitem(1),true);
                }else
                {
                    Intent i = new Intent(WalkThrough_reg.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(WalkThrough_reg.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });


        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotlayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mSlideViewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


    }
    public void setUpIndicator(int position)
    {
        Dots = new TextView[4];
        mDotlayout.removeAllViews();

        for(int i = 0;i< Dots.length;i++)
        {
            Dots[i] = new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226"));
            Dots[i].setTextSize(35);
            Dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotlayout.addView(Dots[i]);
        }
        Dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);
            if(position>0 )
            {
                backbtn.setVisibility(View.VISIBLE);

            }else
            {
                backbtn.setVisibility(View.INVISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i)
    {
        return mSlideViewPager.getCurrentItem()+i;
    }




}