package pe.com.ricindigus.roomapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AddAlumnoViewModel extends ViewModel {
    private LiveData<Alumno> mAlumno;

    public AddAlumnoViewModel(AlumnoRepository alumnoRepository, String dni) {
        mAlumno = alumnoRepository.getAlumnoByDni(dni);
    }

    public LiveData<Alumno> getmAlumno() {
        return mAlumno;
    }
}
