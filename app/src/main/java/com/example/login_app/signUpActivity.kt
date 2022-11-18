package com.example.login_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_app.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        binding.signup.setOnClickListener(){
            var email=binding.email.text.toString()
            var pass1=binding.password.text.toString()
            var pass2=binding.password1.text.toString()
            if(email.isNotEmpty() && pass1.isNotEmpty() && pass2.isNotEmpty())
            {
                if(pass1==pass2)
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener()
                    {
                        if(it.isSuccessful)
                        {
                            Toast.makeText(this,"successful",Toast.LENGTH_SHORT).show()
                            var intent=Intent(this,signInActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,"Can't sign up",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this,"Password not matching",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
        binding.signIn.setOnClickListener()
        {
            var intent=Intent(this,signInActivity::class.java)
            startActivity(intent)
        }

    }
}