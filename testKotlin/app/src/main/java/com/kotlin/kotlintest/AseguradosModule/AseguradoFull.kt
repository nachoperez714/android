package com.kotlin.kotlintest.AseguradosModule

/**
 * Created by Codika on 26/12/2018.
 */
class AseguradoFull(val id: Int, val name: String, val dni: String){


    constructor(adress : String,birthDate : String,pictureUrl : String) : this(id, name, dni)
}