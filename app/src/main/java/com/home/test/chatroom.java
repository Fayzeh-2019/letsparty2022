package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class chatroom extends AppCompatActivity {

    private ChatArrayAdapter chatArrayAdapter;
    EditText chatText;
    ImageView send;
    ListView listy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        String mt = getResources().getString(R.string.title_chatroom);
        ActionBar ab = ((AppCompatActivity)this).getSupportActionBar();

        ab.hide();

        chatText = (EditText) findViewById(R.id.messagg);
        listy = findViewById(R.id.listview);
        send = findViewById(R.id.imageView4);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_right);
        listy.setAdapter(chatArrayAdapter);

        listy.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listy.setAdapter(chatArrayAdapter);


        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listy.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChatMessage(true);
                sendChatMessage2( new ChatMessage(false,"Hello hello"));
            }
        });
    }


    private boolean sendChatMessage(boolean h) {
        chatArrayAdapter.add(new ChatMessage(h, chatText.getText().toString()));
       // Message m = new Message(MainActivity.j.getName(),chatText.getText().toString());
//        for(Subject ss: Subjects.a.subjectArray){
//            if(ss.name.equals(sn)){
//                totr.child(ss.tr.id).child("messages").push().setValue(m);
//            }
//        }

        return true;
    }

    boolean sendChatMessage2(ChatMessage h) {
        chatArrayAdapter.add(h);
        return true;
    }
}