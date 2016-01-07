package com.dever.thinkinlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.dever.thinkinlistview.adapters.CartAdapter;
import com.dever.thinkinlistview.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private CartAdapter adapter;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.cart_list);
        cartItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CartItem item = new CartItem();
            item.setProductId(i);
            item.setProductName("Android" + i);
            item.setProductPrice(100.0f);
            item.setCount(1);

            cartItems.add(item);
        }
        adapter = new CartAdapter(cartItems,this);

        //设置按钮点击的回调接口
        adapter.setOnClickListener(this);
        adapter.setCheckedChangeListener(this);
        listView.setAdapter(adapter);
    }

    public void onClick(View v){
        Object tag = v.getTag();
        Log.d("ViewHolder", ""+tag);
        int position =-1;
        if(tag!=null&&tag instanceof Integer){
            position = (int) tag;
        }
        switch (v.getId()){
            case R.id.item_cart_inc:
                if (position>-1){
                    //TODO跟新数据
                    CartItem cartItem = cartItems.get(position);
                    int count = cartItem.getCount();
                    count++;
                    cartItem.setCount(count);
                }

                break;
            case R.id.item_cart_dec:
                if (position>-1){
                    //TODO跟新数据
                    CartItem cartItem = cartItems.get(position);
                    int count = cartItem.getCount();
                    count--;
                    if (count==0){
                        cartItems.remove(cartItem);
                    }else {
                        cartItem.setCount(count);
                    }

                }

                break;
            case R.id.item_cart_del:
                cartItems.remove(position);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    public void btnSwitchEditMode(View view) {
        adapter.switchEditMode();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.item_cart_check:
                Object tag = buttonView.getTag();
                Log.d("tag", "onCheckedChanged: "+tag);
                if(tag!=null&&tag instanceof Integer){
                    int position = (int) tag;
                    cartItems.get(position).setIsChecked(isChecked);
                }
        }
    }
}
