package com.dever.thinkinlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dever.thinkinlistview.adapters.CartAdapter;
import com.dever.thinkinlistview.adapters.ChatAdapter;
import com.dever.thinkinlistview.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private List<ChatMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView listView = (ListView) findViewById(R.id.chat_list);

        messages = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            ChatMessage mess = new ChatMessage();
            mess.setMessage("123");
            mess.setTime(System.currentTimeMillis());
            mess.setAddress("hhh");
            mess.setIsSelf(i%2==0);
            messages.add(mess);
        }
        adapter = new ChatAdapter(this,messages);
        listView.setAdapter(adapter);
    }
}
