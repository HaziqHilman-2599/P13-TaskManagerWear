package sg.edu.rp.c347.id19023980.p13_taskmanagerwear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUpdateActivity extends AppCompatActivity {
    EditText etTitle,etDesc,etID;
    Button btnUpdate,btnDelete,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);
        etID = findViewById(R.id.etIDUpDel);
        etTitle = findViewById(R.id.etNameUpDel);
        etDesc = findViewById(R.id.etDesUpDel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        DBHelper dbh = new DBHelper(DeleteUpdateActivity.this);
        Intent i = getIntent();
        etID.setText(i.getIntExtra("ID",0)+ "");
        etTitle.setText(i.getStringExtra("title"));
        etDesc.setText(i.getStringExtra("desc"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etID.getText().toString());
                String title = etTitle.getText().toString();
                String desc = etDesc.getText().toString();
                Task task = new Task(id,title,desc);

                dbh.updateTask(task);
                Toast.makeText(DeleteUpdateActivity.this, "The task has been successfully updated",
                        Toast.LENGTH_SHORT).show();
                dbh.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etID.getText().toString());
                dbh.deleteTask(id);
                Toast.makeText(DeleteUpdateActivity.this, "The task has been successfully deleted",
                        Toast.LENGTH_SHORT).show();
                dbh.close();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}