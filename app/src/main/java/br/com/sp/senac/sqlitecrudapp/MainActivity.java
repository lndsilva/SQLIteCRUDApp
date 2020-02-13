package br.com.sp.senac.sqlitecrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "dbempregados";

    TextView lblEmpregados;
    EditText txtNomeEmpregado, txtSalarioEmpregado;
    Spinner spnDepartamentos;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblEmpregados = findViewById(R.id.lblVerEmpregado);
        txtNomeEmpregado = findViewById(R.id.txtNomeEmpregado);
        txtSalarioEmpregado = findViewById(R.id.txtSalario);

        spnDepartamentos = findViewById(R.id.spnDepartamento);

        findViewById(R.id.btnAddEmpregado).setOnClickListener(this);
        lblEmpregados.setOnClickListener(this);

        db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Log.i("db", "Criou banco");
    }

    private boolean validarEntrada(String nome, String salario) {
        if (nome.isEmpty()) {
            txtNomeEmpregado.setError("Favor entrar com o nome");
            txtNomeEmpregado.requestFocus();
            return false;
        }
        if (salario.isEmpty() || Integer.parseInt(salario) <= 0) {
            txtSalarioEmpregado.setError("Favor entrar com o salário");
            txtSalarioEmpregado.requestFocus();
            return false;
        }
        return false;
    }

    private void adicionarEmpregado() {

        String nome = txtNomeEmpregado.getText().toString();
        String salario = txtSalarioEmpregado.getText().toString();
        String depto = spnDepartamentos.getSelectedItem().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String dataEntrada = simpleDateFormat.format(calendar.getTime());

        if (validarEntrada(nome, salario)) {
            String inserirEmpregado = "INSERT INTO employees \n" +
                    "(nome, departamento, dataEntrada, salario)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";
            db.execSQL(inserirEmpregado, new String[]{nome, depto, dataEntrada, salario});

            Toast.makeText(getApplicationContext(), "Empregado adicionado com sucesso!!!", Toast.LENGTH_SHORT).show();
        }

    }

    private void criarTabelaEmpregado() {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS tbEmpregados (\n" +
                        "id int NOT NULL CONSTRAINT empregado_pk PRIMARY KEY,\n" +
                        "nome varchar(200) NOT NULL,\n" +
                        "departamento varchar(200) NOT NULL,\n" +
                        "dataEntrada datetime NOT NULL,\n" +
                        "salario double NOT NULL\n" +
                        ");"
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddEmpregado:
                adicionarEmpregado();
                break;
            case R.id.lblVerEmpregado:
                startActivity(new Intent(getApplicationContext(), Empregado_Activity.class));
                break;
        }
    }


}
