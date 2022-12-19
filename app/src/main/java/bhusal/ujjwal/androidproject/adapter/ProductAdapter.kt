package bhusal.ujjwal.androidproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bhusal.ujjwal.androidproject.R
import bhusal.ujjwal.androidproject.api.ServiceBuilder
import bhusal.ujjwal.androidproject.model.Product
import bhusal.ujjwal.androidproject.repository.ProductRepository
import bhusal.ujjwal.androidproject.ui.CheckoutActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ProductAdapter(val lstProduct: ArrayList<Product>, val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductnameEdit: EditText = view.findViewById(R.id.tvProductnameEdit)
        val tvPriceEdit: EditText = view.findViewById(R.id.tvPriceEdit)
        val tvDescriptionEdit: EditText = view.findViewById(R.id.tvDescriptionEdit)
        val tvCategoryEdit: EditText = view.findViewById(R.id.tvCategoryEdit)
        val tvProductname: TextView = view.findViewById(R.id.tvProductname)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val profilePic: ImageView = view.findViewById(R.id.profilePic)
        val imgDelete: ImageView = view.findViewById(R.id.imgDeletes)
        val imgUpdate: ImageView = view.findViewById(R.id.imgUpdate)
        val addCart: ImageView = view.findViewById(R.id.addCart)
        val addCart2: ImageView = view.findViewById(R.id.likeProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.display_products, parent, false)
        return ProductViewHolder(view)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = lstProduct[position]
        holder.tvProductname.text = product.productname
        holder.tvDescription.text = product.description
        holder.tvPrice.text = product.price.toString()
        holder.tvCategory.text = product.category
        holder.tvProductnameEdit.setVisibility(View.GONE)
        holder.tvPriceEdit.setVisibility(View.GONE)
        holder.tvDescriptionEdit.setVisibility(View.GONE)
        holder.tvCategoryEdit.setVisibility(View.GONE)
        if (product.category == "Grocery") {
            holder.profilePic.setImageResource(R.drawable.male_pp)
        } else if (product.category == "Clothes") {
            holder.profilePic.setImageResource(R.drawable.female_pp)
        } else
            holder.profilePic.setImageResource(R.drawable.noimage)
        holder.profilePic.setOnClickListener {
            Toast.makeText(context, "Hello this is ${product.productname}", Toast.LENGTH_SHORT).show()
        }
        holder.imgDelete.setOnClickListener {
            lstProduct.removeAt(position)
            notifyDataSetChanged()
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete product")
            builder.setMessage("Are you sure you want to delete ${product.productname} ??")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val productRepository = ProductRepository()
                        val response = productRepository.deleteProduct(product)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Product Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Main) {
                                lstProduct.remove(product)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
//        holder.addCart2.setOnClickListener {
//
//        }


        holder.addCart2.setOnClickListener {
            lstProduct.removeAt(position)
            notifyDataSetChanged()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val productRepository = ProductRepository()
                    val response = productRepository.deleteProduct(product)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                        }
                        withContext(Main) {
                            lstProduct.remove(product)
                            notifyDataSetChanged()
                            //update product
                            val product =Product(
                                productname = holder.tvProductnameEdit.text.toString(),
                                description = holder.tvDescriptionEdit.text.toString(),
                                price = holder.tvPriceEdit.text.toString(),
                                category = holder.tvCategoryEdit.text.toString()
                            )
                            CoroutineScope(Dispatchers.IO).launch {
                                val repository = ProductRepository()
                                val response = repository.addProduct(product)
                                if (response.success == true) {
                                    withContext(Dispatchers.Main) {
                                        //room database add data
                                        Toast.makeText(context,"Product Update Sucessfully!!", Toast.LENGTH_LONG).show()
                                    }
                                }
                                else {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(context, "Error update Product", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                            //update code end
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        holder.imgUpdate.setOnClickListener {
            holder.profilePic.setVisibility(View.GONE)
            holder.tvCategoryEdit.setVisibility(View.VISIBLE)
            holder.tvProductnameEdit.setVisibility(View.VISIBLE)
            holder.tvPriceEdit.setVisibility(View.VISIBLE)
            holder.tvDescriptionEdit.setVisibility(View.VISIBLE)
            holder.addCart.setVisibility(View.GONE)
            holder.imgUpdate.setVisibility(View.GONE)
            holder.imgDelete.setVisibility(View.GONE)
            holder.tvProductname.setVisibility(View.GONE)
            holder.tvPrice.setVisibility(View.GONE)
            holder.tvDescription.setVisibility(View.GONE)
            holder.tvCategory.setVisibility(View.GONE)
//            holder.rsPrice
            holder.tvProductnameEdit.setText(holder.tvProductname.text)
            holder.tvDescriptionEdit.setText(holder.tvDescription.text)
            holder.tvPrice.setText(holder.tvPrice.toString())
            holder.tvCategoryEdit.setText(holder.tvCategory.text)
            val images = intArrayOf(R.drawable.photo1, R.drawable.photo2,
                R.drawable.photo3)
            val rand = Random()
            if (product.category == "Spicy") {
                holder. profilePic.setImageResource(images[rand.nextInt(images.size)])
            } else if (product.category == "Non Spicy") {
                holder. profilePic.setImageResource(images[rand.nextInt(images.size)])
            } else
                holder. profilePic.setImageResource(images[rand.nextInt(images.size)])
        }

        holder.addCart.setOnClickListener {
            //add to cart here......
            lstProduct.get(position)
            notifyDataSetChanged()
            val builder = AlertDialog.Builder(context)
            builder.setTitle("add to cart")
            builder.setMessage("Are you sure you want to add ${product.productname} to cart??")
            //builder.setIcon(android.R.drawable.ic_input_add)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        //intent to start activity
                        val intent1 = Intent(context, CheckoutActivity::class.java)
                        intent1.putExtra("Productname", holder.tvProductname.getText().toString())
                        intent1.putExtra("Price", holder.tvPrice.getText().toString())
                        intent1.putExtra("Category", holder.tvCategory.getText().toString())
                        intent1.putExtra("Description", holder.tvDescription.getText().toString())


                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }

        val imagePath = ServiceBuilder.loadImagePath() + product.photo
        if (!product.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.profilePic)
        }

    }



    override fun getItemCount(): Int {
        return lstProduct.size
    }
}
