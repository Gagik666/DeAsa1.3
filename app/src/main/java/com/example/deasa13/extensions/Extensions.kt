package com.example.deasa13.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

fun Fragment.openFragment(id: Int) {
    findNavController().navigate(id)
}

fun isRegistred(): Boolean {
    return FirebaseAuth.getInstance().currentUser != null
}

@RequiresApi(Build.VERSION_CODES.M)
fun AndroidViewModel.checkForInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

fun Fragment.dialog(text: String) {
    val bulder = AlertDialog.Builder(context)
    bulder.setTitle(text)
    bulder.setPositiveButton("Ok") { dialog, i -> }
    bulder.show()
}

fun Fragment.isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun Fragment.emailVerifiDialog(url: String) {
    val bulder = AlertDialog.Builder(context)
    bulder.setTitle("Please confirm the mail")
    bulder.setPositiveButton("Ok") { dialog, i ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).also { (it) }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        isEmailSent()
        ActivityCompat.recreate(context as Activity)
        dialog.dismiss()
    }
    bulder.show()
}

fun Fragment.isEmailSent(): Boolean {
    var emailSent = false
    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(context, "The mail has been sent", Toast.LENGTH_SHORT).show()
            emailSent = true
        } else {
            Toast.makeText(context, "The mail has not sent", Toast.LENGTH_SHORT).show()
            emailSent = false
        }
    }
    return emailSent
}


fun Fragment.getUrl(email: String): String {
    var link = ""
    val index = email.indexOf('@')
    for (i in index + 1 until email.length) {
        link += email[i]
    }
    return "https://$link"
}

fun dialog(context: Context, text: String) {
    val bulder = AlertDialog.Builder(context)
    bulder.setTitle(text)
    bulder.setPositiveButton("Ok") { dialog, i ->  }
    bulder.show()
}

@RequiresApi(Build.VERSION_CODES.M)
fun checkForInternet(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

