package pe.com.ricindigus.roomapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlumnoDao {
    @Insert
    void insertAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);

    @Query("select * from table_alumnos")
    LiveData<List<Alumno>> getAll();

    @Query("select * from table_alumnos where dni = :dni")
    LiveData<Alumno> getAlumno(String dni);


}
