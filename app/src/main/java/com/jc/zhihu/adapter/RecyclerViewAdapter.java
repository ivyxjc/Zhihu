package com.jc.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jc.zhihu.R;
import com.jc.zhihu.TAG;
import com.jc.zhihu.model.ListModel;
import com.jc.zhihu.utils.ImageLoadUtil;

import java.util.List;

/**
 * Created by ivyxjc on on 2016/11/25.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ListModel> datas;
    private OnItemClickListener mCallback;

    public interface OnItemClickListener{
        void onClick(String url,String title,String titleImage,int slug);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mCallback=listener;
    }

    public RecyclerViewAdapter(Context context, List<ListModel> datas){
        mContext=context;
        this.datas=datas;
    }


    public void refresh(List<ListModel> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.item_list,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder)holder).update(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;
        private TextView mTextView;

        private ListModel mItem;

        public ItemViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            Log.i(TAG.RECYCLER_VIEW_ADAPTER_TAG,getLayoutPosition()+"");

        }

        @Override
        public void onClick(View view) {
            mCallback.onClick(mItem.getDetailUrl(),mItem.getTitle(),mItem.getTitleImage(),mItem.getSlug());
        }

        public void update(int position){
            mItem=datas.get(position);
            mTextView=(TextView)itemView.findViewById(R.id.list_text);
            mImageView=(ImageView)itemView.findViewById(R.id.list_image);
            mTextView.setText(mItem.getTitle());
            ImageLoadUtil.load(mContext,mImageView,mItem.getTitleImage());
        }
    }
}
