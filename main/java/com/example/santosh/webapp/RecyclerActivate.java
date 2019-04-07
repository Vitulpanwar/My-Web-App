package com.example.manoj.webapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerActivate implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
        void OnClickLongItem(View view, int position);
    }
    GestureDetector detector;

    public RecyclerActivate(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        mListener = listener;
        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(view != null && mListener != null){
                    mListener.OnClickLongItem(view,recyclerView.getChildAdapterPosition(view));
                }
            }
        });
    }
    @Override public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e){
        View view = recyclerView.findChildViewUnder(e.getX(),e.getY());
        if(view != null && mListener != null && detector.onTouchEvent(e)){
            mListener.OnItemClick(view,recyclerView.getChildAdapterPosition(view));
            return true;
        }
        return false;
    }
    @Override public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent){}

    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){}

}
