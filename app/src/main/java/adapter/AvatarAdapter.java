package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.iter.tuongnguyen.cta.R;

import java.util.ArrayList;

public class AvatarAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList arrayList;

    public AvatarAdapter(Context context, int layout, ArrayList arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout,null);
        ImageView imgAnh = convertView.findViewById(R.id.imgAvatarItem);
         imgAnh.setImageResource((Integer) arrayList.get(position));
        return convertView;
    }
}
