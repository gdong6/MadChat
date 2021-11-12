package com.example.madchat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home extends Fragment implements OnClickListener {

    SwipeAdapter mAdapter;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);


    SwipeListView mListView = (SwipeListView)view.findViewById(R.id.mListView);
    final List<WXMessage> data = new ArrayList<WXMessage>();
    WXMessage msg1 = null;
    WXMessage msg2 = null;
    msg1 = new WXMessage("CS407", " i've finished my page'", "8:44am");
        msg1.setIcon_id(R.drawable.chat1);

        msg2 = new WXMessage("Math535", " we have an exam next week", "18:35pm");
        msg2.setIcon_id(R.drawable.chat2);
        data.add(msg1);
        data.add(msg2);





    mAdapter = new SwipeAdapter(view.getContext(),data);

        mAdapter.setOnRightItemClickListener(new SwipeAdapter.onRightItemClickListener() {

        @Override
        public void onRightItemClick(View v, int position) {

            Toast.makeText(v.getContext(), "delete  " + (position+1)+"th dialog", Toast.LENGTH_SHORT).show();
            data.remove(position);
            mAdapter.notifyDataSetChanged();

        }
    });


        mAdapter.setOnLeftItemClickListener(new SwipeAdapter.onLeftItemClickListener() {


        public void onLeftItemClick(View v, int position) {

            Intent intent = new Intent();
            intent.setAction("abc");         //跳转到聊天窗口，在AndroidManifest.xml文件里需要有相应代码呼应
            startActivity(intent);


        }
    });


        mListView.setAdapter(mAdapter);


        return view;
}

    @Override
    public void onClick(View view) {

    }
}