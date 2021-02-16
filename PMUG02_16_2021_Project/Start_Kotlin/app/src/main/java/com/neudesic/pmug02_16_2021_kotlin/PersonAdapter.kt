package com.neudesic.pmug02_16_2021_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neudesic.pmug02_16_2021_kotlin.model.Person

class PersonAdapter(private var people: List<Person>): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_view_person, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person: Person = people.get(position)
        holder.name.text = person.name
        holder.age.text = person.age.toString()
        holder.birthDate.text = person.birthday.toString()
    }

    override fun getItemCount(): Int {
        return people.size
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var age: TextView = itemView.findViewById(R.id.age)
        var birthDate: TextView = itemView.findViewById(R.id.birthdate)

    }

    fun update(people: List<Person>) {
        this.people = people
    }
}