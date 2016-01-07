package com.dever.thinkinlistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dever.thinkinlistview.R;
import com.dever.thinkinlistview.model.ChatMessage;

import java.util.List;

/**
 * Created by admin on 2015/12/26.
 */
public class ChatAdapter extends BaseAdapter {

    public static final int TYPE_OTHER = 0;
    public static final int TYPE_SELF = 1;

    private Context context;
    private List<ChatMessage> list;

    public ChatAdapter(Context context, List<ChatMessage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 获取当前listView要显示多少种布局类型
     * 如果这个方法返回内容>1 必须要重写getItemViewType(int position)中的方法
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;//返回2是因为聊天界面自己发送的在右侧，收到的在左侧，相当于显示两种不同的布局类型
    }

    /**
     * 因为geyViewTypeCount返回大于1，每进行一次getView之前都会对要获取的view的类型进行检查，根据索引来调用
     * 这个方法能够控制
     * 1.listview将移出的view是如果进行缓存的，决定了缓冲区存放的位置
     * 2.listview在每次调用getView之前，都会进行getItemViewType，获取的类型，就可以从缓冲区加载view形成convertView
     * @param position
     * @return  最后的返回值（0，n）n代表都会进行getItemViewType的返回值
     */
    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        ChatMessage mess = list.get(position);
        if(mess.isSelf()){
            ret = TYPE_SELF;
        }else{
            ret = TYPE_OTHER;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View ret = null;
        if(convertView!=null){
            ret=convertView;
        }else{
            LayoutInflater inflater = LayoutInflater.from(context);
            int itemViewType = getItemViewType(position);
            switch (itemViewType){
                case TYPE_OTHER:
                    ret = inflater.inflate(R.layout.chat_other_item,parent,false);
                    break;
                case TYPE_SELF:
                    ret = inflater.inflate(R.layout.chat_self_item,parent,false);
                    break;
            }
        }
        return ret;
    }
}
