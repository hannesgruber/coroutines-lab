package com.jayway.coroutineslab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jayway.coroutineslab.model.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private val data : List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(product: Product) {
        itemView.producer.text = product.producer
        itemView.name.text = product.name
        itemView.info.text = product.getType() + ", " + product.alcohol
    }
}