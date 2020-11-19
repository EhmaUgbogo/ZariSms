package com.ehmaugbogo.zarisms.views.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ehmaugbogo.zarisms.R
import com.ehmaugbogo.zarisms.model.HomeItem
import com.ehmaugbogo.zarisms.util.Constant
import kotlinx.android.synthetic.main.item_grid_item.view.*

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 2020-11-17
 */

class MainAdapter(private val listener: (homeItem: HomeItem) -> Unit) : ListAdapter<HomeItem, MainAdapter.MainHolder>(
    MainItemDiffCallback()
){

    init { submitList(createHomeItems()) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_item, parent, false)
        return MainHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) = holder.bind(getItem(position))

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener(getItem(adapterPosition)) }
        }

        fun bind(homeItem: HomeItem) {
            itemView.apply {
                item_title_tv.text = homeItem.title
                item_desc_textView.text = homeItem.desc
                item_imageView.setImageResource(homeItem.icon)
            }
        }
    }
}

fun createHomeItems(): List<HomeItem> {
    return listOf(
        HomeItem(Constant.SEND_MAIL, "Send bulk mail", R.drawable.ic_edit, R.id.mailFragment, "Send Sms"),
        HomeItem(Constant.SENT_MESSAGE, "Check messages you have previously sent", R.drawable.ic_message, 0),
        HomeItem(Constant.DRAFT, "", R.drawable.ic_email, 0),
        HomeItem(Constant.PHONE_BOOK, "", R.drawable.ic_contact_phone, 0),
        HomeItem(Constant.BUY_SMS_UNIT, "", R.drawable.ic_add_shopping_cart, 0),
        HomeItem(Constant.RATE, "", R.drawable.ic_star_half, 0),
        HomeItem(Constant.LIVE_CHAT, "", R.drawable.ic_chat, 0),
        HomeItem(Constant.ABOUT_US, "", R.drawable.ic_info, 0),
    )
}

class MainItemDiffCallback : DiffUtil.ItemCallback<HomeItem>(){
    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.title == newItem.title
    }
}








