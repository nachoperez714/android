package com.kotlin.kotlintest

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.my_list.view.*

/**
 * Created by Codika on 10/12/2018.
 */
class MyList @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyle, defStyleRes) {
    

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.my_list, this, true)
    }

    fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int, onItemClick: (Any?) -> Unit) {
        getItemsTask {
            lista ->
            loadList(lista,viewType,onItemClick)
        }.execute(getListItems)
    }


    fun loadList(lista: List<Any>, viewType: Int, onItemClick: (Any?) -> Unit) {
        val myAdapter = object : GenericAdapter<Any>(lista,onItemClick) {
            override fun getLayoutId(position: Int, obj: Any): Int {
                return viewType
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return JavaViewHolderFactory.create(view,viewType)
            }
        }
        recycler.layoutManager= LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        recycler.adapter=myAdapter
        list_progress.visibility = View.GONE
    }

    inner class getItemsTask(private val onTaskCompleted: (List<Any>)->Unit) : AsyncTask<() -> List<Any>, Any, Any>() {
        override fun doInBackground(vararg params: (() -> List<Any>)?): Any? {
           // params.get(0)?.invoke()
            //Thread.sleep(3000)
            return params.get(0)?.invoke()
        }
        override fun onPostExecute(o: Any?) {
            // your stuff
            onTaskCompleted(o as List<Any>)
        }
    }
}