package com.prime.appvacunas2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Detalle : AppCompatActivity() {

    var index: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        index = intent.getStringExtra("ID")?.toInt()!!
        //Log.d("INDEX", index.toString())
        mapearDatos()
    }

    fun mapearDatos(){
        val paciente = MainActivity.obtenerPaciente(index!!)
        var tvNombre = findViewById<TextView>(R.id.tvNombreDetalle)
        var tvNombres = findViewById<TextView>(R.id.tvNombresDetalle)
        var tvApellidos = findViewById<TextView>(R.id.tvApellidosDetalle)
        var tvCiudad = findViewById<TextView>(R.id.tvCiudadDetalle)
        var tvEdad = findViewById<TextView>(R.id.tvEdadDetalle)
        var tvTelefono = findViewById<TextView>(R.id.txtTelefonoDetalle)
        var tvPeso = findViewById<TextView>(R.id.tvPesoDetalle)
        var tvAltura = findViewById<TextView>(R.id.tvAlturaDetalle)
        var tvDescripcion = findViewById<TextView>(R.id.tvDescripcionDetalle)
        var tvDireccion = findViewById<TextView>(R.id.tvDireccionDetalle)
        var tvEmail = findViewById<TextView>(R.id.tvCorreoDetalle)
        var imgFoto = findViewById<ImageView>(R.id.imgFotoDetalle)

        tvNombre.text = paciente.nombres + " " + paciente.apellidos
        tvNombres.text = paciente.nombres
        tvApellidos.text = paciente.apellidos
        tvCiudad.text = paciente.ciudad
        tvEdad.text = paciente.edad.toString() + "años"
        tvPeso.text = paciente.peso.toString() + "Kg"
        tvAltura.text = paciente.altura.toString()
        tvDescripcion.text = paciente.descripcion.toString()
        tvDireccion.text = paciente.direccion.toString()
        tvEmail.text = paciente.correo.toString()
        tvTelefono.text = paciente.telefono.toString()
        imgFoto.setImageResource(paciente.foto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
                android.R.id.home ->{
                    finish()
                    return true
                }

                R.id.Editar ->{
                    val intent = Intent(this, Nuevo::class.java)
                    intent.putExtra("ID", index.toString())
                    startActivity(intent)
                    return true
                }

                R.id.Eliminar -> {
                    MainActivity.eliminarPaciente(index)
                    finish()
                    return true
                }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        mapearDatos()
    }
}