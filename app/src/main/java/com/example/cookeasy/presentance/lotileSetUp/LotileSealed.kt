package com.example.cookeasy.presentance.lotileSetUp

import com.example.cookeasy.R

sealed class LotileSealed(
    val image : Int,
    val title : String,
    val description : String
){
    object FirstScreen : LotileSealed(
        image = R.raw.ee1,
        title = "Welcome to Cook Easy",
        description = "Your ultimate destination for effortless and delicious recipes that will transform your culinary journey. Discover step-by-step guides and helpful tips to make cooking a breeze"
    )
    object SecondScreen : LotileSealed(
        image = R.raw.animation_lnc4vnqh,
        title = "Easy search the food",
        description = "Your one-stop platform for quickly finding your favorite recipes, ingredients, and cooking inspiration with just a few clicks. Simplify meal planning and enjoy hassle-free cooking!"
    )
    object ThirdScreen : LotileSealed(
        image = R.raw.animation_lnekgluz,
        title = "Let's Start recipe",
        description = "Kickstart your cooking journey with simple and beginner-friendly recipes that guarantee a delicious meal every time. Perfect for novice chefs looking to create tasty dishes effortlessly"
    )
}
