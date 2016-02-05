package ir.webarena.cheshmag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Aylin on 2/5/2016.
 */
public class CustomAdapter extends BaseAdapter {
    public CustomAdapter(String[] newsArray, Context context) {
        this.newsArray = newsArray;
        this.context = context;
    }

    String[] newsArray;
    Context context;

    @Override
    public int getCount() {
        return newsArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newsRow = LayoutInflater.from(context).inflate(R.layout.content_list, parent, false);
        TextView textView = (TextView) newsRow.findViewById(R.id.text_view_test);
        textView.setText(newsArray[position]);
        return newsRow;
    }
}
