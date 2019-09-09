package com.kotlin.kotlintest.LoginModule

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
/*import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView*/
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.kotlintest.R


import kotlinx.android.synthetic.main.fragment_legajo_list.*

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class LegajoFragment : Fragment(), LegajoListView {

    private lateinit var getListItems : () -> List<Any>
    private var viewType : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_legajo_list, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle("Lista de legajos")

        legajo_list.loadItemsCallback(getListItems,viewType, onLegajoClick as (Any?) -> Unit)


    }

    override fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int) {
       this.getListItems = getListItems
        this.viewType = viewType
    }



    override fun getViewContext(): Context? {
        return context
    }

     fun getFakeLegajos(): List<Legajo> {
         return List(30,{
             index ->
             Legajo(index)
         })
     }

    lateinit var onLegajoClick : (Legajo) -> Unit

    override fun setCallbacks(onLegajoClick: (Legajo) -> Unit) {
        this.onLegajoClick = onLegajoClick
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {

        // TODO: Customize parameter initialization
        fun newInstance(): LegajoFragment {
            val fragment = LegajoFragment()
            return fragment
        }
    }
}
