package com.example.eurosport.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eurosport.R
import com.example.eurosport.repository.network.client.MainRepository
import com.example.eurosport.view.MediaPresentation
import com.example.eurosport.view.MediaType
import com.example.eurosport.view.adapters.MediaClickListener
import com.example.eurosport.view.adapters.VideosStoriesRecyclerAdapter
import com.example.eurosport.viewModel.VideoStorieViewModel
import com.example.eurosport.viewModel.ViewModelFactory


class VideoStoriesFragment : Fragment(), MediaClickListener {

    var videosStoriesRecycler: RecyclerView? = null
    var errorMessageTxt: TextView? = null
    companion object{
        val MEDIA_DETAILS = "mediaDetails"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.videos_stories_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videosStoriesRecycler = view.findViewById(R.id.videosStoriesRecycler)
        videosStoriesRecycler?.layoutManager = LinearLayoutManager(context)
        errorMessageTxt = view.findViewById(R.id.errorMessage)

        val mainRepository = MainRepository()
        var viewModel = ViewModelProvider(
            this,
            ViewModelFactory(context, mainRepository)
        )[VideoStorieViewModel::class.java]
        viewModel.getVideosStories()

        val adapter = VideosStoriesRecyclerAdapter(requireContext(), this)
        videosStoriesRecycler?.adapter = adapter
        viewModel.movieList.observe(viewLifecycleOwner, { list ->
            adapter.setTable(list)
            errorMessageTxt?.let { textView ->
                textView.visibility = View.GONE
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            errorMessageTxt?.let { textView ->
                textView.visibility = View.VISIBLE
            }
        })
    }

    override fun mediaClickListener(media: MediaPresentation) {
        VideoStoriesFragmentDirections.actionVideoStorieFragmentToStorieDetailFragment()
        val bundle = bundleOf(MEDIA_DETAILS to media)
        when(media.mediaType){
            MediaType.STORIE -> findNavController().navigate(
                R.id.action_videoStorieFragment_to_storieDetailFragment,
                bundle
            )
            MediaType.VIDEO -> findNavController().navigate(
                R.id.action_videoStorieFragment_to_videoReaderFragment,
                bundle
            )
            null -> TODO()
        }

    }
}