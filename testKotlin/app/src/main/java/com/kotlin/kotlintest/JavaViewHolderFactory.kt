package com.kotlin.kotlintest

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kotlin.kotlintest.AseguradosModule.AseguradoBrief
import com.kotlin.kotlintest.AseguradosModule.Poliza
import com.kotlin.kotlintest.LoginModule.Legajo
import kotlinx.android.synthetic.main.accion_item_layout.view.*
import kotlinx.android.synthetic.main.asegurado_item_layout.view.*
import kotlinx.android.synthetic.main.int_item_layout.view.*
import kotlinx.android.synthetic.main.key_value_cell.view.*
import kotlinx.android.synthetic.main.key_value_item_layout.view.*
import kotlinx.android.synthetic.main.legajo_list_item.view.*
import kotlinx.android.synthetic.main.poliza_item_layout.view.*
import kotlinx.android.synthetic.main.string_item_layout.view.*


object JavaViewHolderFactory {

    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            //MyViews
            R.layout.string_item_layout -> StringViewHolder(view)
            R.layout.int_item_layout -> IntViewHolder(view)
            R.layout.legajo_list_item -> LegajoViewHolder(view)
            R.layout.asegurado_item_layout-> AseguradoViewHolder(view)
            R.layout.poliza_item_layout-> PolizaViewHolder(view)
            R.layout.key_value_item_layout -> KeyValueViewHolder(view)
            R.layout.accion_item_layout -> AccionViewHolder(view)
            else -> {
                StringViewHolder(view)
            }
        }
    }

/*    class Car(var name: String, var color: Int)

    class Bus(var name: String, var color: Int)*/
    //Create my views
    class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<String> {

        override fun bind(string: String) {
           itemView.string.text = string
        }
    }

    class KeyValueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<KeyValue> {

        override fun bind(keyValue: KeyValue) {
            itemView.keyValue.key.text = keyValue.key
            itemView.keyValue.value.text = keyValue.value
        }
    }

    class AccionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Accion> {

        override fun bind(accion: Accion) {
            itemView.btn_accion.text = accion.title
            itemView.btn_accion.setOnClickListener { accion.accion(accion.parametro) }
        }
    }

    class PolizaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Poliza> {

        override fun bind(p: Poliza) {
            itemView.poliza_name.text = p.nombrePoliza
        }
    }

    class LegajoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Legajo> {

        var item: Legajo? = null

        override fun bind(legajo: Legajo) {
            itemView.id_item.text = legajo.numLegajo.toString()
        }
    }

    class AseguradoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<AseguradoBrief> {

        override fun bind(aseguradoBrief: AseguradoBrief) {
            itemView.id_asegurado.text = aseguradoBrief.id.toString()
            itemView.name_asegurado.text = aseguradoBrief.name
            itemView.dni_asegurado.text = aseguradoBrief.dni
        }
    }

    class IntViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Int> {

        override fun bind(int: Int) {
            itemView.numero.text = int.toString()
        }
    }
}

