package com.example.eurosport.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.eurosport.R
import com.example.eurosport.view.MediaPresentation
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.video_reader_fragment.*


class VideoReaderFragment : Fragment() {

    var videoPlayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_reader_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: StorieDetailsFragmentArgs by navArgs()
        val media = safeArgs.mediaDetails as MediaPresentation
        media.videoUrl?.let {
            initializePlayer(it)

        }

    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        videoPlayer?.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun releasePlayer() {
        videoPlayer?.release()
    }

    private fun buildMediaSource(url: String): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(context, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))
    }

    private fun initializePlayer(url: String) {
        context?.let { context ->
            videoPlayer = SimpleExoPlayer.Builder(context).build()
            idExoPlayerVIew?.player = videoPlayer
            buildMediaSource(url)?.let {
                videoPlayer?.prepare(it)
            }
        }

    }
}