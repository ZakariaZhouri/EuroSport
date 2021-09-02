package com.example.eurosport.view.adapters

import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.view.MediaPresentation

interface MediaClickListener {

    fun mediaClickListener(media: MediaPresentation)
}