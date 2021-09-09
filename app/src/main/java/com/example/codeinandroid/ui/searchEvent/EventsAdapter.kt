package com.example.codeinandroid.ui.searchEvent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.codeinandroid.databinding.ItemEventDetailsBinding
import com.example.codeinandroid.ui.base.BaseViewHolder
import com.example.codeinandroid.ui.model.EventUIItemModel
import java.lang.ref.WeakReference

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
    private val dataList = ArrayList<EventUIItemModel>()
    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): EventsViewHolder {
        val binding =
            ItemEventDetailsBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return EventsViewHolder(binding, WeakReference(listener))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.onBind(position, dataList[position])
    }

    fun setData(newDataList: List<EventUIItemModel>) {
        val diffCallback = DiffCallback(dataList, newDataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        dataList.addAll(newDataList)
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickListener {
        fun onEventClick(eventUIItemModel: EventUIItemModel)
    }


    class EventsViewHolder(
        private val binding: ItemEventDetailsBinding,
        private val onItemClickListener: WeakReference<OnItemClickListener?>
    ) : BaseViewHolder<EventUIItemModel>(binding.root) {
        override fun onBind(position: Int, data: EventUIItemModel) {
            with(binding) {
                Glide.with(root.context).load(data.image)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imgEvent)
                txtTitle.text = data.title
                txtAddress.text = data.address
                txtDateTime.text = data.dateTime
                root.setOnClickListener {
                    onItemClickListener.get()?.onEventClick(data)
                }
            }
        }
    }

    private class DiffCallback(
        private val oldList: List<EventUIItemModel>,
        private val newList: List<EventUIItemModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
