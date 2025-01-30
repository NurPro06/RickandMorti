package com.example.rickandmorti

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64


object ImageConverter {

    fun encodeImageToBase64(imageUrl: String): String? {
        return try {
            val bitmap = fetchBitmapFromUrl(imageUrl)
            bitmap?.let { convertBitmapToBase64(it) }
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    private fun fetchBitmapFromUrl(urlString: String): Bitmap? {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}