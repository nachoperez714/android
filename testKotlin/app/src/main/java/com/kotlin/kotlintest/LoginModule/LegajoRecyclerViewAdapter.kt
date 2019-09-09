package com.kotlin.kotlintest.LoginModule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.kotlintest.R


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class LegajoRecyclerViewAdapter(private val mValues: List<Legajo>, private val mListener: (Legajo) -> Unit) : RecyclerView.Adapter<LegajoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.legajo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].numLegajo.toString()
       // holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
           mListener(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        //val mContentView: TextView
        var mItem: Legajo? = null

        init {
            mIdView = mView.findViewById<View>(R.id.id_item) as TextView
           // mContentView = mView.findViewById<View>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" //+ mContentView.text + "'"
        }
    }
}
