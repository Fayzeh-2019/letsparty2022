package com.home.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.home.test.databinding.FragmentDesignerHomeBinding;
import com.home.test.ui.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link designerHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class designerHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button design, offer;
    Spinner sp3;
    FragmentDesignerHomeBinding binding;
    TextView desisnernametiltle;
    public designerHome() {
        // Required empty public constructor
    }
    
    public static designerHome newInstance(String param1, String param2) {
        designerHome fragment = new designerHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String mt = getResources().getString(R.string.title_home);
        ActionBar ab =  ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.hide();
        binding = FragmentDesignerHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        design = root.findViewById(R.id.so);
        offer =root.findViewById(R.id.sd);
        sp3 = root.findViewById(R.id.spinner3);
        desisnernametiltle =  root.findViewById(R.id.designernametitle);
        ArrayList<String> lang = new ArrayList<>();
        lang.add(" ");
        lang.add("Profile");
        lang.add("Logout");
        ArrayAdapter<String> sp3adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, lang);
        sp3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(sp3adapter);
        desisnernametiltle.setText("Designer " + MainActivity.designer.getName());
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Profile") ){
                    Intent ii = new Intent(getContext(), DesignerProfile.class);
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
        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), suggestDesign.class);
                startActivity(i);
            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), suggestOffer.class);
                startActivity(i);
            }
        });
        // Inflate the layout for this fragment
        return root;
    }
}