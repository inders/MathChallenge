package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by indersingh on 9/4/16.
 */
public class AnswerOptionsAdapter extends BaseAdapter {

    Random random = new Random();
    private List<Integer> optionsList = new ArrayList<>();
    private final Operator operator;
    private final Context context;
    private final int num1;
    private final int num2;
    private int correctAns;

    public AnswerOptionsAdapter(Context context, int num1,
                                int num2, Operator operator) {
        this.context = context;
        this.operator = operator;
        this.num1 = num1;
        this.num2 = num2;
        if (operator == Operator.MULTIPLY) {
            correctAns = num1 * num2;
        }
        fillOptions();
    }

    private void fillOptions(){
        int randomResultPos = Math.abs(random.nextInt() % 8);
        for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    optionsList.add(generateTwoDigitNum());
                } else {
                    optionsList.add(generateOneDigitNum());
                }
        }
        optionsList.add(randomResultPos, correctAns);
    }

    private int generateTwoDigitNum() {
       return Math.abs(random.nextInt() % 100);
    }

    private int generateOneDigitNum() {
        return Math.abs(random.nextInt() % 10);
    }

    @Override
    public int getCount() {
        return optionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return optionsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.numberview, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.numberView);
        textView.setText(optionsList.get(i).toString());
        return view;
    }

}
