package com.dylanmuszel.melichallenge.presentation.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.R
import com.facebook.drawee.view.SimpleDraweeView
import java.text.NumberFormat

/**
 * A [RecyclerView.Adapter] implementation holding a list of [Product]s.
 */
class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val list = mutableListOf<Product>()

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_product_item, parent, false))
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    /** Set a list of [products] after cleaning the old ones. */
    fun setProducts(products: List<Product>) {
        list.clear()
        list.addAll(products)
        notifyDataSetChanged()
    }

    /**
     * A [RecyclerView.ViewHolder] implementation holding a product item view.
     */
    inner class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<SimpleDraweeView>(R.id.image_view)
        private val titleText = itemView.findViewById<TextView>(R.id.title_text)
        private val priceText = itemView.findViewById<TextView>(R.id.price_text)

        /** Binds a [product] into a row. */
        fun bind(product: Product) = with(product) {
            imageView.setImageURI(thumbnail.toUri())
            titleText.text = title
            priceText.text = NumberFormat.getCurrencyInstance().format(price)
        }
    }
}
