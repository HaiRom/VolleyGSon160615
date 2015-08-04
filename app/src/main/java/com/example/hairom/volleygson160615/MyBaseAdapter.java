package com.example.hairom.volleygson160615;

/**
 * Created by hairom on 17/06/2015.
 */


        import java.util.ArrayList;





        import android.content.Context;
        import android.graphics.Typeface;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter{
    ArrayList<Contacts> arrayListThongTinFaceBook = new ArrayList<Contacts>();

    private Context context;
    private LayoutInflater inflater;
   public Typeface typeface;
    TextView textView;
    public MyBaseAdapter(Context context, ArrayList<Contacts> arrayListThongTinFaceBook) {
        this.arrayListThongTinFaceBook = arrayListThongTinFaceBook;
        this.context = context;
        inflater = LayoutInflater.from(this.context); // only context can also
        // be used

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListThongTinFaceBook.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListThongTinFaceBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, null);
            mViewHolder = new MyViewHolder();
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
          
        }
        mViewHolder.title = detail(convertView, R.id.textview1,
                arrayListThongTinFaceBook.get(position).id + "\t  " + arrayListThongTinFaceBook.get(position).name  + "\t" +arrayListThongTinFaceBook.get(position).gender );



        // return null;
        mViewHolder.description = detail(convertView, R.id.textview2, arrayListThongTinFaceBook
                .get(position).email +"\t"+ arrayListThongTinFaceBook.get(position).address +"\t" + arrayListThongTinFaceBook.get(position).getPhoneList());


        return convertView;
    }
    private class MyViewHolder {

        TextView title, description;
    }
    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);

        return tv;

    }

}