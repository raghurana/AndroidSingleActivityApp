package com.raghurana.singleactivityapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public class MessageBox {

    private static Context context;

    public static void init(final Context ctx) {
        if (MessageBox.context == null) {
            MessageBox.context = ctx;
        }
    }

    public static void show(final String text) {
        Toast.makeText(MessageBox.context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showPositiveNegativeAlert
            (final Context activityContext,
             final String title,
             final String message,
             final String positiveButtonText,
             final DialogInterface.OnClickListener positiveClickListener,
             final String negativeButtonText,
             final DialogInterface.OnClickListener negativeClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, positiveClickListener);
        builder.setNegativeButton(negativeButtonText, negativeClickListener);
        builder.create().show();
    }

    public static void show(final Exception error) {
        Log.e("Exception : ", error.toString());
        MessageBox.show(error.getMessage());
    }
}
