package pe.com.ricindigus.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAlumnoActivity extends AppCompatActivity {

    EditText edtDni,edtNombres,edtApellidos, edtEdad;
    Button btnGuardar, btnEliminar;
    AlumnoRepository alumnoRepository;
    AddAlumnoViewModel addAlumnoViewModel;

    final static String KEY_DNI = "dni";
    final static String DEFAULT_VALUE = "empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumno);

        edtDni = findViewById(R.id.edtDni);
        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtEdad = findViewById(R.id.edtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnEliminar = findViewById(R.id.btnEliminar);

        alumnoRepository = new AlumnoRepository(getApplication());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnGuardar.getText().toString().toUpperCase().equals("GUARDAR")){
                    guardarAlumno();
                }else{
                    actualizarAlumno();
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alumnoRepository.deleteAlumnoByDni(getCurrentAlumno());
                finish();
            }
        });


        if (getIntent().getExtras() != null){
            String currentDni = getIntent().getExtras().getString(KEY_DNI,DEFAULT_VALUE);
            if (!currentDni.equals(DEFAULT_VALUE)){
                btnGuardar.setText("Actualizar");
                btnEliminar.setVisibility(View.VISIBLE);
                AddAlumnoViewModelFactory factory = new AddAlumnoViewModelFactory(currentDni,alumnoRepository);
                addAlumnoViewModel = ViewModelProviders.of(this,factory).get(AddAlumnoViewModel.class);
                final LiveData<Alumno> mAlumno = addAlumnoViewModel.getmAlumno();
                mAlumno.observe(this, new Observer<Alumno>() {
                    @Override
                    public void onChanged(Alumno alumno) {
                        mAlumno.removeObserver(this);
                        displayAlumno(alumno);
                    }
                });
            }
        }
    }

    private Alumno getCurrentAlumno() {
        return new Alumno(
                edtDni.getText().toString(),
                edtNombres.getText().toString(),
                edtApellidos.getText().toString(),
                Integer.parseInt(edtEdad.getText().toString())
        );
    }

    private void actualizarAlumno() {
        alumnoRepository.updateAlumno(getCurrentAlumno());
        finish();
    }

    private void guardarAlumno() {
        alumnoRepository.insertAlumno(getCurrentAlumno());
        finish();
    }

    private void displayAlumno(Alumno alumno) {
        edtDni.setText(alumno.getDni());
        edtNombres.setText(alumno.getNombres());
        edtApellidos.setText(alumno.getApellidos());
        edtEdad.setText(alumno.getEdad()+"");
    }
}
