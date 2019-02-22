package com.l.marc.tinderpi;

//import android.support.design.widget.TabLayout;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.l.marc.tinderpi.Cartas.CartasFragmentTab;

public class MainActivity extends AppCompatActivity implements NavigationHost,TabHost.inicializarTabHost,PerfilFragmentTab.FragmentInteractionPerfil,CartasFragmentTab.FragmentInteractionListenerCartas,ChatFragmentTab.FragmentListenerChat, fragmentCardView.OnFragmentInteractionListener{
    public RelativeLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabHost fragmentPrueba = TabHost.newInstance("1","2");
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new loginFragment())
                    .commit();
        }


    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }








    @Override
   public void iniciliazarTab(TabLayout tl1, final ViewPager v1) {

       tl1.addTab(tl1.newTab().setText("PERFIL"));
       tl1.addTab(tl1.newTab().setText("CARTAS"));
       tl1.addTab(tl1.newTab().setText("CHAT"));
       tl1.setTabGravity(tl1.GRAVITY_FILL);
       final PagerAdapter pageAd = new PagerAdapter(getSupportFragmentManager(),tl1.getTabCount());

        v1.setAdapter(pageAd);
        v1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl1));

        tl1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                v1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public void FragmentCartasTab() {



    }

    @Override
    public void FragmentPerfilTab() {


    }

    @Override
    public void onFragmentCharTab() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


   /* public void alertDatePicker() {
        DatePickerDialog

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendario, null, false);

        // the time picker on the alert dialog, this is how to get the value
        final DatePicker myDatePicker = (DatePicker) view.findViewById(R.id.myDatePicker);

        // so that the calendar view won't appear
        myDatePicker.setCalendarViewShown(false);

        // the alert dialog
        new AlertDialog.Builder(MainActivity.this).setView(view)
                .setTitle("Set Date")
                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {


                        int month = myDatePicker.getMonth() + 1;
                        int day = myDatePicker.getDayOfMonth();
                        int year = myDatePicker.getYear();

                        Toast.makeText(getApplicationContext(),month + "/" + day + "/" + year,Toast.LENGTH_SHORT).show();

                        dialog.cancel();

                    }

                }).show();
        */



