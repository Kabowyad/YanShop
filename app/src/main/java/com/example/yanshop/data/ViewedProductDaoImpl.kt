package com.example.yanshop.data

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.yanshop.domain.ViewedProductDao
import com.example.yanshop.domain.model.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : ViewedProductDao {

    private var savedProductIds: List<Long>
        get() = sharedPreferences.getString(PRODUCT_TAG, null)
            ?.split(",")
            ?.mapNotNull { it.toLongOrNull() } ?: emptyList()
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, value.joinToString(","))
            }
        }

    override fun getAllProducts(): List<Long> = savedProductIds
    override fun retriev(): List<Product> {
        var connections: List<Product> = emptyList()
        val connectionsJSONString: String =
            sharedPreferences.getString("Product", null).toString()
        if (connectionsJSONString.equals("null")) return connections
        val type: Type = object : TypeToken<List<Product?>?>() {}.type
        connections = Gson().fromJson<List<Product>>(connectionsJSONString, type)
        return connections
    }

    override fun addProduct(productId: Long) {
        val productIds: List<Long> = savedProductIds
        val newProductIds = mutableListOf<Long>().apply {
            add(productId)
            addAll(productIds.filter { it != productId })
        }
        savedProductIds = newProductIds
    }

    fun save(product: Product) {
        val gson = Gson()
        sharedPreferences.edit().putString("Product",  gson.toJson(product)).apply()
    }

    fun retrieveAll(): List<Product> {
        val connectionsJSONString: String =
            sharedPreferences.getString("Product", null).toString()
        val type: Type = object : TypeToken<List<Product?>?>() {}.type
        val connections: List<Product> =
            Gson().fromJson<List<Product>>(connectionsJSONString, type)
        return connections
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
        private const val TEST = "TEST"
    }
}