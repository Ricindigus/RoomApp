package pe.com.ricindigus.roomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    AlumnoAdapter alumnoAdapter;
    AlumnoRepository alumnoRepository;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerAlumno);
        floatingActionButton = findViewById(R.id.fabAddAlumno);
        alumnoRepository = new AlumnoRepository(getApplication());
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        alumnoAdapter = new AlumnoAdapter(this, new AlumnoAdapter.OnClick() {
            @Override
            public void onClickItemAlumno(String id) {
                startActivity(new Intent(MainActivity.this, AddAlumnoActivity.class)
                        .putExtra(AddAlumnoActivity.KEY_DNI, id));
            }
        });
        recyclerView.setAdapter(alumnoAdapter);


        mainViewModel.getmAlumnos().observe(this, new Observer<List<Alumno>>() {
            @Override
            public void onChanged(List<Alumno> alumnos) {
                alumnoAdapter.setmListAlumnos(alumnos);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddAlumnoActivity.class));
            }
        });
    }
}
