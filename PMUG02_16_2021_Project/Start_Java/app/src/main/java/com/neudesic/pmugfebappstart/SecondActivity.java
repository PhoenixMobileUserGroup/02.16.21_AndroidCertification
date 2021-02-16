package com.neudesic.pmugfebappstart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.neudesic.pmugfebappstart.model.Person;
import com.neudesic.pmugfebappstart.viewmodel.PersonViewModel;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private FloatingActionButton floatingActionButton;

    private PersonViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycler);
        floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPersonActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()) {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PersonViewModel personViewModel = new PersonViewModel(getApplication());
                return (T)personViewModel;
            }
        }).get(PersonViewModel.class);

        viewModel.getData().observe(this, this::onDataReceived);
    }

    private void onDataReceived(List<Person> people) {
        if (adapter == null) {
            adapter = new PersonAdapter(people);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.update(people);
            adapter.notifyDataSetChanged();
        }
    }
}

