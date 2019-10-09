package pe.com.ricindigus.roomapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_alumnos")
public class Alumno {
    @PrimaryKey
    @NonNull
    private String dni;
    @ColumnInfo(name = "alu_name")
    private String nombres;
    @ColumnInfo(name = "alu_surname")
    private String apellidos;
    @ColumnInfo(name = "alu_age")
    private int edad;

    public Alumno(@NonNull String dni, String nombres, String apellidos, int edad) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
