package com.example.drillar.model

data class Drill(
    val name: String,
    val imageUrl: String,
    val description: String,
    val tips: String,
    val color: Long, // object color
    val shape: String
)

val drills = listOf(
    Drill(
        name = "Drill 1",
        imageUrl = "https://via.placeholder.com/150",
        description = "Place a red cube to try out basic AR.",
        tips = "Tap the floor once in good lighting.",
        color = 0xFFFF0000, // Red
        shape = "cube"
    ),
    Drill(
        name = "Drill 2",
        imageUrl = "https://via.placeholder.com/150",
        description = "Drop a green cone on the ground in AR.",
        tips = "Find a flat, bright spot and tap once.",
        color = 0xFF00FF00, // Green
        shape = "cone"
    ),
    Drill(
        name = "Drill 3",
        imageUrl = "https://via.placeholder.com/150",
        description = "Test your aim by placing a blue cube.",
        tips = "Try placing it at a specific spot and check how accurate it lands.",
        color = 0xFF0000FF, // Blue
        shape = "cube"
    )
)