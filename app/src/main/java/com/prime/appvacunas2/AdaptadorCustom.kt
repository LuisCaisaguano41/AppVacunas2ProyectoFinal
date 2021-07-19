package com.prime.appvacunas2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorCustom(var contexto: Context, items: ArrayList<Paciente>): BaseAdapter() {

    //ALMACENAR LOS ELEMENTOS QUE SE VAN A MOSTRAR EN EL LISTVIEW
    var items: ArrayList<Paciente>? = null
    var copiaItems: ArrayList<Paciente>? = null

    init {
        this.items = ArrayList(items)
        this.copiaItems = items
    }

    override fun getCount(): Int {
        //REGRESAR EL NUMERO DE ELEMENTOS DE MI LISTA
        return this.items?.count()!!
    }

    fun addItem(item: Paciente){
        copiaItems?.add(item)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int){
        copiaItems?.removeAt(index)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, newItem:Paciente){
        copiaItems?.set(index, newItem)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun filtrar(str: String){
        items?.clear()
        if(str.isEmpty()){
            items = ArrayList(copiaItems)
            notifyDataSetChanged()
            return
        }
        var busqueda = str
        busqueda = busqueda.lowercase()
        for (item in copiaItems!!){
            val nombre = item.nombres.lowercase()
            if(nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder?=null
        var vista:View? = convertView
        if(vista == null){
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_paciente, null)
            viewHolder = ViewHolder(vista)
            vista.tag=viewHolder
        }else{
            viewHolder = vista.tag as? ViewHolder
        }
        val item = getItem(position) as Paciente
        //asignacion de valores a elementos graficos
        viewHolder?.nombres?.text = item.nombres + " " + item.apellidos
        viewHolder?.ciudad?.text = item.ciudad
        viewHolder?.foto?.setImageResource(item.foto)
        return vista!!
    }

    private class ViewHolder(vista:View){
        var nombres: TextView? = null
        var ciudad: TextView? = null
        var foto: ImageView? =null

        init {
            nombres= vista.findViewById(R.id.tvNombre)
            ciudad= vista.findViewById(R.id.tvCiudad)
            foto =vista.findViewById(R.id.imgFoto)
        }
    }
}