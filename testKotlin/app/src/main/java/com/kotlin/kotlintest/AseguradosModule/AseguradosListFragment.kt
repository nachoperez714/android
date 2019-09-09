package com.kotlin.kotlintest.AseguradosModule

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.kotlintest.R
import kotlinx.android.synthetic.main.fragment_asegurados_list.*

/**
 * Created by Codika on 18/12/2018.
 */
class AseguradosListFragment : Fragment(), AseguradosListView {

    private lateinit var getListItems : () -> List<Any>
    private var viewType : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getOwnerActivity(): Activity {
        return (this!!.getActivity() as Activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_asegurados_list, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)


        aseguradosList.loadItemsCallback(getListItems, viewType, aseguradoPicked as (Any?) -> Unit)
    }

    override fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int) {
        this.getListItems = getListItems
        this.viewType = viewType
    }

    lateinit var aseguradoPicked : (AseguradoBrief) -> Unit

    override fun setCallbacks(aseguradoPicked: (AseguradoBrief) -> Unit) {
        this.aseguradoPicked = aseguradoPicked
    }





    companion object {

        // TODO: Customize parameter initialization
        fun newInstance(): AseguradosListFragment {
            val fragment = AseguradosListFragment()
            return fragment
        }
    }
}