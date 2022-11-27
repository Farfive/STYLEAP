package com.example.lilium.Shop.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lilium.R
import com.example.lilium.Shop.model.Products


class ProductAdapter(var context: RecyclerView?, productsList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productsList: List<Products>


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.prodImage.setImageResource(productsList[position].imageUrl)
        holder.prodName.text = productsList[position].productName
        holder.prodQty.text = productsList[position].productQty
        holder.prodPrice.text = productsList[position].productPrice
        holder.itemView.setOnClickListener {

        }
    }



    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var prodImage: ImageView
        var prodName: TextView
        var prodQty: TextView
        var prodPrice: TextView

        init {
            prodImage = itemView.findViewById(R.id.prod_image)
            prodName = itemView.findViewById(R.id.prod_name)
            prodPrice = itemView.findViewById(R.id.prod_price)
            prodQty = itemView.findViewById(R.id.prod_qty)
        }
    }

    init {
        this.productsList = productsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        TODO("Not yet implemented")
    }
}
