package com.neudesic.pmugfebappstart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.neudesic.pmugfebappstart.viewmodel.PersonViewModel;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        PersonViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()) {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PersonViewModel personViewModel = new PersonViewModel(getApplication());
                return (T)personViewModel;
            }
        }).get(PersonViewModel.class);

        EditText name = findViewById(R.id.name);
        EditText age = findViewById(R.id.age);
        EditText birthday = findViewById(R.id.birthday);

        Button addPerson = findViewById(R.id.action_add);
        addPerson.setOnClickListener(v -> {
            viewModel.addPerson(name.getText().toString(), Integer.parseInt(age.getText().toString()), birthday.getText().toString());
            Toast.makeText(getApplicationContext(), "Person added!", Toast.LENGTH_LONG).show();
        });
    }
}