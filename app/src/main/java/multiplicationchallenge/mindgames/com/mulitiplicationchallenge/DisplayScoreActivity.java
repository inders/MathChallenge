package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by indersingh on 9/4/16.
 */
public class DisplayScoreActivity extends Activity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.score_layout);
        TextView textView = (TextView) findViewById(R.id.scoreView);
        Intent intent = getIntent();
        int score = intent.getExtras().getInt(Constants.SCORE);
        textView.setText("Your final score is = " + score + "/" + Constants.TOTAL_GAME_PLAY_COUNT);

        Button button = (Button) findViewById(R.id.playAgain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);


            }
        });
    }
}
