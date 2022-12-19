package com.example.baocaocuoiky_vothanhhai.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baocaocuoiky_vothanhhai.AddMenuFood;
import com.example.baocaocuoiky_vothanhhai.DetailActivity;
import com.example.baocaocuoiky_vothanhhai.FoodAdapter;
import com.example.baocaocuoiky_vothanhhai.R;
import com.example.baocaocuoiky_vothanhhai.model.DetailFood;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView lsvfood;
    ArrayList<DetailFood> arr;

    FoodAdapter adapter;
    DatabaseReference databaseReference;
    FloatingActionButton createTxt;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        createTxt = v.findViewById(R.id.createFood);
        createTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddMenuFood.class));
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("monan");
        lsvfood = v.findViewById(R.id.list_food);

        arr = new ArrayList<>();

        adapter = new FoodAdapter(getContext(), R.layout.menu_food_row_item, arr);
        lsvfood.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                DetailFood food = snapshot.getValue(DetailFood.class);
                arr.add(food);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lsvfood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xoa(i);
                return false;
            }
        });

        lsvfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent =  new Intent(getActivity().getBaseContext(), DetailActivity.class);
                intent.putExtra("food",arr.get(i));
                startActivity(intent);
            }
        });



        return v;
    }
    public void xoa(int pos){

        AlertDialog.Builder alert  =new AlertDialog.Builder(getContext());
        alert.setTitle("Thông Báo!! ");
        alert.setMessage("Bạn có muốn xóa không ?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseReference.child("monan").child(arr.get(pos).idMon).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(),"Remove succeed",Toast.LENGTH_SHORT)
                                        .show();
                                arr.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(getContext(),"Remove fail",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
        alert.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();


    }



}