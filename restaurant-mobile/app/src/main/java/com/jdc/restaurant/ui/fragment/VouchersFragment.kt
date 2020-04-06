package com.jdc.restaurant.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdc.restaurant.R
import com.jdc.restaurant.api.dto.Table
import com.jdc.restaurant.ui.adapter.VoucherAdapter
import com.jdc.restaurant.ui.model.VouchersModel
import kotlinx.android.synthetic.main.fragment_vouchers.*

class VouchersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vouchers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model by viewModels<VouchersModel>()

        val adapter = VoucherAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        model.vouchers.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        model.showFab.observe(viewLifecycleOwner, Observer {
            if(it) fab.show() else fab.hide()
        })

        arguments?.getSerializable("table")?.also {
            val table = it as Table
            model.tableNumber.value = table.id
            val appCompactActivity = requireActivity() as AppCompatActivity
            appCompactActivity.supportActionBar?.title = table.tableNumber
        }
    }
}