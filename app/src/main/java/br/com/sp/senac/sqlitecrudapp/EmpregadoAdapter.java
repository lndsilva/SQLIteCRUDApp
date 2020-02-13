package br.com.sp.senac.sqlitecrudapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.zip.Inflater;

public class EmpregadoAdapter extends ArrayAdapter<Empregado> {

    Context context;
    int listaLayoutRes;
    List<Empregado> listEmpregados;
    SQLiteDatabase db;


    public EmpregadoAdapter(@NonNull Context context, int listaLayoutRes, List<Empregado> empregados, SQLiteDatabase db) {
        super(context, listaLayoutRes, empregados);

        this.context = context;
        this.listaLayoutRes = listaLayoutRes;
        this.listEmpregados = empregados;
        this.db = db;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listaLayoutRes, null);

        Empregado empregado = listEmpregados.get(position);


        TextView txtVerNome = view.findViewById(R.id.txtVerNome);
        TextView txtVerDepto = view.findViewById(R.id.txtVerDepto);
        TextView txtVerSalario = view.findViewById(R.id.txtVerSalario);
        TextView txtVerDataEntrada = view.findViewById(R.id.txtVerDataEntrada);


        txtVerNome.setText(empregado.getNome());
        txtVerDepto.setText(empregado.getDepto());
        txtVerSalario.setText(String.valueOf(empregado.getSalario()));
        txtVerDataEntrada.setText(empregado.getDataEntrada());


        Button btnApagar = view.findViewById(R.id.btnApagarEmpregado);
        Button btnEditar = view.findViewById(R.id.btnAlterarEmpregado);

        return view;
    }
}
