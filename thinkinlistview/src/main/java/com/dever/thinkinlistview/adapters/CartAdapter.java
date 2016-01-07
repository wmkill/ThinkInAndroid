package com.dever.thinkinlistview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dever.thinkinlistview.R;
import com.dever.thinkinlistview.model.CartItem;

import java.util.List;

import javax.crypto.BadPaddingException;

/**
 * Created by admin on 2015/12/26.
 */
public class CartAdapter extends BaseAdapter {

    private List<CartItem> list;
    private Context context;

    private View.OnClickListener onClickListener;//Item内部按钮点击事件的监听器

    private CompoundButton.OnCheckedChangeListener checkedChangeListener;

    private boolean editMode;//是否编辑模式

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setCheckedChangeListener(CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
    }

    public CartAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        if(convertView!=null){
            ret = convertView;
        }else{
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.cart_item,parent,false);
        }

        ViewHolder holder = (ViewHolder) ret.getTag();

        if(holder==null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }

        CartItem cartItem = list.get(position);
        holder.txtPructName.setText(cartItem.getProductName());
        holder.txtPructPrice.setText(Float.toString(cartItem.getProductPrice()));
        holder.txtCount.setText(Integer.toString(cartItem.getCount()));
        //针对按钮， Checkbox等可以交互的控件，必须要更新控件位置的表示
        holder.btnInc.setTag(position);
        holder.btnDel.setTag(position);
        holder.btnDec.setTag(position);
        holder.chbcheck.setTag(position);//chenckbox设置tag，一定要在checkBox进行代码选中之前

        //编辑模式的处理
        if (editMode){
            holder.chbcheck.setVisibility(View.VISIBLE);
            holder.btnDel.setVisibility(View.VISIBLE);
            holder.chbcheck.setChecked(cartItem.isChecked());
        }else{
            holder.chbcheck.setVisibility(View.INVISIBLE);
            holder.btnDel.setVisibility(View.INVISIBLE);
            holder.chbcheck.setChecked(false);
        }

        return ret;
    }
    //方法内部调用Adapter刷新，这个方法必须在主线程调用
    public void switchEditMode(){
        editMode = !editMode;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        public CheckBox chbcheck;
        public ImageView imageView;
        public TextView txtPructName,txtPructPrice,txtCount;
        public Button btnInc,btnDec,btnDel;

        public  ViewHolder(View itemView){
            chbcheck = (CheckBox) itemView.findViewById(R.id.item_cart_check);
            imageView = (ImageView) itemView.findViewById(R.id.item_cart_icon);
            txtPructName = (TextView) itemView.findViewById(R.id.item_cart_name);
            txtPructPrice = (TextView) itemView.findViewById(R.id.item_cart_price);
            txtCount = (TextView) itemView.findViewById(R.id.item_cart_count);
            btnInc = (Button) itemView.findViewById(R.id.item_cart_inc);
            btnDec = (Button) itemView.findViewById(R.id.item_cart_dec);
            btnDel = (Button) itemView.findViewById(R.id.item_cart_del);

            btnInc.setOnClickListener(onClickListener);
            btnDec.setOnClickListener(onClickListener);
            btnDel.setOnClickListener(onClickListener);
            chbcheck.setOnCheckedChangeListener(checkedChangeListener);
        }
    }
}
