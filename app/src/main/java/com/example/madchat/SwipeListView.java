package com.example.madchat;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;



public class SwipeListView extends ListView {

    private static final String TAG = SwipeListView.class.getSimpleName();

    private boolean isShown;

    private View mPreItemView;

    private View mCurrentItemView;

    private float mFirstX;

    private float mFirstY;

    private boolean mIsHorizontal;

    public SwipeListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float lastX = ev.getX();
        float lastY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mIsHorizontal = false;

                mFirstX = lastX;
                mFirstY = lastY;
                int motionPosition = pointToPosition((int) mFirstX, (int) mFirstY);

                Log.e(TAG, "onInterceptTouchEvent----->ACTION_DOWN position=" + motionPosition);

                if (motionPosition >= 0) {
                    View currentItemView = getChildAt(motionPosition - getFirstVisiblePosition());
                    mPreItemView = mCurrentItemView;
                    mCurrentItemView = currentItemView;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = lastX - mFirstX;
                float dy = lastY - mFirstY;

                if (Math.abs(dx) >= 5 && Math.abs(dy) >= 5) {
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                Log.i(TAG, "onInterceptTouchEvent----->ACTION_UP");
                if (isShown && mPreItemView != mCurrentItemView) {
                    Log.i(TAG, "1---> hiddenRight");
                    hiddenRight(mPreItemView);
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        float lastX = ev.getX();
        float lastY = ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "---->ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = lastX - mFirstX;
                float dy = lastY - mFirstY;

                mIsHorizontal = isHorizontalDirectionScroll(dx, dy);

                if (!mIsHorizontal) {
                    break;
                }

                Log.i(TAG, "onTouchEvent ACTION_MOVE");

                if (mIsHorizontal) {
                    if (isShown && mPreItemView != mCurrentItemView) {
                        Log.i(TAG, "2---> hiddenRight");

                        hiddenRight(mPreItemView);
                    }
                }else {
                    if (isShown) {
                        Log.i(TAG, "3---> hiddenRight");

                        hiddenRight(mPreItemView);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "============ACTION_UP");
                if (isShown) {
                    Log.i(TAG, "4---> hiddenRight");

                    hiddenRight(mPreItemView);
                }

                if (mIsHorizontal) {
                    if (mFirstX - lastX > 30) {
                        showRight(mCurrentItemView);
                    } else {
                        Log.i(TAG, "5---> hiddenRight");

                        hiddenRight(mCurrentItemView);
                    }
                    return true;
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void showRight(View rightView) {
        RelativeLayout rl_right=(RelativeLayout)rightView.findViewById(R.id.item_right);
        rl_right.setVisibility(View.VISIBLE);

        isShown = true;
    }

    private void hiddenRight(View rightView) {

        RelativeLayout rl_right=(RelativeLayout)rightView.findViewById(R.id.item_right);
        rl_right.setVisibility(View.GONE);

        isShown = false;
    }


    private boolean isHorizontalDirectionScroll(float dx, float dy) {
        boolean mIsHorizontal = true;

        if (Math.abs(dx) > 30 && Math.abs(dx) > 2 * Math.abs(dy)) {
            mIsHorizontal = true;
            System.out.println("mIsHorizontal---->" + mIsHorizontal);
        } else if (Math.abs(dy) > 30 && Math.abs(dy) > 2 * Math.abs(dx)) {
            mIsHorizontal = false;
            System.out.println("mIsHorizontal---->" + mIsHorizontal);
        }

        return mIsHorizontal;
    }

}
