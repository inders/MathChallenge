package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indersingh on 9/4/16.
 */
public class MainActivityAdapter extends BaseAdapter {

    private List<String> options = new ArrayList<>();
    private final Context context;

    public MainActivityAdapter(Context context) {
        this.context = context;
        setupOptions();
    }

    private void setupOptions() {
        options.add(new String(Constants.PRACTICE));
        options.add(new String(Constants.CHALLENGE));
        options.add(new String(Constants.MYSCORE));
        options.add(new String(Constants.PROFILE));
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int i) {
        return options.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.main_activity_view_layout, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.main_layout_text);
        textView.setText(options.get(i).toString());
        return view;
    }
}
