package com.androiddev.lab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CODE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //получаем и обрабатываем данные со второго Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Пользователь вышел из SecondActivity", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_OK) {

                if (data != null) {
                    String text = data.getStringExtra("result");
                    //Если результат совпадает с числом
                    if (text.equals("11.09")) {
                        TextView secret = findViewById(R.id.textView);

                        //выводим секретный текст
                        secret.setText("ПЕНТАГОН");
                        TextView result = findViewById(R.id.textView4);
                        result.setText("Минута молчания");

                        //Показываем изображение
                        ImageView masson = findViewById(R.id.imageView);
                        masson.setVisibility(View.VISIBLE); //фантазии хватило не на многое
                    } else if (text != null) { //в случае не вполнения предидущего условия, просто выводится текст, введённый в предыдущем активити
                        TextView result = findViewById(R.id.textView4);
                        result.setText(text);
                    }
                }
            }
        }
    }

    //Функция для явного намерения
    //Открытие второго Activity
    public void openActivity_Click(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);
    }


    //Функция для неявного намерения
    //Запуск таймера
    public void startTimer_Click(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
        //Задаём название
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "В чем смысл жизни?");
        //Задаём время(секунды)
        intent.putExtra(AlarmClock.EXTRA_LENGTH, 42);

        //Получаем PackageManager
        PackageManager manager = this.getPackageManager();
        //Получаем список обработчиков намерения
        List<ResolveInfo> list = manager.queryIntentActivities(intent, 0);

        if(list.size() > 0) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Неудалось запустить таймер", Toast.LENGTH_LONG).show();
        }

    }
}
