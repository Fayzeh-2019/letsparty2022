package com.home.test.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.test.DesignerProfile;
import com.home.test.R;
import com.home.test.UserProfile;
import com.home.test.databinding.FragmentDashboardBinding;
import com.home.test.databinding.FragmentHomeBinding;
import com.home.test.ui.MyAdapter;
import com.home.test.ui.dashboard.DashboardViewModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public FragmentHomeBinding binding;

    RecyclerView recycle;
    ArrayList<Integer> list;
    MyAdapter adapter;
    Spinner sp2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel HomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        String mt = getResources().getString(R.string.title_home);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();

        ab.hide();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recycle = root.findViewById(R.id.rec1);
        sp2 = root.findViewById(R.id.spinner2);
        ArrayList<String> lang = new ArrayList<>();
        lang.add(" ");
        lang.add("Profile");
        lang.add("Logout");
        ArrayAdapter<String> sp2adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, lang);
        sp2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(sp2adapter);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Profile") ){
                    Intent ii = new Intent(getContext(), UserProfile.class);
                    startActivity(ii);
                    adapterView.setSelection(0);
                }
                if(adapterView.getItemAtPosition(i).toString().equals("logout") ){
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setHasFixedSize(true);
        list= new ArrayList<>();
        list.add(8);
        list.add(8);
        list.add(8);

        adapter = new MyAdapter(getContext(), list);
        recycle.setAdapter(adapter);

        return root;
    }
}