package com.example.zhaoy.eyepetizer.bean

class ResponseClasses {
    data class Categories(val id: Int, val name: String, val alias: String, val description: String,
                          val bgPicture: String, val bgColor: String, val headerImage: String,
                          val defaultAuthorId: String)
}