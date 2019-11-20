package com.example.fluxit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fluxit.Interfaces.ApiService;
import com.example.fluxit.Model.Results;
import com.example.fluxit.Model.User;
import com.example.fluxit.api.ApiClient;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {

    // Binding elements with ButterKnife

    @BindView(R.id.mainActivityToolbar)
    Toolbar mainActivityToolbar;
    @BindView(R.id.searchview_action)
    MaterialSearchView searchView;
    @BindView(R.id.userContainerRecyclerMain)
    RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Results results;
    User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mainActivityToolbar);

       layoutManager = new LinearLayoutManager(MainActivity.this);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);

       LoadJson();



    }

    // Menu Inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        searchView.setMenuItem(menu.findItem(R.id.search_action));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search_action);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search User...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.length()>3){
                    LoadJson();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                LoadJson();
                return true;
            }
        });

        searchMenuItem.getIcon().setVisible(false,false);

        return true;
    }

    // Setting actions for toolbar buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*String actionIName = (String) item.getTitle();
        Toast.makeText(getApplicationContext(), "Selected option: " + actionIName, Toast.LENGTH_SHORT).show();*/

        switch (item.getItemId()) {
            case R.id.about_me_action:
                WebView linkedIn = new WebView(this);
                setContentView(linkedIn);
                linkedIn.loadUrl("https://www.linkedin.com/feed/");
                break;
        }
        return true;
    }

    public void LoadJson(){

        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Call<Results> call;
        call = apiService.getApiResults();

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.isSuccessful() && response.body().getUserList() != null){

                    userList = response.body().getUserList();

                    adapter = new Adapter(userList, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Service Error. Please Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);

            User user = userList.get(position);
            intent.putExtra("profileLargePicture", user.getPicture().getLarge());
            intent.putExtra("userEmailUserActivity", user.getEmail());

            startActivity(intent);

    }
}
