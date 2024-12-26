package com.luukitoo.geolabnestedrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luukitoo.geolabnestedrecyclerview.adapter.CategoryAdapter
import com.luukitoo.geolabnestedrecyclerview.adapter.ProductsPageContentAdapter
import com.luukitoo.geolabnestedrecyclerview.data.Category
import com.luukitoo.geolabnestedrecyclerview.data.Product
import com.luukitoo.geolabnestedrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val categoriesList = (listOf("All", "Woman", "Man", "All", "Woman", "Man", "All", "Woman", "Man")).map { Category(name = it) }

    private val productsContentAdapter = ProductsPageContentAdapter(
        products = Product.generateList(),
        categoryAdapter = CategoryAdapter().apply {
            submitList(categoriesList)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = productsContentAdapter
    }
}