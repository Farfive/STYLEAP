package com.example.lilium.Shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lilium.R
import com.example.lilium.Shop.model.ProductCategory

class ProductCategoryAdapter(var context: Context, productCategoryList: List<ProductCategory>) :
    RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder>() {
    var productCategoryList: List<ProductCategory>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.category_row_item, parent, false)
        // lets create a recyclerview row item layout file
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return productCategoryList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var catagoryName: TextView

        init {
            catagoryName = itemView.findViewById(R.id.cat_name)
        }
    }

    init {
        this.productCategoryList = productCategoryList
    }
}
