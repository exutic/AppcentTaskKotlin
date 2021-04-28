package com.example.appcenttaskkotlin.appcentModel

data class Post1 (
    val status: String,
    val totalResults:Int,
    val articles:List<Post2Articles>
)