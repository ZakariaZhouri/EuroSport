package com.example.eurosport.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eurosport.R
import com.example.eurosport.view.MediaPresentation
import com.example.eurosport.view.fragments.VideoStoriesFragment.Companion.MEDIA_DETAILS

class StorieDetailsFragment : Fragment() {


    private var mediaDetail: MediaPresentation? = null

    var sportNameTxt: TextView? = null
    var titleTxt: TextView? = null
    var authorTxt: TextView? = null
    var storiDetailsTxt: TextView? = null
    var imageDetails: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mediaDetail = it.getParcelable(MEDIA_DETAILS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.storie_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            sportNameTxt = this.findViewById(R.id.details_sport_name)
            titleTxt = this.findViewById(R.id.details_title)
            authorTxt = this.findViewById(R.id.details_autho_views)
            storiDetailsTxt = this.findViewById(R.id.stori_details)
            imageDetails = this.findViewById(R.id.details_media_image)
        }
        val safeArgs: StorieDetailsFragmentArgs by navArgs()
        val media = safeArgs.mediaDetails as MediaPresentation
        media.apply {
            sportNameTxt?.text = sportName
            titleTxt?.text = title
            authorTxt?.text = author
            storiDetailsTxt?.text = teaser
            context?.let {
                Glide.with(it).load(image).into(imageDetails!!)
            }

            
        }
    }


}