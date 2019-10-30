package com.cricker.admin.Activity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.cricker.admin.Adapter.ViewPagerAdapter;
import com.cricker.admin.BuildConfig;
import com.cricker.admin.Fragment.Tab5;
import com.cricker.admin.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {


    Toolbar toolbar;
    String answer;
    String[] list = {"Home", "Predictions", "Ranking", "Tab4", "Team Maker"};
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private TextView category;
    private RelativeLayout relativeLayout;
    private ValueAnimator valueAnimator;
    private DrawerLayout drawerLayout;
    private boolean isRunning;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
            ShowAcceptDialog();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(null);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
//                    actionBar.setBackgroundDrawable(new ColorDrawable(R.drawable.offers_background));
        getWindow().setStatusBarColor(0x00000000);

        drawerLayout = findViewById(R.id.container);
        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);

                drawerLayout.closeDrawers();

                int id = item.getItemId();

                if (id == R.id.nav_share) {

                    try {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "All Sports Predictions");
                        String sAux = "\nDownlload this  App and get predictions for all fantasy matches \n\n";
                        sAux = sAux + "https://play.google.com/store/apps/details?id=com.cricker.app\n";
                        i.putExtra(Intent.EXTRA_TEXT, sAux);
                        startActivity(Intent.createChooser(i, "Share with"));
                    } catch (Exception e) {
                        //e.toString();
                    }

                } else if (id == R.id.nav_rate) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.cricker.app")));

                } else if (id == R.id.nav_feedback) {

                    Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_report) {

                    Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_privacy_policy) {

                    Intent i = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_disclosure) {

                    Intent i = new Intent(getApplicationContext(), DisclosureActivity.class);
                    startActivity(i);

                } else if (id == R.id.action_moreapps) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=StatusStock")));


                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        relativeLayout = findViewById(R.id.relativeLayout);
        valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#0030c1"), Color.parseColor("#ed0082"), Color.parseColor("#d66f0e"), Color.parseColor("#ff0558"), Color.parseColor("#1ca055"));
        valueAnimator.setDuration((5 - 1) * 100000000);


        category = findViewById(R.id.category);
        viewPager = findViewById(R.id.viewPager);


        fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        adapter.AddFragment(new Tab5(), "Cricket");
//        adapter.AddFragment(new Tab6(), "Football");
//        adapter.AddFragment(new Tab7(), "Kabaddi");
//        adapter.AddFragment(new Tab8(), "NBA");
//        adapter.AddFragment(new Tab9(), "Smart Team Maker");

        viewPager.setClipToPadding(false);
        viewPager.setPadding(50, 50, 50, 0);
        viewPager.setPageMargin(10);


        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                valueAnimator.setCurrentPlayTime((long) ((v + i) * 100000000));
                String data = list[i];
                category.setText(data);
            }

            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                relativeLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                answer = "You are connected to a WiFi Network";
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                answer = "You are connected to a Mobile Network";


            Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();

        } else {


            answer = "No internet Connectivity. Please Connect to the Internet";

            @SuppressLint("CutPasteId") final Snackbar snackbar = Snackbar.make(findViewById(R.id.container), "No internet connection!", 1000000);
            snackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    snackbar.dismiss();

                }
            });

// Changing message text color
            snackbar.setActionTextColor(Color.YELLOW);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

//        Update Check

        isRunning = true;

        UpdateChecker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void ShowAcceptDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.checkbox, null);

        TextView textview = (TextView) view.findViewById(R.id.textmsg);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disclosure ");
        textview.setText(" \n" + "\n" + "What information does the Application obtain and how is it used?\n" + " \n" + "\n" + " \n" + "\n" + "User Provided Information\n" + " \n" + "\n" + "The Application obtains the information you provide when you download and install the Application. There is no registration/login options. All users can directly access our application features.\n" + "\n" + " \n" + "\n" + "When you provide any feedback or report any issue inside the Application, you generally provide\n" + "\n" + " \n" + "\n" + "(a) Your name, email address and the message regarding the feedback or report.\n" + "(b) Along with your message we also collect your Unique Device ID to store your message.\n" + "Once we received your feedback or report, we try to improve our application as per your feedback or report. And after that, we removed your name, mail ID, Unique Device ID and the message from our database.\n" + "\n" + " \n" + "\n" + "Automatically Collected Information\n" + " \n" + "\n" + "In addition, the Application may collect certain information automatically, including, but not limited to, the type of mobile device you use, the IP address of your mobile device, your mobile operating system, the type of mobile Internet browsers you use, and information about the way you use the Application.\n" + "\n" + " \n" + "\n" + "We may disclose User Provided and Automatically Collected Information:\n" + "\n" + "1) As required by law, such as to comply with a subpoena or similar legal process;\n" + "\n" + "2) when we believe in good faith that disclosure is necessary to protect our rights, protect your safety or the safety of others, investigate fraud, or respond to a government request;\n" + "\n" + "3) with our trusted services providers who work on our behalf, do not have an independent use of the information we disclose to them and have agreed to adhere to the rules set forth in this privacy statement.\n" + "\n" + "4)if this app(All Sports Predictions) is involved in a merger, acquisition, or sale of all or a portion of its assets, you will be notified via email and/or a prominent notice on our Website of any change in ownership or uses of this information, as well as any choices you may have regarding this information.\n" + "\n" + "5) To advertisers and third-party advertising networks and analytics companies as described in the section below\n" + "\n" + " \n" + "\n" + "Automatic Data Collection and Advertising\n" + " \n" + "\n" + "We may work with analytics companies to help us understand how the Application is being used, such as the type of mobile device you use, your mobile operating system, and information about the way you use the Application, frequency, and duration of usage. We work with advertisers and third-party advertising networks, who need to know how you interact with advertising provided in the Application which helps us keep the cost of the Application low. Advertisers and advertising networks use some of the information collected by the Application, including, but not limited to, the unique identification ID of your mobile device and your Facebook profile ID. To protect the anonymity of this information, we use an encryption technology to help ensure that these third parties can’t identify you personally. These third parties may also obtain anonymous information about other applications you’ve downloaded to your mobile device, the mobile websites you visit, your non-precise location information (e.g., your zip code), and other non-precise location information in order to help analyze and serve anonymous targeted advertising on the Application and elsewhere. We may also share encrypted versions of information you have provided in order to enable our partners to append other available information about you for analysis or advertising related use.\n" + "\n" + " \n");
        builder.setView(view).setCancelable(false);
        builder.setPositiveButton("Agree & Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

            }
        }).show();

    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void showStartDialog() {

        new AlertDialog.Builder(this).setTitle(Html.fromHtml("<font color='#FF0000'>Important!</font>")).setMessage("Please read the privacy policy carefully before proceed.").setCancelable(false).setPositiveButton("Read", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                startActivity(i);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }

    public void UpdateChecker() {


        Firebase myFirebase;

        Firebase.setAndroidContext(getApplicationContext());

        myFirebase = new Firebase("https://crickerdemo-f5ce7.firebaseio.com/Version/currentVersion");

        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                Integer version = Integer.valueOf(dataSnapshot.getValue(String.class));

                Integer appVersion = BuildConfig.VERSION_CODE;

                if (version > appVersion) {


                    new AlertDialog.Builder(HomeActivity.this).setTitle(Html.fromHtml("<font color='#00838F'>Update Available</font>")).setMessage("New Version is available....Download and enjoy the new features").setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.cricker.app")));

                        }
                    }).show();


                } else {


                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


}
