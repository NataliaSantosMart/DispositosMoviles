package com.spendTogether.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.Group
import com.spendTogether.views.GroupViewHolder

class GroupAdapter(private val groups: List<Group>) : RecyclerView.Adapter<GroupViewHolder> (){

    //Inflar el elemento o view a manejar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_list_item, parent, false);
        return GroupViewHolder(view);
    }

    //Contar el numero total de items
    override fun getItemCount(): Int {
        return groups.size;
    }

    //Adaptar una de las vistas
    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
       holder.render(groups[position])
    }

}