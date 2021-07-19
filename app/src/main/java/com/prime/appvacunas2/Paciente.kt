package com.prime.appvacunas2

class Paciente(nombres:String,
               apellidos: String,
               ciudad: String,
               edad:Int,
               peso:Float,
               altura:Float,
               direccion: String,
               telefono: String,
               correo: String, foto:Int,
               descripcion: String) {

    var nombres: String =""
    var apellidos: String =""
    var ciudad: String=""
    var edad: Int=0
    var peso: Float=0.0F
    var altura: Float=0.0F
    var direccion: String=""
    var telefono: String=""
    var correo: String=""
    var foto: Int=0
    var descripcion: String=""

    init {
        this.nombres = nombres
        this.apellidos = apellidos
        this.ciudad = ciudad
        this.edad = edad
        this.peso = peso
        this.altura = altura
        this.direccion = direccion
        this.telefono = telefono
        this.correo = correo
        this.foto = foto
        this.descripcion = descripcion
    }


}