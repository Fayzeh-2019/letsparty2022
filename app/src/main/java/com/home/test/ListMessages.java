package com.home.test;

import com.home.test.ui.MainActivity;
import com.home.test.ui.MyAdapter;

import java.util.ArrayList;

public class ListMessages {
    private com.google.firebase.database.FirebaseDatabase database;
    public com.google.firebase.database.DatabaseReference myRef;
     String eemail;
     int index1;
     String userName1;
     public ArrayList<String> list;

    public ListMessages(){
        this.list = new ArrayList<>();
        }

        public void fill(){
            database = com.google.firebase.database.FirebaseDatabase.getInstance();
            myRef = database.getReference("Messages");

            if(com.home.test.ui.MainActivity.designer.email != null){
                eemail =com.home.test.ui.MainActivity.designer.email;
                index1= eemail.indexOf('@');
                userName1 = eemail.substring(0, index1);

            }
            if(com.home.test.ui.MainActivity.user.email != null){
                eemail =com.home.test.ui.MainActivity.user.email;
                index1= eemail.indexOf('@');
                userName1 = eemail.substring(0, index1);

            }
            myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {

                @Override
                public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot snapshot) {
                    list.clear();
                    if(com.home.test.ui.MainActivity.designer.email != null ){
                        Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.getChildren();
                        for (com.google.firebase.database.DataSnapshot child : messages) {
                            String id = child.getKey();
                            if(id.contains(userName1)){
                                list.add(id);
                            }



                        }
                    }

                    if(com.home.test.ui.MainActivity.user.email != null ){
                        Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.getChildren();
                        for (com.google.firebase.database.DataSnapshot child : messages) {
                            String id = child.getKey();
                            if(id.contains(userName1)){
                                list.add(id);
                            }


                        }
                    }
                }





                @Override
                public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError error) {

                }
            });
        }

        public void fillImg(String title){

            MyAdapter.imgs.put(0, MainActivity.bitmapList.get(title));
            for (int i = 1; i < 4; i++) {
                if (MainActivity.bitmapList.containsKey(title + (i))) {
                    String s = title + (i);
                    MyAdapter.imgs.put(i, MainActivity.bitmapList.get(s));
                }
            }
        }
}
