package com.atihon09.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atihon09.quotes.R
import com.atihon09.quotes.databinding.CategoryItemBinding

class CategoryAdapter(var listener: Listener) :
    ListAdapter<String, CategoryAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CategoryItemBinding.bind(view)

        fun setData(text: String, listener: Listener) = with(binding) {

            tvCatTitle.text = text
            imButton.setImageResource(ContentManager.btnImageList[adapterPosition])
//            cardViewCat.backgroundTintList = ColorStateList
//                .valueOf(Color.parseColor(ContentManager.colorList[adapterPosition]))
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
            imButton.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }

    class Comparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(getItem(position), listener)
    }


    interface Listener {
        fun onClick(pos: Int)
    }
}