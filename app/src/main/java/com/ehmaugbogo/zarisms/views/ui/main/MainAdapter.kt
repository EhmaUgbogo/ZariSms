package com.ehmaugbogo.zarisms.views.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.model.MainItem
import com.ehmaugbogo.zarisms.util.Constant
import kotlinx.android.synthetic.main.item_grid_item.view.*

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

class MainAdapter(private val listener: (mainItem: MainItem) -> Unit) : ListAdapter<MainItem, MainAdapter.MainHolder>(MainItemDiffCallback()){

    init { submitList(createMainItems()) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MainHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_item, parent, false)
        return MainHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) = holder.bind(getItem(position))

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener(getItem(adapterPosition)) }
        }

        fun bind(mainItem: MainItem) {
            itemView.apply {
                item_title_tv.text = mainItem.title
                item_desc_textView.text = mainItem.desc
                item_imageView.setImageResource(mainItem.icon)
            }
        }
    }
}

private fun createMainItems(): List<MainItem> {
    return listOf(
        MainItem(Constant.SEND_MAIL, "Send bulk mail", R.drawable.ic_edit, R.id.mailFragment),
        MainItem(Constant.SENT_MESSAGE, "Check messages you have previously sent", R.drawable.ic_message, 0),
        MainItem(Constant.DRAFT, "", R.drawable.ic_email, 0),
        MainItem(Constant.PHONE_BOOK, "", R.drawable.ic_contact_phone, 0),
        MainItem(Constant.BUY_SMS_UNIT, "", R.drawable.ic_add_shopping_cart, 0),
        MainItem(Constant.RATE, "", R.drawable.ic_star_half, 0),
        MainItem(Constant.LIVE_CHAT, "", R.drawable.ic_chat, 0),
        MainItem(Constant.ABOUT_US, "", R.drawable.ic_info, 0),
    )
}

class MainItemDiffCallback : DiffUtil.ItemCallback<MainItem>(){
    override fun areItemsTheSame(oldItem: MainItem, newItem: MainItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MainItem, newItem: MainItem): Boolean {
        return oldItem.title == newItem.title
    }
}








