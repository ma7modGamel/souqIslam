package com.safwa.souqclean.data.models.products

import android.media.audiofx.AudioEffect.Descriptor

data class ProductModel(
    val id:Int,
    val name:String,
    val price:Double,
    val image:String,
    val description:String,
    val category:String,
)
