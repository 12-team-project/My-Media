package com.example.my_media.home.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.my_media.R
import com.example.my_media.databinding.ItemVideosBinding

class HomePopularListAdapter(
    val context: Context,
    val itemClickListener: (HomePopularModel) -> Unit
): ListAdapter<HomePopularModel, HomePopularListAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<HomePopularModel>() {
        override fun areItemsTheSame(oldItem: HomePopularModel, newItem: HomePopularModel): Boolean {
            return oldItem.txtTitle == newItem.txtTitle
        }

        override fun areContentsTheSame(oldItem: HomePopularModel, newItem: HomePopularModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class ViewHolder(private val binding: ItemVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //클릭된 아이템 뷰홀더 위치를 사용하여 get(position)으로 클릭된아이템가져오기
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                itemClickListener(getItem(position))
            }
        }
        fun bind(item: HomePopularModel) = with(binding) {
            imgThumbnail.load(item.imgThumbnail) {
                error(R.drawable.ic_no_image)
            }
            txtTitle.text = item.txtTitle
            root.setOnClickListener {
                itemClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lastPosition = -1
        if (holder.adapterPosition > lastPosition){
            val animation: Animation = AnimationUtils.loadAnimation(context,R.anim.slide_in_row)
            holder.bind(getItem(position))
            holder.itemView.startAnimation(animation)
        }

    }
}