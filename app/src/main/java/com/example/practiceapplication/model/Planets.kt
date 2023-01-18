package com.example.practiceapplication.model

sealed class Planets(populate: Int){
    class venus:Planets(1000)
    class earth:Planets(1200)
    class mercury:Planets(1300)
}
