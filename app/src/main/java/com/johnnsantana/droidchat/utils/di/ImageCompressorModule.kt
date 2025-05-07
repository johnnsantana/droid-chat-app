package com.johnnsantana.droidchat.utils.di

import com.johnnsantana.droidchat.utils.images.ImageCompressor
import com.johnnsantana.droidchat.utils.images.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(compressor: ImageCompressorImpl): ImageCompressor

}