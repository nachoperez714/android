package com.kotlin.kotlintest.AseguradosModule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.kotlintest.R
import kotlinx.android.synthetic.main.activity_poliza_list.*

/**
 * Created by Codika on 20/12/2018.
 */
class PolizaListFragment : Fragment(), PolizasListView {

    private lateinit var getListItems : () -> List<Any>
    private var viewType : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_poliza_list, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        activity!!.title = "Polizas"
        polizaList.loadItemsCallback(getListItems, viewType,  {})
    }

    override fun loadItemsCallback(getListItems: () -> List<Any>, viewType: Int) {
        this.getListItems = getListItems
        this.viewType = viewType
    }

    var polizaPicked : ((Poliza) -> Unit)? = null

    override fun setCallbacks(polizaPicked: (Poliza) -> Unit) {
        // this.polizaPicked = polizaPicked
    }


    override fun onDetach() {
        super.onDetach()
        activity!!.title = "Detalle AseguradoBrief: " + arguments!!.getInt("id")
    }


    companion object {

        // TODO: Customize parameter initialization
        fun newInstance(a : AseguradoBrief): PolizaListFragment {
            val fragment = PolizaListFragment()
            val args = Bundle()
            args.putInt("id", a.id!!)
            args.putString("name", a.name)
            args.putString("dni", a.dni)
            fragment.arguments = args
            return fragment
        }
    }
}