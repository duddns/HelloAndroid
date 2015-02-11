package dont.kr.helloandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by duddns on 2/11/15.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mItems = new ArrayList<String>();

    public ImageAdapter(Context context) {
        mContext = context;
    }


    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position < mItems.size()) {
            mItems.remove(position);

            notifyDataSetChanged();
        }
    }

    public void remove(String item) {
        if (mItems.contains(item)) {
            mItems.remove(item);

            notifyDataSetChanged();
        }
    }

    public void addItem(final String item) {
        mItems.add(item);

        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int position) {
        if (position < mItems.size()) {
            return mItems.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
         * 이미지 뷰 생성
         */
        ImageView imageView = null;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView)convertView;
        }

        /*
         * 이미지 로드
         */
        String path = getItem(position);
        if (null != path) {
            File file = new File(path);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            }
        }

        return imageView;
    }
}
