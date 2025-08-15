package com.example.testapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.databinding.ActivitySignUpBinding
import com.example.testapp.utils.CyrillicInputFilter
import com.example.testapp.utils.initEmailInputValidation
import com.example.testapp.utils.isValidEmail
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {
    private var _binding: ActivitySignUpBinding? = null
    private val binding: ActivitySignUpBinding
        get() = _binding ?: throw RuntimeException("ActivitySignUpBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            emailEtv.initEmailInputValidation()
            vkButton.setOnClickListener {
                openUrl(VK_URL)
            }
            okButton.setOnClickListener {
                openUrl(OK_URL)
            }
            registerBtn.setOnClickListener {
                signUp()
            }
        }
    }

    private fun signUp() {
        binding.apply {
            if (emailEtv.text.toString().isValidEmail()
                && passwordEtv.text.toString().length >= 8
            ) {
                openMainRootFragment()
            } else showToast(resources.getString(R.string.login_error_text))
        }
    }

    private fun openMainRootFragment() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val VK_URL = "https://vk.com/"
        private const val OK_URL = "https://ok.ru/"

        val l = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    }
}