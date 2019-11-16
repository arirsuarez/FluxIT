package com.example.fluxit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Binding elements with ButterKnife

    @BindView(R.id.mainActivityToolbar)
    Toolbar mainActivityToolbar;
    @BindView(R.id.searchview_action)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mainActivityToolbar);

    }

    // Menu Inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        searchView.setMenuItem(menu.findItem(R.id.search_action));
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
}
