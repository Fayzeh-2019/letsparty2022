package com.home.test.ui.notifications;

import android.database.DataSetObserver;
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

import com.home.test.ChatMessage;
import com.home.test.R;
import com.home.test.databinding.FragmentNotificationsBinding;
import com.home.test.ui.ChatAdapter;
import com.home.test.ui.ChatAdapter;
import com.home.test.ui.MainActivity;
import com.home.test.ui.MyOrdersAdapter;

import java.util.ArrayList;
import java.util.Date;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    RecyclerView recycle;
    ChatAdapter adapter;
    static View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);



        String mt = getResources().getString(R.string.title_notifications);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));


        MainActivity.listMessages.fill();
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
         root = binding.getRoot();
        adapter = new ChatAdapter(getContext(),MainActivity.listMessages.list);
        recycle = root.findViewById(R.id.rec3);
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setHasFixedSize(true);
        recycle.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}