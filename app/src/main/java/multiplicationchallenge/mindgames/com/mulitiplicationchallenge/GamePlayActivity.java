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

        RoundCornerProgressBar progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);

        if (gameCount < Constants.TOTAL_GAME_PLAY_COUNT) {
            if (mode.equalsIgnoreCase(Constants.CHALLENGE)) {
                MyCountDownTimer myCountDownTimer = new MyCountDownTimer(10000, 500, progressBar, mode);
                myCountDownTimer.start();
                runGame(myCountDownTimer, mode);
            } else if (mode.equalsIgnoreCase(Constants.PRACTICE)){
                progressBar.setVisibility(View.GONE);
                runGame(null, mode);
            }
        } else {
            Intent intent1 = new Intent(context, DisplayScoreActivity.class);
            intent1.putExtra(Constants.SCORE, score);
            startActivity(intent1);
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
                if (timer != null) {
                    timer.cancel();
                }
                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra(Constants.GAMECOUNT, gameCount+1);
                intent.putExtra(Constants.SCORE, score);
                intent.putExtra(Constants.TABLE_NUM, tableNum);
                intent.putExtra(Constants.MODE, mode);
                startActivity(intent);
            }
        });
    }

    private int generateOneDigitNum() {
        return Math.abs(random.nextInt() % 10);
    }

    private class MyCountDownTimer extends CountDownTimer {
        private RoundCornerProgressBar progressBar;
        private final String mode;

        public MyCountDownTimer(long millisInFuture, long countDownInterval,
                                RoundCornerProgressBar progressBar, final String mode) {
            super(millisInFuture, countDownInterval);
            this.progressBar = progressBar;
            this.mode = mode;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/100);
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            progressBar.setProgress(0);
            this.cancel();
            Intent intent = new Intent(context, GamePlayActivity.class);
            intent.putExtra(Constants.GAMECOUNT, gameCount + 1);
            intent.putExtra(Constants.SCORE, score);
            intent.putExtra(Constants.TABLE_NUM, tableNum);
            intent.putExtra(Constants.MODE, mode);
            startActivity(intent);
        }

    }
}
