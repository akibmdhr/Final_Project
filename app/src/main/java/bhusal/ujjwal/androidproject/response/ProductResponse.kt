package bhusal.ujjwal.androidproject.response

import bhusal.ujjwal.androidproject.model.Product

data class ProductResponse(
    val success : Boolean? = null,
    val data : MutableList<Product>? =null
)
