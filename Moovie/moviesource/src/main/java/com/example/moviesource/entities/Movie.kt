package com.example.moviesource.entities

data class Movie(
        val id: Int,
        val name: String,
        val averageVote: Double,
        val description: String,
        val posterUrl: String)
