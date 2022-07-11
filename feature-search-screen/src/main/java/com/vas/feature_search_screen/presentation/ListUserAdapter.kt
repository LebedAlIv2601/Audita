package com.vas.feature_search_screen.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vas.feature_search_screen.R
import com.vas.feature_search_screen.databinding.ItemUserBinding
import com.vas.feature_search_screen.domain.model.UserModel

class ListUserAdapter: RecyclerView.Adapter<ListUserViewHolder>(){

    var data = listOf<UserModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnListUserClickListener? = null

    interface OnListUserClickListener {
        fun onClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder =
        ListUserViewHolder.from(parent)

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onClickListener!!)
    }

    override fun getItemCount() = data.size


}

class ListUserViewHolder(itemView: ItemUserBinding) : RecyclerView.ViewHolder(itemView.root){

    private val imageViewProfile: ImageView = itemView.imageViewProfile
    private val imageViewInstrument: ImageView = itemView.imageViewInstrument
    private val imageViewLevel: ImageView = itemView.imageViewLevel
    private val cardViewLevel: CardView = itemView.cardViewLevel
    private val cardViewInstrument: CardView = itemView.cardViewInstrument

    fun bind(item: UserModel, onListUserClickListener: ListUserAdapter.OnListUserClickListener) {

        imageViewProfile.setImageResource(item.photo)

        if (item.type == "musician" || item.type == "band"){
            imageViewInstrument.setImageResource(item.instrument!!)
            imageViewLevel.setImageResource(R.drawable.ic_level)
            cardViewLevel.setCardBackgroundColor(Color.parseColor(item.levelColor))
        } else {
            cardViewInstrument.visibility = View.INVISIBLE
            imageViewLevel.setImageResource(R.drawable.ic_price)
            cardViewLevel.setCardBackgroundColor(Color.parseColor(item.levelColor))
        }

        imageViewProfile.setOnClickListener {
            onListUserClickListener.onClick(item.id)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ListUserViewHolder {
            val binding = ItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ListUserViewHolder(binding)
        }
    }
}