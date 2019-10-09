package pe.com.ricindigus.roomapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Alumno.class},version = 1)
public abstract class RoomAlumnoDatabase extends RoomDatabase {
    private static volatile RoomAlumnoDatabase instance;
    private static final String DATABASE_NAME = "colegio";

    public static RoomAlumnoDatabase getInstance(Context context) {
        if (instance == null){
            synchronized (RoomAlumnoDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RoomAlumnoDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

    public abstract AlumnoDao alumnoDao();
}
