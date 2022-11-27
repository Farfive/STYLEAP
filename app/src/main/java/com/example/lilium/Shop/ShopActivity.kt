package com.example.lilium.Shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lilium.R
import com.example.lilium.Shop.adapter.ProductAdapter
import com.example.lilium.Shop.adapter.ProductCategoryAdapter
import com.example.lilium.Shop.model.ProductCategory
import com.example.lilium.Shop.model.Products
import java.util.*

class ShopActivity : AppCompatActivity() {
    var productCategoryAdapter: ProductCategoryAdapter? = null
    var productCatRecycler: RecyclerView? = null
    var prodItemRecycler: RecyclerView? = null
    var productAdapter: ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        val productCategoryList: MutableList<ProductCategory> = ArrayList<ProductCategory>()
        productCategoryList.add(ProductCategory(1, "Trending"))
        productCategoryList.add(ProductCategory(2, "Most Popular"))
        productCategoryList.add(ProductCategory(3, "All Body Products"))
        productCategoryList.add(ProductCategory(4, "Skin Care"))
        productCategoryList.add(ProductCategory(5, "Hair Care"))
        productCategoryList.add(ProductCategory(6, "Make Up"))
        productCategoryList.add(ProductCategory(7, "Fragrance"))
        setProductRecycler(productCategoryList)
        val productsList: MutableList<Products> = ArrayList<Products>()
        productsList.add(
            Products(
                1,
                "Japanese Cherry Blossom",
                "250 ml",
                "$ 17.00",
                R.drawable.prod2
            )
        )
        productsList.add(
            Products(
                2,
                "African Mango Shower Gel",
                "350 ml",
                "$ 25.00",
                R.drawable.prod1
            )
        )
        productsList.add(
            Products(
                1,
                "Japanese Cherry Blossom",
                "250 ml",
                "$ 17.00",
                R.drawable.prod2
            )
        )
        productsList.add(
            Products(
                2,
                "African Mango Shower Gel",
                "350 ml",
                "$ 25.00",
                R.drawable.prod1
            )
        )
        productsList.add(
            Products(
                1,
                "Japanese Cherry Blossom",
                "250 ml",
                "$ 17.00",
                R.drawable.prod2
            )
        )
        productsList.add(
            Products(
                2,
                "African Mango Shower Gel",
                "350 ml",
                "$ 25.00",
                R.drawable.prod1
            )
        )
        setProdItemRecycler(productsList)
    }

    private fun setProductRecycler(productCategoryList: List<ProductCategory>) {
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productCatRecycler!!.layoutManager = layoutManager
        productCategoryAdapter = ProductCategoryAdapter(this, productCategoryList)
        productCatRecycler!!.adapter = productCategoryAdapter
    }

    private fun setProdItemRecycler(productsList: List<Products>) {
        prodItemRecycler = findViewById(R.id.product_recycler)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        with(prodItemRecycler) {
            productAdapter = ProductAdapter(this, productsList)
        }
    } 
}
