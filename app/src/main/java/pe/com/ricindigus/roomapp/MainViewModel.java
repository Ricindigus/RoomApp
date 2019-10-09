package pe.com.ricindigus.roomapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Alumno>> mAlumnos;
    private AlumnoRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AlumnoRepository(application);
        mAlumnos = mRepository.getAllAlumnos();
    }

    public LiveData<List<Alumno>> getmAlumnos() {
        return mAlumnos;
    }
}
