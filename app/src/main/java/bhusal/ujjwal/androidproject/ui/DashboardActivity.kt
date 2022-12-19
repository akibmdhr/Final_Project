package bhusal.ujjwal.androidproject.ui


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bhusal.ujjwal.androidproject.R
import bhusal.ujjwal.androidproject.api.ServiceBuilder


class DashboardActivity : AppCompatActivity() {
    private lateinit var btnAddproduct : ImageView
    private lateinit var btnDisplayproduct : ImageView
    private lateinit var btnLogout : ImageView
    private lateinit var leaveanim : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Toast.makeText(this, ServiceBuilder.token.toString(), Toast.LENGTH_SHORT).show()
        btnAddproduct = findViewById(R.id.btnAddproduct)
        btnDisplayproduct = findViewById(R.id.btnDisplayproduct)
        btnLogout = findViewById(R.id.btnLogout)
        leaveanim = findViewById(R.id.leaveanim)


        btnAddproduct.setOnClickListener{
            startActivity(Intent(this, AddProduct::class.java))
        }

        btnLogout.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        leaveanim.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        btnDisplayproduct.setOnClickListener{
            startActivity(Intent(this, DisplayProductActivity::class.java))
        }
    }
}