package com.ftpd.hafhashtad.post.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ftpd.hafhashtad.base.models.PostData
import kotlinx.android.synthetic.main.post_cell.view.priceTextView
import kotlinx.android.synthetic.main.post_cell.view.imageView
import kotlinx.android.synthetic.main.post_cell.view.categoryTextView
import kotlinx.android.synthetic.main.post_cell.view.rateTextView
import kotlinx.android.synthetic.main.post_cell.view.titleTextView
import com.ftpd.hafhashtad.base_android.adapter_utils.Change

class PostsAdapter(val context: Context) : ListAdapter<PostData, PostsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(PostsDiffUtil()).build()
), Filterable {

    var postFilterList = mutableListOf<PostData>()

    private class PostsDiffUtil : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: PostData, newItem: PostData): Any {
            return Change(
                oldItem, newItem
            )
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(currentList[position]?.image).into(holder.imageView)
        holder.bindPostCell(currentList[position])

    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: AppCompatImageView = view.imageView
        private val titleTextView = view.titleTextView
        private val priceTextView = view.priceTextView
        private val categoryTextView = view.categoryTextView
        private val rateTextView = view.rateTextView

        fun bindPostCell(postData: PostData) {
            titleTextView.text = postData.title
            priceTextView.text = postData.price
            categoryTextView.text = postData.category
            postData.rating?.let {
                rateTextView.text = "${it.rate} (${it.count})"
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                postFilterList = if (charSearch.isEmpty()) {
                    currentList
                } else {
                    val resultList = ArrayList<PostData>()
                    for (row in currentList) {
                        if (row.title.lowercase().contains(constraint.toString().lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = postFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                postFilterList = results?.values as MutableList<PostData>
                submitList(postFilterList)
            }
        }
    }
}