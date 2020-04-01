package com.dylanmuszel.melichallenge.presentation.productdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.presentation.model.ProductAttributeUI

/**
 * A [BaseAdapter] implementation used to show product [attributes] on a grid view.
 */
class ProductAttributeAdapter(private val attributes: List<ProductAttributeUI>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(parent.context).inflate(R.layout.view_product_attribute_item, parent, false)
        with(attributes[position]) {
            view.findViewById<TextView>(R.id.name_text).text = name
            view.findViewById<TextView>(R.id.value_text).text = values
        }
        return view
    }

    override fun getItem(position: Int) = null

    override fun getItemId(position: Int) = 0L

    override fun getCount() = attributes.size
}
