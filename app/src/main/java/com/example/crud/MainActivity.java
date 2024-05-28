package com.example.crud;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail;
    private Button buttonAdd, buttonUpdate, buttonDelete;
    private ListView listViewStudents;
    private List<MyApplication.Student> studentList;
    private MyApplication.Student.FirebaseDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        listViewStudents = findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();
        databaseHelper = new MyApplication.Student.FirebaseDatabaseHelper();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });

        loadStudents();
    }

    private void addStudent() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String id = databaseHelper.getReference().push().getKey();
        MyApplication.Student student = new MyApplication.Student(id, name, email);
        databaseHelper.addStudent(student);
    }

    private void updateStudent() {
        String String = null;
        String id = // get selected student id
                null String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        MyApplication.Student student = new MyApplication.Student(id, name, email);
        databaseHelper.updateStudent(id, student);
    }

    private void deleteStudent() {
        String id = // get selected student id
                databaseHelper.deleteStudent(id);
    }

        private void loadStudents() {
            ValueEventListener valueEventListener = databaseHelper.getReference().addValueEventListener(new ValueEventListener() {
                public <Student extends MyApplication.Student> void onDataChange(DataSnapshot dataSnapshot) {
                    studentList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Student student = postSnapshot.getValue(Student.class);
                        studentList.add((MyApplication.Student) student);
                    }
                    // Update ListView adapter here
                }

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
