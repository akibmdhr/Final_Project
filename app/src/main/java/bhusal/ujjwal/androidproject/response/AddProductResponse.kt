package bhusal.ujjwal.androidproject.response

import bhusal.ujjwal.androidproject.model.Product

data class AddProductResponse (
    val success : Boolean? = null,
    val data : Product? = null
)