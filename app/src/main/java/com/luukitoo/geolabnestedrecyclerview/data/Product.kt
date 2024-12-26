package com.luukitoo.geolabnestedrecyclerview.data

import java.util.UUID
import kotlin.random.Random

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String
) {

    companion object {
        fun generateList(): List<Product> {
            return List(5) {
                Product(
                    id = UUID.randomUUID().toString(),
                    name = "Product $it",
                    description = "Description $it",
                    price = Random.nextInt(from = 100, until = 500),
                    imageUrl = "https://picsum.photos/200/300?random=$it"
                )
            }
        }
    }
}
