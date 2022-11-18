package com.example.login_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_app.databinding.ActivitySigninBinding
import com.example.login_app.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener()
        {
            var intent= Intent(this,signUpActivity::class.java)
            startActivity(intent)
        }
        binding.signin.setOnClickListener()
        {
            var email=binding.email.text.toString()
            var pass=binding.password.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty())
            {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener()
                {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this,"successful",Toast.LENGTH_SHORT).show()
                        var intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this,"Wrong credentials",Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}