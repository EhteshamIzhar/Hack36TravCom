package com.android.parii.travcom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.parii.travcom.Common.Common;
import com.android.parii.travcom.Interface.ItemClickListener;
import com.android.parii.travcom.Model.Category;
import com.android.parii.travcom.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;
    ImageButton b1;

    TextView txtFullName,bitcoins;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        b1 = (ImageButton) findViewById(R.id.vr);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,VrActivity.class);
                startActivity(i);
            }
        });

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(Home.this, chatBot.class);
                 startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //    .setAction("Action", null).show();
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView) headerView.findViewById(R.id.txtFullName);
        bitcoins = (TextView) headerView.findViewById(R.id.bitcoins);//doubt *********
  //      txtFullName.setText(Common.currentUser.getName());

        Log.d("me",""+Common.x);
        bitcoins.setText(""+Common.x);
        //Load full menu
        recycler_menu = (RecyclerView) findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();
    }











    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.menu_item, MenuViewHolder.class, category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
               viewHolder.txtMenuName.setText(model.getName());
               Picasso.with(getBaseContext()).load(model.getImage())
                       .into(viewHolder.imageView);
               final Category clickItem = model;
               viewHolder.setItemClickListener(new ItemClickListener() {
                   @Override
                   public void onClick(View view, int position, boolean isLongClick) {
//                       Toast.makeText(Home.this,""+clickItem.getImage(),Toast.LENGTH_SHORT).show();
                       //Get CategoryID and send to new Activity
                       Intent foodList = new Intent(Home.this,FoodList.class);
                       //Because CategoryID is key, so we just get key of this item
                       foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                   }
               });

            }

    };

        recycler_menu.setAdapter(adapter);


}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.songs: {
                Intent i = new Intent(Home.this,BitCoinActivity.class);
                startActivity(i);

               // Toast.makeText(getApplicationContext(), "Sun le zara", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.filter:
                Toast.makeText(getApplicationContext(),"filter it bro", Toast.LENGTH_SHORT).show();
                // another startActivity, this is for item with id "menu_item2"
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;


        //return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.mood)
        {
                Intent i = new Intent(Home.this,Detect.class);
                startActivity(i);
        }
        else if (id == R.id.d1)
        {//book
            Toast.makeText(getBaseContext(),"OKAY FINE",Toast.LENGTH_LONG);
            Intent i = new Intent(Home.this, ttsJava.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.d2)
        {


            //text
        } else if (id == R.id.d3)
        {
            //fitness
        } else if (id == R.id.d4)
        {
            Intent i = new Intent(Home.this,MapsActivity.class);
            startActivity(i);
            //nearby

        }else if (id == R.id.d5)
        {

            //logout
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
