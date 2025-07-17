package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNum;
    private TextView textViewResult;
    private Button buttonCalcular;
    private PrimeCalculator primeCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        editTextNum = findViewById(R.id.TextInputEditTextNum);
        textViewResult = findViewById(R.id.textViewExplain);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        // Inicializar la clase PrimeCalculator
        primeCalculator = new PrimeCalculator();

        // Configurar el botón de cálculo
        buttonCalcular.setOnClickListener(v -> {
            String inputText = editTextNum.getText().toString().trim();
            if (inputText.isEmpty()) {
                textViewResult.setText("Por favor, introduce un número.");
                return;
            }

            try {
                int position = Integer.parseInt(inputText);
                if (position <= 0) {
                    textViewResult.setText("Por favor, introduce un número positivo mayor que 0.");
                    return;
                }

                // Llamar al método correcto en PrimeCalculator
                int nthPrime = primeCalculator.calculateNthPrime(position);
                textViewResult.setText("El primo número " + position + " es el " + nthPrime);
            } catch (NumberFormatException e) {
                textViewResult.setText("Por favor, introduce un número válido.");
            }
        });
    }
}
