package com.example.eurosport.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eurosport.R
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity
import com.example.eurosport.view.MediaPresentation
import com.example.eurosport.view.MediaType

class VideosStoriesRecyclerAdapter(
    val context: Context,
    val mediaClickListener: MediaClickListener
) :
    RecyclerView.Adapter<VideosStoriesRecyclerAdapter.VideosStoriesViewHolder>() {


    var listElement = mutableListOf<MediaPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosStoriesViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_storie_item, parent, false)
        return VideosStoriesViewHolder(v)
    }

    override fun onBindViewHolder(holder: VideosStoriesViewHolder, position: Int) {
        holder.bind(listElement[position])
    }

    override fun getItemCount() = listElement.size


    fun setTable(listElment: List<MediaPresentation>) {
        listElement = listElment.toMutableList()
        notifyDataSetChanged()
    }

    inner class VideosStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var sportName: TextView = itemView.findViewById(R.id.sport_name)
        var viewAuthor: TextView = itemView.findViewById(R.id.autho_views)
        var imageView: ImageView = itemView.findViewById(R.id.media_image)
        var playVideoImage: ImageView = itemView.findViewById(R.id.play_video)

        init {
            itemView.setOnClickListener {
                mediaClickListener.mediaClickListener(listElement[bindingAdapterPosition])
            }
        }

        fun bind(media: MediaPresentation) {
            title.text = media.title
            sportName.text = media.sportName
            viewAuthor.text =
                if (media.mediaType == MediaType.STORIE) media.author else media.views.toString() + context.getString(
                    R.string.video_views
                )

            var imageUrl =
                if (media.mediaType == MediaType.STORIE) media.image else media.thumb
            Glide.with(itemView.context).load(imageUrl).into(imageView)

            playVideoImage.visibility =
                if (media.mediaType == MediaType.STORIE) View.INVISIBLE else View.VISIBLE

        }

    }
}