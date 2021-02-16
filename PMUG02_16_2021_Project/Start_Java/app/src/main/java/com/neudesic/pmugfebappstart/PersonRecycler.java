package com.neudesic.pmugfebappstart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neudesic.pmugfebappstart.model.Person;

import java.util.List;

public class PersonRecycler extends RecyclerView.Adapter<PersonRecycler.PersonViewHolder> {

    private List<Person> people;

    public PersonRecycler(List<Person> people) {
        this.people = people;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_person, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = people.get(position);

        holder.name.setText(person.getName());
        holder.age.setText(person.getAge());
        holder.birthDate.setText(person.getBirthday().toString());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        TextView birthDate;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            birthDate = itemView.findViewById(R.id.birthdate);
        }
    }
}
