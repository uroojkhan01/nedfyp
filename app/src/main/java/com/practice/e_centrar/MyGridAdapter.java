package com.practice.e_centrar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter{

    Context context;
    ArrayList<Product> arrayList;
    String initial = "http://192.168.1.5";
    String newgif;

    MyGridAdapter(Context context, ArrayList<Product> arrayList){

        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.samplegridview,null);
        TextView t1 = (TextView)convertView.findViewById(R.id.textView4);
        TextView t2 = (TextView)convertView.findViewById(R.id.textView5);
       final ImageView img = (ImageView)convertView.findViewById(R.id.productimageView);

        Product product = arrayList.get(position);

        t1.setText(product.getProductName());
        t2.setText(product.getUnitPrice());
        newgif = product.getProductImage();


        String gif = initial.concat(newgif);

        Picasso.with(this.context).load(gif).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)

                .into(img, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageInByte = baos.toByteArray();
                        Log.d("final", imageInByte.toString());
                        // hello(imageInByte);
                    }

                    @Override
                    public void onError() {

                    }
                });


        return convertView;
    }


}
