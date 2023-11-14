package com.example.asm_mob403.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.asm_mob403.R;
import com.example.asm_mob403.fragment.HomeFragment;
import com.example.asm_mob403.fragment.QuanLiFragment;
import com.example.asm_mob403.fragment.SettingFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout layoutcontend;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout  = findViewById(R.id.id_layoutdrawer);
        toolbar = findViewById(R.id.id_toolbar);
        layoutcontend = findViewById(R.id.id_layoutcontend);
        navigationView = findViewById(R.id.id_navView);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =   new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        loadHomeFragment();
//        if(savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.id_layoutcontend,new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.home);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            replaceFragment(HomeFragment.newInstance());
        } else if(id==R.id.quanli) {
            replaceFragment(QuanLiFragment.newInstance());
        } else if(id==R.id.setting) {
            replaceFragment(SettingFragment.newInstance());
        } else if(id==R.id.thoat) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // xóa activity mở trc đó
            startActivity(intent);
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
        }



    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontend, fragment);
        transaction.commit();
    }
    public void loadHomeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontend,new HomeFragment());
        transaction.commit();
    }
}