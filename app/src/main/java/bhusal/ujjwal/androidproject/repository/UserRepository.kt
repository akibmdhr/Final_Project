package bhusal.ujjwal.androidproject.repository


import bhusal.ujjwal.androidproject.api.MyApiRequest
import bhusal.ujjwal.androidproject.api.ServiceBuilder
import bhusal.ujjwal.androidproject.api.UserApi
import bhusal.ujjwal.androidproject.model.User
import bhusal.ujjwal.androidproject.response.LoginResponse

class UserRepository :
    MyApiRequest() {
    val myApi = ServiceBuilder.buildService(UserApi::class.java)
    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            myApi.registerUser(user)
        }
    }
    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }
}