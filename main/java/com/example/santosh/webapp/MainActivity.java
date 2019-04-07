package com.example.manoj.webapp;

import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements FragmentList.OnURLSelectedListener{
    boolean webPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentList listFragment = new FragmentList();
            ft.add(R.id.fragment_list_container,listFragment);
            ft.commit();
        }
        if(findViewById(R.id.fragment_web_container)!= null){
            webPage = true;
            getFragmentManager().popBackStack();

            FragmentWebView webFragment = (FragmentWebView)getFragmentManager().findFragmentById(R.id.fragment_web_container);

            if(webFragment == null){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                webFragment = new FragmentWebView();
                ft.replace(R.id.fragment_web_container,webFragment);
                ft.commit();
            }
        }
    }

@Override
    public void onURLSelectedListener(String URL){
        if(webPage){
            FragmentWebView webFragment = (FragmentWebView)getFragmentManager().findFragmentById(R.id.fragment_web_container);

            webFragment.updateURLContent(URL);
        } else {
            FragmentWebView webFragment = new FragmentWebView();
            webFragment.setURLContent(URL);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_list_container,webFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
