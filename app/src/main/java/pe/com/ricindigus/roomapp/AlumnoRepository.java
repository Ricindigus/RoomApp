package pe.com.ricindigus.roomapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlumnoRepository {
    private AlumnoDao alumnoDao;
    private LiveData<List<Alumno>> mAllAlumnos;

    public AlumnoRepository(Application application) {
        RoomAlumnoDatabase roomAlumnoDatabase = RoomAlumnoDatabase.getInstance(application);
        alumnoDao = roomAlumnoDatabase.alumnoDao();
        mAllAlumnos = alumnoDao.getAll();
    }

    public LiveData<List<Alumno>> getAllAlumnos(){
        return mAllAlumnos;
    }

    public LiveData<Alumno> getAlumnoByDni(String dni){
        return alumnoDao.getAlumno(dni);
    }

    public void insertAlumno(Alumno alumno){
        new InsertAsyncTask(alumnoDao).execute(alumno);
    }

    public void updateAlumno(Alumno alumno){
        new UpdateAsyncTask(alumnoDao).execute(alumno);
    }

    public void deleteAlumnoByDni(Alumno alumno){
        new DeleteAsyncTask(alumnoDao).execute(alumno);
    }


    private static class InsertAsyncTask extends AsyncTask<Alumno,Void,Void> {

        private AlumnoDao mAlumnoDao;

        public InsertAsyncTask(AlumnoDao mAlumnoDao) {
            this.mAlumnoDao = mAlumnoDao;
        }

        @Override
        protected Void doInBackground(Alumno... alumnos) {
            mAlumnoDao.insertAlumno(alumnos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Alumno,Void,Void> {

        private AlumnoDao mAlumnoDao;

        public UpdateAsyncTask(AlumnoDao mAlumnoDao) {
            this.mAlumnoDao = mAlumnoDao;
        }

        @Override
        protected Void doInBackground(Alumno... alumnos) {
            mAlumnoDao.updateAlumno(alumnos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Alumno,Void,Void> {

        private AlumnoDao mAlumnoDao;

        public DeleteAsyncTask(AlumnoDao mAlumnoDao) {
            this.mAlumnoDao = mAlumnoDao;
        }

        @Override
        protected Void doInBackground(Alumno... alumnos) {
            mAlumnoDao.deleteAlumno(alumnos[0]);
            return null;
        }
    }
}
