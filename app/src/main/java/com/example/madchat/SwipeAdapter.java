package com.example.madchat;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private Context mContext = null;
    private List<WXMessage> data;


    public SwipeAdapter(Context ctx,List<WXMessage> data) {
        mContext = ctx;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView , final ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.weixin_item, parent, false);
            //convertView.setPadding(0,-20,0,0);
            holder = new ViewHolder();

            holder.item_left = (RelativeLayout)convertView.findViewById(R.id.item_left);

            holder.item_right = (RelativeLayout)convertView.findViewById(R.id.item_right);
            holder.addfriend = (ImageButton)convertView.findViewById(R.id.addfriend);

            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
            holder.tv_msg = (TextView)convertView.findViewById(R.id.tv_msg);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);

            holder.item_right_txt = (TextView)convertView.findViewById(R.id.item_right_txt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Log.i("SwipeAdapter", "getView position="+position);

        WXMessage msg = data.get(position);

        holder.tv_title.setText(msg.getTitle());
        holder.tv_msg.setText(msg.getMsg());
        holder.tv_time.setText(msg.getTime());

        holder.iv_icon.setImageResource(msg.getIcon_id());

        holder.item_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightItemClick(v, position);
                }
            }
        });
        holder.item_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlListener != null) {

                    v = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, parent, false);
                    mlListener.onLeftItemClick(v, position);
                }
            }


        });



        return convertView;
    }

    static class ViewHolder {
        public ImageButton addfriend;
        RelativeLayout item_left;
        RelativeLayout item_right;

        TextView tv_title;
        TextView tv_msg;
        TextView tv_time;
        ImageView iv_icon;

        TextView item_right_txt;
    }


    private onRightItemClickListener mListener = null;

    public void setOnRightItemClickListener(onRightItemClickListener listener){
        mListener = listener;
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }


    private onLeftItemClickListener mlListener = null;

    public void setOnLeftItemClickListener(onLeftItemClickListener listener){
        mlListener = listener;
    }

    public interface onLeftItemClickListener {
        void onLeftItemClick(View v, int position);
    }


    private onTopItemClickListener mLListener = null;

    public void setOnTopItemClickListener(onTopItemClickListener listener){
        mLListener = listener;
    }

    public interface onTopItemClickListener {
        void onTopItemClick(View v, int position);
    }



}