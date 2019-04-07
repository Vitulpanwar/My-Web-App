package com.example.manoj.webapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView viewImage;
        public TextView viewText;

        public ViewHolder(View itemView){
            super(itemView);
            viewImage = (ImageView)itemView.findViewById(R.id.image_view);
            viewText = (TextView)itemView.findViewById(R.id.text_view);
        }
    }
    private List<String> Listweb;
    private List<Integer> Listimage;
    private Context mContext;
    public RecyclerViewAdapter(Context context, List<String>webList, List<Integer> imageList){
        mContext = context;
        Listweb = webList;
        Listimage = imageList;
    }
    private Context context(){
        return mContext;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View webList = inflater.inflate(R.layout.show_recyle,parent,false);
        webList.setOnClickListener(FragmentList.myOnClickListener);

        ViewHolder viewHolder = new ViewHolder(webList);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position){
        String webList = Listweb.get(position);
        Integer imageList = Listimage.get(position);

        ImageView viewImage = viewHolder.viewImage;
        viewImage.setImageResource(imageList);
        TextView viewText = viewHolder.viewText;
        viewText.setText(webList);
    }
    @Override
    public int getItemCount(){
        return Listweb.size();
    }
}
