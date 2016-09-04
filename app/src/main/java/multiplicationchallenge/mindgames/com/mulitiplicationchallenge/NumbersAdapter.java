package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.content.Context;
import android.text.Layout;
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
public class NumbersAdapter extends BaseAdapter {

    private List<Integer> numbersList = new ArrayList<>();
    private final Context context;

    public NumbersAdapter(Context context) {
        this.context = context;
        fillNumList();
    }

    private void fillNumList() {
        numbersList.add(new Integer(2));
        numbersList.add(new Integer(3));
        numbersList.add(new Integer(4));
        numbersList.add(new Integer(5));
        numbersList.add(new Integer(6));
        numbersList.add(new Integer(7));
        numbersList.add(new Integer(8));
        numbersList.add(new Integer(9));
        numbersList.add(new Integer(10));
    }

    @Override
    public int getCount() {
        return numbersList.size();
    }

    @Override
    public Object getItem(int i) {
        return numbersList.get(i);
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
        textView.setText(numbersList.get(i).toString());
        return view;
    }
}
