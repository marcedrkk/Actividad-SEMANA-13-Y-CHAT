package com.example.semana13;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {
    EditText etNombre;
    EditText etNumero;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etNumero = findViewById(R.id.etNumero);
        btnGuardar = findViewById(R.id.btnIngresar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String numero = etNumero.getText().toString().trim();

            if (!nombre.isEmpty() && !numero.isEmpty()) {
                User nuevoUsuario = new User(nombre, numero);
                FirebaseDatabase.getInstance().getReference("usuarios")
                        .push().setValue(nuevoUsuario)
                        .addOnSuccessListener(aVoid -> Log.d("Registro", "Usuario guardado correctamente"))
                        .addOnFailureListener(e -> Log.e("Registro", "Error al guardar usuario", e));
                finish();
            } else {
                Log.w("Registro", "Campos vacíos. No se guardó.");
            }
        });
    }
}
