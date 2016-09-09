package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indersingh on 9/9/16.
 */
public class AllScoresActivity extends Activity {

    private List<String> tableNumList = new ArrayList<>();

    private void setupTableNames() {
        tableNumList.add(Constants.TABLE_SCORE_2);
        tableNumList.add(Constants.TABLE_SCORE_3);
        tableNumList.add(Constants.TABLE_SCORE_4);
        tableNumList.add(Constants.TABLE_SCORE_5);
        tableNumList.add(Constants.TABLE_SCORE_6);
        tableNumList.add(Constants.TABLE_SCORE_7);
        tableNumList.add(Constants.TABLE_SCORE_8);
        tableNumList.add(Constants.TABLE_SCORE_9);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_scores_layout);
        setupTableNames();

        SharedPreferences pref = getApplicationContext().
                getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        String profileName = pref.getString(Constants.PROFILE_NAME, null);

        TextView userName = (TextView) findViewById(R.id.user_name);
        if (profileName != null) {
            userName.setText(profileName + "'s Best Scores");
        } else {
            userName.setText("Best Scores");
        }
        ListView allScoresView = (ListView) findViewById(R.id.all_scores_listview);
        allScoresView.setAdapter(new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return tableNumList.size();
            }

            @Override
            public Object getItem(int i) {
                return tableNumList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                //i starts from 0 and our tables start from 2
                if (view == null) {
                    view =  getLayoutInflater().
                           inflate(R.layout.score_row_layout, viewGroup, false);
                }
                TextView scoreRow = (TextView) view.findViewById(R.id.score_row);

                SharedPreferences pref = getApplicationContext().
                        getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
                int tableNum = i+2;
                int score = pref.getInt(new Integer(tableNum).toString(), 0);
                if (score == 0) {
                    scoreRow.setText("Table of " + tableNum + ", No best score yet...") ;
                } else {
                    scoreRow.setText("Table of " + tableNum + ", Best Score = "+ score) ;
                };
                return view;
            }

            @Override
            public int getItemViewType(int i) {
                return i;
            }

            @Override
            public int getViewTypeCount() {
                return tableNumList.size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });

    }
}
