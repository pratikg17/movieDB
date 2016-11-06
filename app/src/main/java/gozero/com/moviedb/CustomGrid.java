package gozero.com.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pratik G on 17/08/2016.
 */
public class CustomGrid extends ArrayAdapter<POJO> {


    ArrayList<POJO>  pojo;
    Context context;
    int resource;

    public CustomGrid(Context context, int resource, ArrayList<POJO> pojo) {
        super(context, resource, pojo);
        this.pojo=pojo;
        this.context=context;
        this.resource=resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            convertView =layoutInflater.inflate(R.layout.single_grid,null,true);

        }

        POJO pojo = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view_grid);

        Picasso.with(context).load(pojo.getImage()).into(imageView);


        return convertView;
    }
}