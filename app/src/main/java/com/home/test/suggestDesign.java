package com.home.test;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.home.test.ui.MainActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class suggestDesign extends AppCompatActivity {

    TextInputEditText title, desc, price;
    String designer;
    static Design d = new Design();
    FirebaseDatabase database ;
    DatabaseReference myRef;
    SliderView uploadSlugder;
    public static Map<Integer, Bitmap> designImages = new HashMap<>();
    static  imageSligerAdapter ad2;
    Button add;
    int counter =0;
    TextView upload;
    ActivityResultLauncher<String> mGetContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_design);
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        ab.hide();


        title = findViewById(R.id.designTitlee);
        designer = MainActivity.designer.getEmail();
        desc = findViewById(R.id.designdescr);
        price = findViewById(R.id.designpricee);
        upload = findViewById(R.id.textView13);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Design");

        add= findViewById(R.id.button5);
        uploadSlugder = findViewById(R.id.imageSlider2);
        ad2 = new imageSligerAdapter(designImages);
        uploadSlugder.setIndicatorAnimation(IndicatorAnimationType.WORM);
        uploadSlugder.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        uploadSlugder.startAutoCycle();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setTitle(title.getText().toString());
                d.setDesigner(designer);
                d.setDescription(desc.getText().toString());
                d.setPrice(price.getText().toString());
                d.setApproved("no");
                d.setOffer("no");

                if(title.getText().toString() != null){
                    myRef.push().setValue(d);
                }

                for(int i =0; i< designImages.size(); i++){
                    uploadFile(designImages.get(i),i);
                }
            }
        });

       mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
           @Override
           public void onActivityResult(Uri result) {
               try{
                   Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(result));
                   if(counter < 4){
                       designImages.put(counter, bitmap);
                       counter++;
                       uploadSlugder.setSliderAdapter(ad2);
                   }if(counter > 3){
                       Toast.makeText(suggestDesign.this, "you cant add more images", Toast.LENGTH_SHORT).show();
                   }
               }catch(Exception e){}
           }
       }

       );

       upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mGetContent.launch("image/*");
           }
       });
    }


//    private Bitmap pickImage(){
//        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        StartActivityForResult(pickImageIntent, 3);
//        //Bitmap bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//    }

    private void uploadFile(Bitmap bitmap, int i) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainImagesRef= null;
        if(i ==0){
            mountainImagesRef = storageRef.child(title.getText().toString() + ".jpg");
        }else{
            mountainImagesRef = storageRef.child(title.getText().toString()+i + ".jpg");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Toast.makeText(suggestDesign.this, "done", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }