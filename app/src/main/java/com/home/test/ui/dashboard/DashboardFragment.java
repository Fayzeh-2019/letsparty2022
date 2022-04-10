package com.home.test.ui.dashboard;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.test.R;
import com.home.test.databinding.FragmentDashboardBinding;
import com.home.test.ui.MyAdapter;
import com.home.test.ui.MyOrdersAdapter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    RecyclerView recycle;
    MyOrdersAdapter adapter;
    com.google.firebase.database.DatabaseReference myRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        String mt = getResources().getString(R.string.title_dashboard);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();

        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recycle = root.findViewById(R.id.rec2);
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setHasFixedSize(true);
         myRef = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Applicant");
        com.firebase.ui.database.FirebaseRecyclerOptions<com.home.test.Applicant> list = new com.firebase.ui.database.FirebaseRecyclerOptions.Builder<com.home.test.Applicant>()
                .setQuery(myRef, com.home.test.Applicant.class)
                .build();

        adapter = new MyOrdersAdapter( list);
        recycle.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}