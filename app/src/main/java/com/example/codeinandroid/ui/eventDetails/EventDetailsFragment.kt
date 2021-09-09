package com.example.codeinandroid.ui.eventDetails

import android.content.Context
import android.os.Bundle
import android.view.*
import com.bumptech.glide.Glide
import com.example.codeinandroid.R
import com.example.codeinandroid.databinding.FragmentEventDetailsBinding
import com.example.codeinandroid.ui.SharedViewModel
import com.example.codeinandroid.ui.base.BaseFragment
import com.example.codeinandroid.ui.model.EventUIItemModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding, EventDetailsViewModel>() {
    override var _binding: FragmentEventDetailsBinding? = null

    private var callback: Callback? = null
    private val eventDetailsViewModel: EventDetailsViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by lazy { requireParentFragment().getViewModel() }
    lateinit var args: EventDetailsFragmentArgs

    override fun getViewModel() = eventDetailsViewModel

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
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun initView() {
        args = EventDetailsFragmentArgs.fromBundle(requireArguments())
        eventDetailsViewModel.initializeArgs(args.eventDetails)
    }

    override fun initObserver() {
        eventDetailsViewModel.eventDetails.observe(viewLifecycleOwner) {
            it?.let { updateDetails(it) }
        }

        eventDetailsViewModel.isFavourite.observe(viewLifecycleOwner) {
            it?.let {
                binding.imgFavourite.setImageResource(if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_outiline)
            }
        }

        binding.imgFavourite.setOnClickListener {
            eventDetailsViewModel.onFavouriteClick()
            sharedViewModel.onFavouriteStatusChange()
        }
    }

    private fun updateDetails(uiItemModel: EventUIItemModel) {
        with(binding) {
            txtTitle.text = uiItemModel.title
            txtAddress.text = uiItemModel.address
            txtDateTime.text = uiItemModel.dateTime
            Glide.with(requireContext()).load(uiItemModel.image).into(imgEvent)
        }
    }

    interface Callback
}