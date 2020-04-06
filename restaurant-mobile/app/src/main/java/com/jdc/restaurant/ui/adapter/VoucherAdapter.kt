package com.jdc.restaurant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdc.restaurant.api.dto.Voucher
import com.jdc.restaurant.databinding.ItemVoucherBinding

class VoucherAdapter:ListAdapter<Voucher, VoucherAdapter.VH>(VoucherItemCallback) {

    class VH(val binding:ItemVoucherBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemVoucherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.binding.voucher = item
    }
}

object VoucherItemCallback:DiffUtil.ItemCallback<Voucher>() {
    override fun areItemsTheSame(oldItem: Voucher, newItem: Voucher) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Voucher, newItem: Voucher) = oldItem == newItem
}