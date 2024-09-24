package com.example.recipi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotifFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notif, container, false)

        recyclerView = view.findViewById(R.id.notification_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NotificationAdapter(viewModel.notifications.value ?: mutableListOf())
        recyclerView.adapter = adapter

        viewModel.notifications.observe(viewLifecycleOwner, { notifications ->
            adapter.notifications = notifications // Update the adapter data
            adapter.notifyDataSetChanged()
        })

        return view
    }
}
