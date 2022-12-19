package bhusal.ujjwal.androidproject.dao

import androidx.room.*
import bhusal.ujjwal.androidproject.model.Product

@Dao
interface ProductDAO {
    @Insert
    suspend fun insertProduct(product : Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : List<Product>

    @Update
    suspend fun updatePoduct(product : Product)

    @Delete
    suspend fun DeleteProduct(product : Product)
}