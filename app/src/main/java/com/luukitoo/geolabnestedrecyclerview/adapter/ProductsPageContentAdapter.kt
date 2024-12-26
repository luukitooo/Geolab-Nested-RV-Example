package com.luukitoo.geolabnestedrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luukitoo.geolabnestedrecyclerview.data.Product
import com.luukitoo.geolabnestedrecyclerview.databinding.ItemCategoriesRvBinding
import com.luukitoo.geolabnestedrecyclerview.databinding.ItemFooterBinding
import com.luukitoo.geolabnestedrecyclerview.databinding.ItemProductBinding

class ProductsPageContentAdapter(
    private val products: List<Product>,
    private val categoryAdapter: CategoryAdapter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CategoriesViewHolder(private val binding: ItemCategoriesRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.categoriesRecyclerView.adapter = categoryAdapter
        }
    }

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Glide.with(productImageView)
                .load(product.imageUrl)
                .into(productImageView)
            nameTextView.text = product.name
            descriptionTextView.text = product.description
            priceTextView.text = "${product.price}$"
        }
    }

    class FooterViewHolder(binding: ItemFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_CATEGORIES
            products.size + 1 -> VIEW_TYPE_FOOTER
            else -> VIEW_TYPE_PRODUCT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CATEGORIES -> {
                val binding = ItemCategoriesRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CategoriesViewHolder(binding)
            }
            VIEW_TYPE_FOOTER -> {
                val binding = ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FooterViewHolder(binding)
            }
            VIEW_TYPE_PRODUCT -> {
                val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProductViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong viewType was found: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoriesViewHolder -> holder.bind()
            is ProductViewHolder -> holder.bind(products[position - 1])
        }
    }

    override fun getItemCount() = products.size + 2

    companion object {
        const val VIEW_TYPE_CATEGORIES = 0
        const val VIEW_TYPE_FOOTER = 1
        const val VIEW_TYPE_PRODUCT = 2
    }
}