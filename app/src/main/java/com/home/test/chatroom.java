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


    static String ee;
    static int index;
    static String userName;
    static String ee2 ;
    static int index2;
    static String userName2 ;

     private com.google.firebase.database.FirebaseDatabase database;
    public com.google.firebase.database.DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        if(com.home.test.ui.MainActivity.designer.email != null){
            ee =com.home.test.ui.MainActivity
                    .designer.email;
            index= ee.indexOf('@');
            userName = ee.substring(0, index);
            ee2 =getIntent().getStringExtra("user");
            index2= ee2.indexOf('@');
            userName2 = ee2.substring(0, index2);
        }
        if(com.home.test.ui.MainActivity.user.email != null){
            ee =getIntent().getStringExtra("designer");
            index= ee.indexOf('@');
            userName = ee.substring(0, index);
            ee2 =com.home.test.ui.MainActivity.user.email;
            index2= ee2.indexOf('@');
            userName2 = ee2.substring(0, index2);
        }


        String mt = getResources().getString(R.string.title_chatroom);
        ActionBar ab = ((AppCompatActivity)this).getSupportActionBar();

        ab.hide();

          database = com.google.firebase.database.FirebaseDatabase.getInstance();
        myRef = database.getReference("Messages");

        chatText = (EditText) findViewById(R.id.messagg);
        listy = findViewById(R.id.listview);
        send = findViewById(R.id.imageView4);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_right);
        listy.setAdapter(chatArrayAdapter);

        listy.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listy.setAdapter(chatArrayAdapter);

        if(chatArrayAdapter.chatMessageList.isEmpty()) {
        myRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {

                @Override
                public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot snapshot) {

                    if(com.home.test.ui.MainActivity.designer.email != null){
                        Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.getChildren();
                        for (com.google.firebase.database.DataSnapshot child : messages) {
                            if(child.getKey().equals(userName + userName2)){
                                Iterable<com.google.firebase.database.DataSnapshot> message = snapshot.child(child.getKey()).getChildren();
                                for (com.google.firebase.database.DataSnapshot childd : message) {
                                    if(childd.child("name").getValue().toString().equals(com.home.test.ui.MainActivity.designer.email)){
                                        ChatMessage m = new ChatMessage(true, childd.child("message").getValue().toString());
                                        sendChatMessage2(m);
                                    } if(childd.child("name").getValue().toString().equals(ee2)){
                                        ChatMessage m = new ChatMessage(false, childd.child("message").getValue().toString());
                                        sendChatMessage2(m);
                                    }

                                }
                            }
                        }
                    }

                    if(com.home.test.ui.MainActivity.user.email != null){
                        Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.getChildren();
                        for (com.google.firebase.database.DataSnapshot child : messages) {
                            if(child.getKey().equals(userName + userName2)){
                                Iterable<com.google.firebase.database.DataSnapshot> message = snapshot.child(child.getKey()).getChildren();
                                for (com.google.firebase.database.DataSnapshot childd : message) {
                                    if(childd.child("name").getValue().toString().equals(com.home.test.ui.MainActivity.user.email)){
                                        ChatMessage m = new ChatMessage(true, childd.child("message").getValue().toString());
                                        sendChatMessage2(m);
                                    } if(childd.child("name").getValue().toString().equals(ee)){
                                        ChatMessage m = new ChatMessage(false, childd.child("message").getValue().toString());
                                        sendChatMessage2(m);
                                    }

                                }
                            }
                        }
                    }

                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError error) {

                }
            });
}
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
                if(com.home.test.ui.MainActivity.designer.email != null){
                    Message m = new Message();
                    m.setDateTime( java.util.Calendar.getInstance().getTime());
                    m.setMessage(chatText.getText().toString());
                    m.setName(com.home.test.ui.MainActivity.designer.email);
                    m.setTo(ee2);
                    myRef.child(userName +userName2).push().setValue(m);
                    chatText.setText("");
                }
                if(com.home.test.ui.MainActivity.user.email != null){
                    Message m = new Message();
                    m.setDateTime( java.util.Calendar.getInstance().getTime());
                    m.setMessage(chatText.getText().toString());
                    m.setName(com.home.test.ui.MainActivity.user.email);
                    m.setTo(ee);
                    myRef.child(userName +userName2).push().setValue(m);
                    chatText.setText("");
                }

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