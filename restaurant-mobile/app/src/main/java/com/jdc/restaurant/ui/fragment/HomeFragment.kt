package com.jdc.restaurant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jdc.restaurant.R
import com.jdc.restaurant.databinding.FragmentHomeBinding
import com.jdc.restaurant.ui.adapter.TableAdapter
import com.jdc.restaurant.ui.model.HomeModel
import com.jdc.restaurant.ui.utils.NextTransition
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TableAdapter()
        val model by activityViewModels<HomeModel>()
        val binding = FragmentHomeBinding.bind(view)

        recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        recycler.adapter = adapter

        binding.lifecycleOwner = viewLifecycleOwner
        binding.model = model

        model.message.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            }
        })

        model.alreadyLogin.observe(viewLifecycleOwner, Observer {
            if(it) {
                if(model.firstTime) {
                    homeMotion.setTransitionListener(NextTransition {
                        homeMotion.transitionToState(R.id.loginOK)
                    })

                    homeMotion.transitionToEnd()
                    model.firstTime = false
                } else {
                    homeMotion.setTransitionListener(NextTransition{})
                    homeMotion.setTransition(R.id.hideLoginView, R.id.loginOK)
                    homeMotion.transitionToEnd()
                }
            } else {
                homeMotion.transitionToStart()
            }
        })

        model.tables.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }



}