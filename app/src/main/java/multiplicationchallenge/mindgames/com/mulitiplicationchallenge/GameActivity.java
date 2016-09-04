package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by indersingh on 9/4/16.
 */
public class GameActivity extends Activity {

    private Random random = new Random();
    private String tableNum;
    private Context context;
    private Integer gameCount;
    private Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Intent intent = getIntent();
        tableNum = intent.getExtras().getString(Constants.TABLE_NUM);
        gameCount = intent.getExtras().getInt(Constants.GAMECOUNT);
        score = intent.getExtras().getInt(Constants.SCORE);
        this.context = this;

        if (gameCount < Constants.TOTAL_GAME_PLAY_COUNT) {
            runGame();
        } else {
            Intent intent1 = new Intent(context, DisplayScoreActivity.class);
            intent1.putExtra(Constants.SCORE, score);
            startActivity(intent1);
        }
    }

    private void runGame() {
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
                    Toast.makeText(GameActivity.this, "You got it right",
                            Toast.LENGTH_SHORT).show();
                    score += 1;
                } else {
                    Toast.makeText(GameActivity.this, "You got it wrong",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra(Constants.GAMECOUNT, gameCount+1);
                intent.putExtra(Constants.SCORE, score);
                intent.putExtra(Constants.TABLE_NUM, tableNum);
                startActivity(intent);
            }
        });
    }

    private int generateOneDigitNum() {
        return Math.abs(random.nextInt() % 10);
    }
}
