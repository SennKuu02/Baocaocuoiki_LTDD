package com.example.baocaocuoiky_vothanhhai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocaocuoiky_vothanhhai.model.DetailFood;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddMenuFood extends AppCompatActivity {

    TextView addAnh,tenCreate, thongTinCreate, giaTienCreate, danhgiaCreate;
    ImageView AddImg;
    Button btnAdd;
    DatabaseReference databaseReference  ;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_food);
        Anhxa();

        databaseReference = FirebaseDatabase.getInstance().getReference("monan");

        addAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten = tenCreate.getText().toString();
                final String thongTin = thongTinCreate.getText().toString();
                final String gia = giaTienCreate.getText().toString();
                final String danhgia = danhgiaCreate.getText().toString();
                UUID uuid = UUID.randomUUID();
                DetailFood food = new DetailFood(uuid.toString(), imgUri.toString(), ten, thongTin, gia, danhgia);
                uploadImg(food, imgUri);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2  && resultCode == RESULT_OK && data != null){
            imgUri = data.getData();
            AddImg.setImageURI(imgUri);
        }
    }

    private void Anhxa() {
        tenCreate = findViewById(R.id.edt_namefood);
        thongTinCreate = findViewById(R.id.edt_description);
        giaTienCreate = findViewById(R.id.edt_price);
        danhgiaCreate = findViewById(R.id.edt_rating);
        addAnh = findViewById(R.id.addAnh);
        AddImg = findViewById(R.id.imageViewAdd);
        btnAdd = findViewById(R.id.btnAdd);
    }

    public void uploadImg(DetailFood food,Uri uri){
        StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtensionUri(uri) );
        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        food.imageURL = uri.toString();

                        databaseReference.child(food.idMon).setValue(food);
                        Toast.makeText(AddMenuFood.this, "upload successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMenuFood.this, "uploading file failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtensionUri(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
}