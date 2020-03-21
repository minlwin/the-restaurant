package com.jdc.restaurant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdc.restaurant.api.dto.Table
import com.jdc.restaurant.databinding.ItemTableBinding

class TableAdapter:ListAdapter<Table, TableAdapter.VH>(TableCallback) {

    class VH(val binding:ItemTableBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.table = getItem(position)
    }
}

object TableCallback:DiffUtil.ItemCallback<Table>() {
    override fun areItemsTheSame(oldItem: Table, newItem: Table) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Table, newItem: Table) = oldItem == newItem
}