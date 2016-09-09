package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.Random;

/**
 * Created by indersingh on 9/4/16.
 */
public class GamePlayActivity extends Activity {

    private Random random = new Random();
    private String tableNum;
    private Context context;
    private Integer gameCount;
    private Integer score;
    private int progress;
    private int problemsAttempted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play_layout);
        Intent intent = getIntent();
        tableNum = intent.getExtras().getString(Constants.TABLE_NUM);
        gameCount = intent.getExtras().getInt(Constants.GAMECOUNT);
        score = intent.getExtras().getInt(Constants.SCORE);
        this.context = this;
        final String mode = intent.getExtras().getString(Constants.MODE);
        progress = intent.getExtras().getInt(Constants.PROGRESS);
        problemsAttempted = intent.getExtras().getInt(Constants.PROBLEMS_ATTEMPTED);

        TextView progressBar = (TextView) findViewById(R.id.progressBar);

            if (mode.equalsIgnoreCase(Constants.CHALLENGE)) {
                MyCountDownTimer myCountDownTimer = null;
                if (progress == 0) {
                     myCountDownTimer = new MyCountDownTimer(60000, 1000, progressBar);
                } else {
                    int progressInMsec = progress * 1000;
                    myCountDownTimer = new MyCountDownTimer(progressInMsec, 1000, progressBar);
                }
                myCountDownTimer.start();
                runGame(myCountDownTimer, mode);
            } else if (mode.equalsIgnoreCase(Constants.PRACTICE)){
                progressBar.setVisibility(View.GONE);
                runGame(null, mode);
            }
    }

    private void runGame(final MyCountDownTimer timer, final String mode) {
        int i=0;
        final Integer num1 = new Integer(tableNum);
        final Integer num2 = generateOneDigitNum();
        TextView problemView = (TextView) findViewById(R.id.problemView);

        problemView.setText(num1 + " * " + num2.toString() + " = ");
        GridView optionsView = (GridView) findViewById(R.id.answersView);
        final Operator operator = Operator.MULTIPLY;

        optionsView.setAdapter(new AnswerOptionsAdapter(this, new Integer(num1), num2, operator));
        optionsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer optionSelected = (Integer) adapterView.getAdapter().getItem(i);
                int expectedAns = 0;
                if (operator == Operator.MULTIPLY) {
                    expectedAns = num1 * num2;
                }
                if (optionSelected.intValue() == expectedAns) {
                    Toast.makeText(GamePlayActivity.this, "You got it right",
                            Toast.LENGTH_SHORT).show();
                    score += 1;
                } else {
                    Toast.makeText(GamePlayActivity.this, "You got it wrong",
                            Toast.LENGTH_SHORT).show();
                }
                problemsAttempted += 1;
                if (timer != null) {
                    timer.cancel();
                }
                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra(Constants.GAMECOUNT, gameCount+1);
                intent.putExtra(Constants.SCORE, score);
                intent.putExtra(Constants.TABLE_NUM, tableNum);
                intent.putExtra(Constants.MODE, mode);
                intent.putExtra(Constants.PROGRESS, progress);
                intent.putExtra(Constants.PROBLEMS_ATTEMPTED, problemsAttempted);
                startActivity(intent);
            }
        });
    }

    private int generateOneDigitNum() {
        return Math.abs(random.nextInt() % 10);
    }

    private class MyCountDownTimer extends CountDownTimer {
        private TextView progressBar;

        public MyCountDownTimer(long millisInFuture, long countDownInterval,
                                TextView progressBar) {
            super(millisInFuture, countDownInterval);
            this.progressBar = progressBar;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progress = (int) (millisUntilFinished/1000);
            progressBar.setText(new Integer(progress).toString());
        }

        @Override
        public void onFinish() {
            if (progress == 1) {
                progressBar.setText("0");
                this.cancel();
                Intent intent1 = new Intent(context, DisplayScoreActivity.class);
                intent1.putExtra(Constants.SCORE, score);
                intent1.putExtra(Constants.PROBLEMS_ATTEMPTED, problemsAttempted);
                startActivity(intent1);
            }
        }

    }
}
