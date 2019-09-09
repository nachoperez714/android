package com.kotlin.kotlintest.HomeModule

/**
 * Created by Codika on 22/11/2018.
 */

interface HomeView{
    fun loadItemsCallback(getListItems: () -> List<Any>, string_item_layout: Int)
    fun setCallbacks(homeOptionPicked: (String) -> Unit)
    //fun loadList(lista: List<Any>, string_item_layout: Int)
}
