package com.codewithandro.apitest

data class prodMainFile(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)