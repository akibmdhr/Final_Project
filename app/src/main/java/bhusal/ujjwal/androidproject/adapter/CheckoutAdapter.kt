package bhusal.ujjwal.androidproject.adapter

import bhusal.ujjwal.androidproject.R
import bhusal.ujjwal.androidproject.model.Product


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheckoutAdapter(val lstProduct: ArrayList<Product>, val context: Context) :
    RecyclerView.Adapter<CheckoutAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductname: TextView = view.findViewById(R.id.tvProductname)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val profilePic: ImageView = view.findViewById(R.id.profilePic)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.display_cart, parent, false)
        return ProductViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val checkout = lstProduct[position]
        holder.tvProductname.text = checkout.productname
        holder.tvDescription.text = checkout.description
        holder.tvPrice.text = checkout.price.toString()
        holder.tvCategory.text = checkout.category
        if (checkout.category == "Grocery") {
            holder.profilePic.setImageResource(R.drawable.male_pp)
        } else if (checkout.category == "Clothes") {
            holder.profilePic.setImageResource(R.drawable.male_pp)
        } else
            holder.profilePic.setImageResource(R.drawable.male_pp)
    }

    override fun getItemCount(): Int {
        return lstProduct.size
    }
}
