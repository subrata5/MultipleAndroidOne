package com.example.subrata.firstapplication.BlurImageDemo.WorkManagerDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.subrata.firstapplication.R;

public class WorkManagerExample extends AppCompatActivity {

    private Button btn_perform;
    private TextView txt_status;

    public static final String KEY_TASK_DEC = "key_task_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_example);

        btn_perform = findViewById(R.id.btn_perform);
        txt_status = findViewById(R.id.txt_status);

        //Data to be sent to the worker class
        final Data data = new Data.Builder()
                .putString(KEY_TASK_DEC, "Work data is sending") //Put as many by calling this
                .build();

        //constrains to be set like battery should not be low, network should be wifi only, etc.
        Constraints constraints = new Constraints.Builder()
                //.setRequiresCharging(true)
                .setRequiresBatteryNotLow(true)
                .build();


        //This is object from the abstract class to perform one time action
        //This calls the doWork() method from the WorkerClass, which sends notification
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(WorkerClass.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        //Button Click function
        btn_perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkManager.getInstance().enqueue(request);
            }
        });

        //
        //get the status of the task
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {


                        if(workInfo != null) {

                            //If the work is finished then we get the sent data from WorkerClass
                            if(workInfo.getState().isFinished())
                            {
                                Data data1 = workInfo.getOutputData();
                                String outputString = data1.getString(WorkerClass.KEY_TASK_OUTPUT);
                                txt_status.append(outputString + "\n");
                            }

                            String status = workInfo.getState().name();
                            txt_status.append(status + "\n");
                        }
                    }
                });

    }
}
