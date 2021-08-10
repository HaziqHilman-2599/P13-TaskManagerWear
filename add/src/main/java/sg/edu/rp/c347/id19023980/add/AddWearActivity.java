package sg.edu.rp.c347.id19023980.add;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import sg.edu.rp.c347.id19023980.add.databinding.ActivityAddWearBinding;

public class AddWearActivity extends Activity {

    private TextView mTextView;
    private ActivityAddWearBinding binding;
    int reqCode = 12345;
    EditText etName, etDes, etTime;
    Button btnAdd, btnCancel;
   // DBHelper dbh = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddWearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDes);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                Integer time = Integer.parseInt(etTime.getText().toString());

                Log.d("TEST","TEST: "+des+" "+name);
//                dbh.insertTask(des, name);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, time);

//                Intent intent = new Intent(AddActivity.this,
//                        ScheduledBroadcastReceiver.class);

//                intent.putExtra("name", name);
//                intent.putExtra("description", des);
//                intent.putExtra("Next Notification time", time);

//                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                        AddActivity.this, reqCode,
//                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
//                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
//                        pendingIntent);

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}