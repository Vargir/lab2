package com.androiddev.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED);
    }
    //функция для отправки данных
    public void send_and_close_Click(View view) {

        EditText text = findViewById(R.id.editText);

        // Считываем значение поля
        String result = text.getText().toString();

        // Формируем "пустое" намерение
        Intent intent = new Intent();

        // Добавляем в намерение данные
        intent.putExtra("result", result);

        // Устанавливаем результат
        setResult(RESULT_OK, intent);

        // Закрываем Activity
        finish();
    }
}
