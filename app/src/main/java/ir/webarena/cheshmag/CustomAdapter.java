package ir.webarena.cheshmag;

import android.content.Context;
import android.text.Html;
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
    public CustomAdapter(String[] newsIdArray, String[] newsDateArray, String[] newsTitleArray, Context context) {
        //this.newsArray = newsArray;
        this.newsIdArray = newsIdArray;
        this.newsDateArray = newsDateArray;
        this.newsTitleArray = newsTitleArray;
        this.context = context;
    }

    //String[] newsArray;
    String[] newsIdArray;
    String[] newsDateArray;
    String[] newsTitleArray;
    Context context;

    @Override
    public int getCount() {
        return newsIdArray.length;
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
        TextView textView = (TextView) newsRow.findViewById(R.id.text_view_news);
        TextView newsId = (TextView) newsRow.findViewById(R.id.text_view_newsid);

        String htmlFormatNews = "<font color=\"blue\">" + newsTitleArray[position] + "</font> - <font color=\"gray\" size=\"10px\">"+newsDateArray[position]+"</font>";

        newsId.setText(newsIdArray[position]);
        textView.setText(Html.fromHtml(htmlFormatNews));
        //textView.setText(newsTitleArray[position]);

        return newsRow;
    }
}
