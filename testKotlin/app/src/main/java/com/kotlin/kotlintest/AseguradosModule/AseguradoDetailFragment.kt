package com.kotlin.kotlintest.AseguradosModule

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.kotlintest.*

import kotlinx.android.synthetic.main.activity_asegurado_detalle.*
import kotlinx.android.synthetic.main.my_list.view.*


@Suppress("UNCHECKED_CAST")
class AseguradoDetailFragment : Fragment(), AseguradoDetailView {

    lateinit var aseguradoBrief: AseguradoBrief
    lateinit var navigatoToPolizaList: (AseguradoBrief) -> Unit

    override fun setCallbacks(navigatoToPolizaList: (AseguradoBrief) -> Unit) {
        this.navigatoToPolizaList = navigatoToPolizaList
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getOwnerActivity(): Activity {
        return (this!!.getActivity() as Activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_asegurado_detalle, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //aseguradoBrief = arguments!!["aseguradoBrief"] as AseguradoBrief
        aseguradoBrief = AseguradoBrief(arguments!!.getInt("id"), arguments!!.getString("name"), arguments!!.getString("dni"))

        val lista = listOf<Any>(KeyValue("Nombre", aseguradoBrief.name), KeyValue("Id", aseguradoBrief.id.toString()), KeyValue("Dni", aseguradoBrief.dni),Accion("Ver Polizas", navigatoToPolizaList as (Any) -> Unit, aseguradoBrief))
        asegurado_detalle.loadList(lista)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        activity!!.setTitle("Asegurados")
    }


    companion object {

        fun newInstance(a: AseguradoBrief): AseguradoDetailFragment {
            val fragment = AseguradoDetailFragment()
            val args = Bundle()
            //args.putSerializable("aseguradoBrief",a)
            args.putInt("id", a.id!!)
            args.putString("name", a.name)
            args.putString("dni", a.dni)
            fragment.arguments = args
            return fragment
        }
    }
// Required empty public constructor

    private fun MyList.loadList(list : List<Any>) {
        val myAdapter = object : GenericAdapter<Any>(list, {}) {
            override fun getLayoutId(position: Int, obj: Any): Int {
                when (obj) {
                    is KeyValue -> return R.layout.key_value_item_layout
                    is Accion -> return R.layout.accion_item_layout

                    else -> return 0
                }


            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return JavaViewHolderFactory.create(view, viewType)
            }
        }
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        recycler.adapter = myAdapter
        list_progress.visibility = View.GONE
    }
}