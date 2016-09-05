package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GameStartActivity extends Activity {

    private Integer[] numbersArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start_layout);
        final Context context = this;

        final String mode = getIntent().getExtras().getString(Constants.MODE);
        GridView numbersView = (GridView) findViewById(R.id.numbersView);
        numbersView.setAdapter(new NumbersAdapter(this));
        numbersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer number = (Integer) adapterView.getAdapter().getItem(i);
                Toast.makeText(GameStartActivity.this, "Ok! Starting game for " + number.toString(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra(Constants.TABLE_NUM, number.toString());
                intent.putExtra(Constants.MODE, mode);
                startActivity(intent);
            }
        });


    }
}
