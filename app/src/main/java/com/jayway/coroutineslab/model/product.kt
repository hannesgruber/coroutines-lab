package com.jayway.coroutineslab.model

import com.google.gson.annotations.SerializedName

data class Product(
    val producer : String,
    val name: String,
    @SerializedName("product_group")
    val productGroup : String,
    @SerializedName("type")
    val productType : String?,
    val style : String?,
    val alcohol : String) {

    fun getType() : String {
        return style ?: productGroup
    }
}