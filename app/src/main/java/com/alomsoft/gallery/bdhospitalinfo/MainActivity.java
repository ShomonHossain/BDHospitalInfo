package com.alomsoft.gallery.bdhospitalinfo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView splashImageView;
    boolean splashloading = false;


    Fragment fragment=null;
    FragmentManager fm;
    FragmentTransaction ft;


    private boolean isLogin =false;

    private Button signupBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashImageView = new ImageView(this);
        splashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        splashImageView.setImageResource(R.drawable.splash_screen);
        setContentView(splashImageView);
        splashloading = true;
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                splashloading = false;
                setContentView(R.layout.activity_main);
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                FragmentLogin fragmentLogin = new FragmentLogin();
                ft.add(R.id.fragmentContainer,fragmentLogin);
                ft.commit();
            }

        }, 2000);

        signupBTN =findViewById(R.id.signBTN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem home = menu.findItem(R.id.menuHome);
        MenuItem login = menu.findItem(R.id.menuLogin);
        MenuItem logout = menu.findItem(R.id.menuLogout);
        MenuItem setting = menu.findItem(R.id.menuSetting);
        MenuItem help = menu.findItem(R.id.menuHelp);
        MenuItem signup = menu.findItem(R.id.menuSignup);

        if(!isLogin){
            login.setVisible(true);
            signup.setVisible(true);
            home.setVisible(true);
            help.setVisible(true);

            logout.setVisible(false);
            setting.setVisible(false);
        }else{
            login.setVisible(false);
            signup.setVisible(false);

            home.setVisible(true);
            logout.setVisible(true);
            setting.setVisible(true);
            help.setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuHome:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogin:
                fragment = new FragmentLogin();
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSetting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSignup:
                fragment = new FragmentSignup();
                break;
            case R.id.menuHelp:
                fragment = new FragmentHelp();
                break;
        }

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer,fragment);
        ft.commit();
        return super.onOptionsItemSelected(item);
    }

    public void signClick(View view) {
        fragment = new FragmentSignup();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer,fragment);
        ft.commit();
        Toast.makeText(this, "Sign Up", Toast.LENGTH_SHORT).show();
    }
}
