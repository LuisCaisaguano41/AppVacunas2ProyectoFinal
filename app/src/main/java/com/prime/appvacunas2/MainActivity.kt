package com.prime.appvacunas2

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    var lista: ListView? = null
    var grid : GridView? = null
    var viewSwitcher: ViewSwitcher? = null


    companion object {
        var pacientes: ArrayList<Paciente>? = null
        var adaptador: AdaptadorCustom? = null
        var adaptadorGrid: AdaptadorCustomGrid? = null

        fun agregarPaciente(paciente: Paciente) {
            adaptador?.addItem(paciente)
        }

        fun obtenerPaciente(index: Int): Paciente {
            return adaptador?.getItem(index) as Paciente
        }

        fun eliminarPaciente(index: Int) {
            adaptador?.removeItem(index)
        }

        fun actualizarPaciente(index: Int, nuevoPaciente: Paciente) {
            adaptador?.updateItem(index, nuevoPaciente)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        pacientes = ArrayList()
        pacientes!!.add(
            Paciente(
                "Luis",
                "Caisaguano",
                "LOJA",
                30,
                70.0F,
                1.70F,
                "LOJA",
                "0995351473",
                "lcaisaguano@hotmail.com",
                R.drawable.foto2,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Jose",
                "Nieto",
                "QUITO",
                27,
                70.0F,
                1.70F,
                "QUITO",
                "0995351473",
                "jnieto@hotmail.com",
                R.drawable.foto3,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Andres",
                "Hernandez",
                "CUENCA",
                40,
                70.0F,
                1.70F,
                "CUENCA",
                "0995351473",
                "ahernandez@hotmail.com",
                R.drawable.foto3,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Carla",
                "Solorzano",
                "MACHALA",
                37,
                70.0F,
                1.70F,
                "MACHALA",
                "0995351473",
                "csolorzano@hotmail.com",
                R.drawable.foto6,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Daniela",
                "Benitez",
                "GUAYAQIUIL",
                27,
                70.0F,
                1.70F,
                "GUAYAQUIL",
                "0995351473",
                "dbenitez@hotmail.com",
                R.drawable.foto6,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Sebastian",
                "Silva",
                "SANTA ELENA",
                35,
                70.0F,
                1.70F,
                "SANTA ELENA",
                "0995351473",
                "ssilva@hotmail.com",
                R.drawable.foto1,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Pablo",
                "Flores",
                "QUITO",
                27,
                70.0F,
                1.70F,
                "QUITO",
                "0995351473",
                "pflores@hotmail.com",
                R.drawable.foto2,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Lorena",
                "Constante",
                "AMBATO",
                34,
                70.0F,
                1.70F,
                "AMBATO",
                "0995351473",
                "lconstante@hotmail.com",
                R.drawable.foto6,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        pacientes!!.add(
            Paciente(
                "Eduardo",
                "Perez",
                "LOJA",
                65,
                70.0F,
                1.70F,
                "LOJA",
                "0995351473",
                "eperez@hotmail.com",
                R.drawable.foto2,
                "EL PACIENTE SE ENCUENTRA VACUNADO"
            )
        )
        lista = findViewById<ListView>(R.id.lista)
        grid = findViewById<GridView>(R.id.grid)
        adaptador = AdaptadorCustom(this, pacientes!!)
        adaptadorGrid = AdaptadorCustomGrid(this, pacientes!!)
        viewSwitcher = findViewById(R.id.viewSwitcher)
        lista?.adapter = adaptador
        grid?.adapter = adaptadorGrid
        lista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as SearchView
        val itemSwitch = menu?.findItem(R.id.switchView)
        itemSwitch?.setActionView(R.layout.switch_item)
        val switchView = itemSwitch?.actionView?.findViewById<Switch>(R.id.sCambiaVista)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar contacto..."
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            //preparamos los datos
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //filtrar
                adaptador?.filtrar(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //filtrar
                return true
            }
        })

        switchView?.setOnCheckedChangeListener { buttonView, isChecked ->
            viewSwitcher?.showNext()
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.itNuevo -> {
                val intent = Intent(this, Nuevo::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onResume() {
        super.onResume()
        adaptador?.notifyDataSetChanged()
    }

}