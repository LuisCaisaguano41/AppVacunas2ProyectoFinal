package com.prime.appvacunas2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class Nuevo : AppCompatActivity() {

    var fotoIndex: Int=0

    val fotos = arrayOf(R.drawable.foto1, R.drawable.foto2, R.drawable.foto3, R.drawable.foto4, R.drawable.foto5, R.drawable.foto6)
    var foto: ImageView? = null
    var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        foto = findViewById<ImageView>(R.id.imgFotoDetalle)
        foto?.setOnClickListener{
            seleccionarFoto()
        }

        //reconocer accion de nuevo y editar
        if(intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){

            android.R.id.home ->{
                finish()
                return true
            }

            R.id.CrearNuevo ->{
                //seleccionarFoto()
                //Aqui se va a crear un nuevo elemento de tipo paciente
                val nombres = findViewById<EditText>(R.id.tvNombre)
                val apellidos = findViewById<EditText>(R.id.tvApellido)
                val ciudad = findViewById<EditText>(R.id.tvCiudad)
                val edad = findViewById<EditText>(R.id.tvEdad)
                val peso = findViewById<EditText>(R.id.tvPeso)
                val altura = findViewById<EditText>(R.id.tvAltura)
                val direccion = findViewById<EditText>(R.id.tvDireccionNuevo)
                val telefono = findViewById<EditText>(R.id.tvTelefonoNuevo)
                val correo = findViewById<EditText>(R.id.tvCorreo)
                val descripcion = findViewById<EditText>(R.id.tvDescripcion)
                //Validacion de campos
                var campos = ArrayList<String>()
                campos.add(nombres.text.toString())
                campos.add(apellidos.text.toString())
                campos.add(ciudad.text.toString())
                campos.add(edad.text.toString())
                campos.add(peso.text.toString())
                campos.add(altura.text.toString())
                campos.add(direccion.text.toString())
                campos.add(telefono.text.toString())
                campos.add(correo.text.toString())
                campos.add(descripcion.text.toString())

                var flag=0
                for(campo in campos){
                    if(campo.isNullOrEmpty())
                        flag++
                }

                if(flag > 0){
                    Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
                }else{
                    if(index > -1){
                        MainActivity.actualizarPaciente(index, Paciente(campos.get(0), campos.get(1), campos.get(2), campos.get(3).toInt(),
                            campos.get(4).toFloat(), campos.get(5).toFloat(), campos.get(6), campos.get(7), campos.get(8),
                            obtenerFoto(fotoIndex), campos.get(9)))
                    }else{
                        MainActivity.agregarPaciente(Paciente(campos.get(0), campos.get(1), campos.get(2), campos.get(3).toInt(),
                            campos.get(4).toFloat(), campos.get(5).toFloat(), campos.get(6), campos.get(7), campos.get(8),
                            obtenerFoto(fotoIndex), campos.get(9)))
                    }
                    finish()
                    Log.d("Nro. Elemento", MainActivity.pacientes?.count().toString())
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun seleccionarFoto(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona la imagen de perfil")
        val adaptadorDialogo = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        adaptadorDialogo.add("Foto01")
        adaptadorDialogo.add("Foto02")
        adaptadorDialogo.add("Foto03")
        adaptadorDialogo.add("Foto04")
        adaptadorDialogo.add("Foto05")
        adaptadorDialogo.add("Foto06")

        builder.setAdapter(adaptadorDialogo){
            dialog, which ->
            fotoIndex = which
            foto?.setImageResource(obtenerFoto(fotoIndex))
        }

        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun obtenerFoto(index: Int): Int{
        return fotos.get(index)
    }

    fun rellenarDatos(index: Int){
        val paciente = MainActivity.obtenerPaciente(index)
        //var tvNombre = findViewById<EditText>(R.id.tvNombreDetalle)
        var tvNombres = findViewById<EditText>(R.id.tvNombre)
        var tvApellidos = findViewById<EditText>(R.id.tvApellido)
        var tvCiudad = findViewById<EditText>(R.id.tvCiudad)
        var tvEdad = findViewById<EditText>(R.id.tvEdad)
        var tvTelefono = findViewById<EditText>(R.id.tvTelefonoNuevo)
        var tvPeso = findViewById<EditText>(R.id.tvPeso)
        var tvAltura = findViewById<EditText>(R.id.tvAltura)
        var tvDescripcion = findViewById<EditText>(R.id.tvDescripcion)
        var tvDireccion = findViewById<EditText>(R.id.tvDireccionNuevo)
        var tvEmail = findViewById<EditText>(R.id.tvCorreo)
        var imgFoto = findViewById<ImageView>(R.id.imgFotoDetalle)



        //tvNombre.text = paciente.nombres + " " + paciente.apellidos
        tvNombres.setText(paciente.nombres, TextView.BufferType.EDITABLE)
        tvApellidos.setText(paciente.apellidos, TextView.BufferType.EDITABLE)
        tvCiudad.setText(paciente.ciudad, TextView.BufferType.EDITABLE)
        tvEdad.setText(paciente.edad.toString(), TextView.BufferType.EDITABLE)
        tvPeso.setText(paciente.peso.toString(), TextView.BufferType.EDITABLE)
        tvAltura.setText(paciente.altura.toString(), TextView.BufferType.EDITABLE)
        tvDescripcion.setText(paciente.descripcion.toString(), TextView.BufferType.EDITABLE)
        tvDireccion.setText(paciente.direccion.toString(), TextView.BufferType.EDITABLE)
        tvEmail.setText(paciente.correo.toString(), TextView.BufferType.EDITABLE)
        tvTelefono.setText(paciente.telefono.toString(), TextView.BufferType.EDITABLE)
        imgFoto.setImageResource(paciente.foto)

        var posicion = 0
        for(foto in fotos){
            if(paciente.foto== foto){
                fotoIndex = posicion
            }
            posicion++
        }
    }
}