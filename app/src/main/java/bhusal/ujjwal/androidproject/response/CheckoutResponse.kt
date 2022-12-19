package bhusal.ujjwal.androidproject.response

import bhusal.ujjwal.androidproject.model.Product

data class CheckoutResponse(
    val success : Boolean? = null,
    val data : MutableList<Product>? =null
)
