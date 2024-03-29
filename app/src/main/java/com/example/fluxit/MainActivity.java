package com.example.fluxit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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

import com.example.fluxit.controller.UserController;
import com.example.fluxit.model.dao.UsersContainer;
import com.example.fluxit.model.pojo.User;
import com.example.fluxit.util.ResultListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Adapter.BoxListener {

    // Binding elements with ButterKnife

    @BindView(R.id.mainActivityToolbar)
    Toolbar mainActivityToolbar;
    @BindView(R.id.searchview_action)
    MaterialSearchView searchView;
    @BindView(R.id.userContainerRecyclerMain)
    RecyclerView recyclerView;
    private List<User> userList;
    private LinearLayoutManager layoutManager;
    private Adapter recyclerViewAdapter;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mainActivityToolbar);

        userController = new UserController();
        layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        userList = new ArrayList<>();


        requestApiUserList();

        recyclerViewAdapter = new Adapter(userList, this);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastBox = layoutManager.findLastVisibleItemPosition();
                int recyclerSize = recyclerViewAdapter.getItemCount();

                if (lastBox >= recyclerSize - 5){

                    requestApiUserList();
                }
            }
        });


    }

    private void requestApiUserList() {

        userController.userApiRequest(new ResultListener<UsersContainer>() {
            @Override
            public void onFinish(UsersContainer results) {
                userList = results.getResults();
                recyclerViewAdapter.refreshRecyclerList(userList);
            }
        });
    }

    @Override
    public void userPicked(User userPicked) {

        // Toast.makeText(this, userPicked.getLogin().getUsername(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(UserInfoActivity.KEY_USER, userPicked);
        intent.putExtras(bundle);

        startActivity(intent);
        //Fragment, instance, .setArguments(), userPicked, stick Fragment y and getArguments()
        //
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

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

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
                linkedIn.loadUrl("https://www.linkedin.com/in/ariel-suarez");
                break;
        }
        return true;
    }


}
