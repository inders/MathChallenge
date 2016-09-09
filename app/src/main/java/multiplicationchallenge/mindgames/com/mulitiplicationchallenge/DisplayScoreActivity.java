package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mehdi.sakout.fancybuttons.FancyButton;

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
        int problemsAttempted = intent.getExtras().getInt(Constants.PROBLEMS_ATTEMPTED);
        textView.setText("Your final score is = " + score + "/" + problemsAttempted);

        FancyButton button = (FancyButton) findViewById(R.id.playAgain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);


            }
        });
    }
}
