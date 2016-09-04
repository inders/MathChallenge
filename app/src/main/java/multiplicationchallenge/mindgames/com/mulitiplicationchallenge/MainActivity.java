package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Integer[] numbersArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        GridView numbersView = (GridView) findViewById(R.id.numbersView);
        numbersView.setAdapter(new NumbersAdapter(this));
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer number = (Integer) adapterView.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, "Ok! Starting game for " + number.toString(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra(Constants.TABLE_NUM, number.toString());
                startActivity(intent);
            }
        });


    }
}
