package pe.com.ricindigus.roomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoHolder> {

    private List<Alumno> mListAlumnos;
    private Context context;
    private OnClick onClick;

    interface OnClick{
        void onClickItemAlumno(String id);
    }

    public AlumnoAdapter(Context context, OnClick onClick) {
        this.context = context;
        this.onClick = onClick;
    }

    public void setmListAlumnos(List<Alumno> mListAlumnos) {
        this.mListAlumnos = mListAlumnos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlumnoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno,parent,false);
        return new AlumnoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoHolder holder, int position) {
        final Alumno currentAlumno = mListAlumnos.get(position);
        holder.txtDni.setText(currentAlumno.getDni());
        holder.txtNombre.setText(currentAlumno.getNombres());
        holder.txtApellido.setText(currentAlumno.getApellidos());
        holder.txtEdad.setText(currentAlumno.getEdad()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickItemAlumno(currentAlumno.getDni());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListAlumnos == null)
            return 0;
        return mListAlumnos.size();
    }

    public static class AlumnoHolder extends RecyclerView.ViewHolder {
        TextView txtDni, txtNombre, txtApellido, txtEdad;
        public AlumnoHolder(@NonNull View itemView) {
            super(itemView);
            txtDni = itemView.findViewById(R.id.txtDni);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtApellido = itemView.findViewById(R.id.txtApellido);
            txtEdad = itemView.findViewById(R.id.txtEdad);
        }
    }
}
