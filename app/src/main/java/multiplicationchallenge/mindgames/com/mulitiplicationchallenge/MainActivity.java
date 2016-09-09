package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by indersingh on 9/4/16.
 */
public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        context = this;

        SharedPreferences pref = getApplicationContext().
                getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        String userName = pref.getString(Constants.PROFILE_NAME, null);
        if (userName == null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }

        GridView gridView = (GridView) findViewById(R.id.startLayout);
        gridView.setAdapter(new MainActivityAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String optionSelected = (String) adapterView.getAdapter().getItem(i);
                if (optionSelected.equalsIgnoreCase(Constants.PRACTICE)) {
                    Intent intent = new Intent(context, GameStartActivity.class);
                    intent.putExtra(Constants.MODE, Constants.PRACTICE);
                    startActivity(intent);
                } else if (optionSelected.equalsIgnoreCase(Constants.CHALLENGE)) {
                    Intent intent = new Intent(context, GameStartActivity.class);
                    intent.putExtra(Constants.MODE, Constants.CHALLENGE);
                    startActivity(intent);
                } else if (optionSelected.equalsIgnoreCase(Constants.MYSCORE)) {
                    Intent intent = new Intent(context, AllScoresActivity.class);
                    startActivity(intent);
                } else if (optionSelected.equalsIgnoreCase(Constants.PROFILE)) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
