package bhusal.ujjwal.androidproject.repository

import bhusal.ujjwal.androidproject.api.MyApiRequest
import bhusal.ujjwal.androidproject.api.ProductApi
import bhusal.ujjwal.androidproject.api.ServiceBuilder
import bhusal.ujjwal.androidproject.model.Product
import bhusal.ujjwal.androidproject.response.*
import okhttp3.MultipartBody

class ProductRepository : MyApiRequest() {
    private val productApi = ServiceBuilder.buildService(ProductApi::class.java)
    suspend fun addProduct(product:Product):AddProductResponse{
        return  apiRequest {
            productApi.addProduct(ServiceBuilder.token!!,product)
        }
    }
    suspend fun getProducts(): ProductResponse {
        return apiRequest {
            productApi.getAllProducts(ServiceBuilder.token!!)
        }
    }
    suspend fun getCheckout(): CheckoutResponse {
        return apiRequest {
            productApi.getAllCheckout(ServiceBuilder.token!!)
        }
    }
    suspend fun deleteProduct(product: Product): DeleteProductResponse {
        return apiRequest {
            productApi.deleteProduct(ServiceBuilder.token!!, product._id!!)
        }
    }
    suspend fun deleteCheckout(checkout: CheckoutResponse): DeleteCheckoutResponse {
        return apiRequest {
            productApi.deleteCheckout(ServiceBuilder.token!!)
        }
    }

    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            productApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }
}