package pe.com.ricindigus.roomapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddAlumnoViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private String dniAlumno;
    private AlumnoRepository alumnoRepository;

    public AddAlumnoViewModelFactory(String dniAlumno, AlumnoRepository alumnoRepository) {
        this.dniAlumno = dniAlumno;
        this.alumnoRepository = alumnoRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddAlumnoViewModel(alumnoRepository,dniAlumno);
    }
}
