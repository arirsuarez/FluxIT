package com.example.fluxit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Binding elements with ButterKnife

    @BindView(R.id.mainActivityToolbar)
    Toolbar mainActivityToolbar;

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
        return true;
    }

    // Setting actions for toolbar buttons
    // TODO Set SearchView & AboutMe
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String actionIName = (String) item.getTitle();
        Toast.makeText(getApplicationContext(), "Selected option: " + actionIName, Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.search_action:
                break;
            case R.id.about_me_action:
                break;
        }
        return true;
    }
}
