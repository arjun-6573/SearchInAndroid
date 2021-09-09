package com.example.codeinandroid.ui.searchEvent

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeinandroid.databinding.FragmentSearchEventListBinding
import com.example.codeinandroid.ui.SharedViewModel
import com.example.codeinandroid.ui.base.BaseFragment
import com.example.codeinandroid.ui.model.EventUIItemModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchEventsFragment : BaseFragment<FragmentSearchEventListBinding, SearchEventsViewModel>(),
    EventsAdapter.OnItemClickListener {
    override var _binding: FragmentSearchEventListBinding? = null

    private var callback: Callback? = null
    private val searchEventsViewModel: SearchEventsViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by lazy { requireParentFragment().getViewModel() }
    private var eventsAdapter: EventsAdapter? = null

    override fun getViewModel() = searchEventsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback)
            callback = context
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventsAdapter = null
    }


    override fun initView() {
        eventsAdapter = EventsAdapter()
        eventsAdapter?.listener = this
        with(binding) {
            rvSearchedEvents.layoutManager = LinearLayoutManager(context)
            rvSearchedEvents.adapter = eventsAdapter
        }
    }

    override fun initObserver() {
        searchEventsViewModel.eventList.observe(viewLifecycleOwner) {
            it?.let { eventsAdapter?.setData(it) }
        }
        with(binding) {
            txtCancelSearch.setOnClickListener { etSearch.setText("") }
            etSearch.addTextChangedListener {
                it?.let { editable ->
                    searchEventsViewModel.searchEvent(editable.toString())
                }
            }
        }

        sharedViewModel.refreshEvents.observe(viewLifecycleOwner) {
            if (it == true) {
                searchEventsViewModel.onRefresh()
            }
        }
    }

    override fun onEventClick(eventUIItemModel: EventUIItemModel) {
        val direction =
            SearchEventsFragmentDirections.actionSearchEventsFragmentToEventDetailsFragment(
                eventUIItemModel
            )
        findNavController().navigate(direction)
    }

    interface Callback
}