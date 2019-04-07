package com.example.manoj.webapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmentList extends android.app.Fragment{
    OnURLSelectedListener mListener;
    static View.OnClickListener myOnClickListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.list_view,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        displayRecycleView();
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            try{
                mListener = (OnURLSelectedListener) context;
            } catch (ClassCastException e){
                throw new ClassCastException(context.toString() + "must implement OnURLSelectedListener");
            }
        }
    }
    public interface OnURLSelectedListener{
        void onURLSelectedListener(String URL);
    }
    public void displayRecycleView(){
        final List<String> webList = new ArrayList<>();
        webList.add("University of Central Missouri");
        webList.add("Crunchyroll");
        webList.add(" Facebook");
        webList.add("Amazon");
        webList.add("Google");
        webList.add(" Google Mail");
        webList.add("Youtube");
        webList.add(" Bing");

        final List<String> urlList = new ArrayList<>();
        urlList.add("http://www.ucmo.edu");
        urlList.add("http://www.crunchyroll.com");
        urlList.add("http://www.facebook.com");
        urlList.add("http://www.amazon.com");
        urlList.add("http://www.google.com");
        urlList.add("http://www.mail.google");
        urlList.add("http://www.youtube.com");
        urlList.add("http://www.bing.com");

        final List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.ucmo);
        imageList.add(R.drawable.crunchyroll);
        imageList.add(R.drawable.facebook);
        imageList.add(R.drawable.amazon);
        imageList.add(R.drawable.google);
        imageList.add(R.drawable.gmail);
        imageList.add(R.drawable.youtube);
        imageList.add(R.drawable.bing);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),webList,imageList);
        RecyclerView recyclerView =(RecyclerView)getView().findViewById(R.id.listofwebsites);
        recyclerView.addOnItemTouchListener(new RecyclerActivate(getActivity(),recyclerView,new RecyclerActivate.OnItemClickListener(){

            @Override
            public void OnItemClick(View view, int position) {
                mListener.onURLSelectedListener(urlList.get(position));
            }

            @Override
            public void OnClickLongItem(View view, int position) {
                mListener.onURLSelectedListener(urlList.get(position));
            }
        }));
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
    }
}