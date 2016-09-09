package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by indersingh on 9/4/16.
 */
public class DisplayScoreActivity extends Activity {
    private Context context;
    private String tableNum;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.show_score_layout);
        TextView textView = (TextView) findViewById(R.id.scoreView);
        Intent intent = getIntent();
        score = intent.getExtras().getInt(Constants.SCORE);
        int problemsAttempted = intent.getExtras().getInt(Constants.PROBLEMS_ATTEMPTED);
        tableNum = intent.getExtras().getString(Constants.TABLE_NUM);

        textView.setText("Your final score is = " + score + "/" + problemsAttempted);
        manageScore();

        FancyButton button = (FancyButton) findViewById(R.id.playAgain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
            }
        });
    }


    private void manageScore() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        int prevScore = pref.getInt(tableNum, 0);
        if (score > prevScore) {
            Toast.makeText(this, "Congrats! New Record Score", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(tableNum, score);
            editor.apply();
        } else {
            Toast.makeText(this, "You can do better, Try Again.", Toast.LENGTH_SHORT).show();
        }
    }
}
