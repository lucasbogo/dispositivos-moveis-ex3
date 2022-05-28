package com.example.exercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtText;
    private SeekBar skbTamanhoLetra;
    private CheckBox chkNegrito;
    private CheckBox chkItalico;
    private CheckBox chkMaiusculas;
    private RadioGroup rdgCorLetra;
    private RadioButton rdbVermelho;
    private RadioButton rdbAmarelo;
    private RadioButton rdbVerde;
    private ImageButton imageButton;
    private LinearLayout lblDados;
    private TextView txtText;
    int seekValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        skbTamanhoLetra = findViewById(R.id.skbTamanhoLetra);
        chkNegrito = findViewById(R.id.chkNegrito);
        chkItalico = findViewById(R.id.chkItalico);
        chkMaiusculas = findViewById(R.id.chkMaiusculas);
        rdgCorLetra = findViewById(R.id.rdgCorLetra);
        rdbVermelho = findViewById(R.id.rdbVermelho);
        rdbAmarelo = findViewById(R.id.rdbAmarelo);
        rdbVerde = findViewById(R.id.rdbVerde);
        imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(this);
        rdgCorLetra.setOnCheckedChangeListener(this::onCheckedChanged);
        chkNegrito.setOnCheckedChangeListener(this::onCheckedChanged);
        chkItalico.setOnCheckedChangeListener(this::onCheckedChanged);
        chkMaiusculas.setOnCheckedChangeListener(this::onCheckedChanged);

        lblDados = findViewById(R.id.lblDados);
        txtText = findViewById(R.id.txtText);


        // LÓGICA SEEKBAR | TAMANHO-LETRA
        skbTamanhoLetra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                String temp = "Processing...";
                txtText.setText(temp);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                txtText.setText(edtText.getText().toString());
                txtText.setTextSize(seekValue);
            }
        });

    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.imageButton) {

            lblDados.setVisibility(View.VISIBLE);
            txtText.setText(edtText.getText().toString());

        }
    }

    // LÓGICA CHECKBUTTONS | STYLE-CHANGE
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean checkedI = ((CheckBox) chkNegrito).isChecked();
        boolean checkedN = ((CheckBox) chkItalico).isChecked();
        boolean checkedM = ((CheckBox) chkMaiusculas).isChecked();
        int style = 0;

        //utilizaçao de operadores ternario para substituir os if-else.
        style|=(checkedN?Typeface.BOLD:Typeface.NORMAL);
        style|=(checkedI?Typeface.ITALIC:Typeface.NORMAL);
        Typeface typeface = Typeface.create(Typeface.SERIF, style);
        txtText.setTypeface(typeface);

        if (checkedM == true) {
            txtText.setAllCaps(true);
        }else if (checkedM == false) {
            txtText.setAllCaps(false);


        }
    }

    // LÓGICA RADIOBUTTONS | COLOR-CHANGE
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        boolean Vermelhocheckted = ((RadioButton) rdbVermelho).isChecked();
        boolean Amarelocheckted = ((RadioButton) rdbAmarelo).isChecked();
        boolean Verdecheckted = ((RadioButton) rdbVerde).isChecked();

        if (Vermelhocheckted){
            txtText.setTextColor(Color.RED);
        } else if (Amarelocheckted){
            txtText.setTextColor(Color.YELLOW);
        }else if (Verdecheckted){
            txtText.setTextColor(Color.GREEN);
        }
    }
}