package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import personal.jayhou.launchmodedemo.R;

public class Utils {
    public static final String TAG = "DemoTag-";

    public static final String ACTION_ACTIVITY1 = "com.jayhou.intent.action.Activity1";
    public static final String ACTION_ACTIVITY2 = "com.jayhou.intent.action.Activity2";
    public static final String ACTION_ACTIVITY3 = "com.jayhou.intent.action.Activity3";
    public static final String ACTION_SINGLE_INSTANCE = "com.jayhou.intent.action.ACTIVITY_SINGLE_INSTANCE";
    public static final String ACTION_SINGLE_TASK = "com.jayhou.intent.action.ACTIVITY_SINGLE_TASK";
    public static final String ACTION_SINGLE_TASK2 = "com.jayhou.intent.action.ACTIVITY_SINGLE_TASK2";
    public static final String ACTION_SINGLE_TOP = "com.jayhou.intent.action.ACTIVITY_SINGLE_TOP";
    public static final String ACTION_START_NEW_TASK = "com.jayhou.intent.action.START_NEW_TASK";

    public static void startActivity(Context context, String action, int flag) {
        Intent targetActivityIntent = new Intent(action);
        if(flag != 0) {
            targetActivityIntent.addFlags(flag);
        }
        context.startActivity(targetActivityIntent);
    }

    public static void LogAndTost(Context context, String tag ,String message) {
        Log.d(TAG + tag, message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void initAllWidget(Activity activity) {
        ((TextView)activity.findViewById(R.id.textview)).append(" " + activity);
        activity.findViewById(R.id.start_activity1).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_ACTIVITY1,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });
        activity.findViewById(R.id.start_activity2).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_ACTIVITY2,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });
        activity.findViewById(R.id.start_activity3).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_ACTIVITY3,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });
        activity.findViewById(R.id.start_single_top).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_SINGLE_TOP,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });
        activity.findViewById(R.id.start_single_task).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_SINGLE_TASK,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });

        activity.findViewById(R.id.start_single_task2).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_SINGLE_TASK2,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });

        activity.findViewById(R.id.start_single_instance).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_SINGLE_INSTANCE,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });

        activity.findViewById(R.id.start_activity_as_new_task).setOnClickListener((view) ->  {
            Utils.startActivity(activity, Utils.ACTION_START_NEW_TASK,
                    activity instanceof StartActivityAsNewTask?Intent.FLAG_ACTIVITY_NEW_TASK:0);
        });

    }
}
