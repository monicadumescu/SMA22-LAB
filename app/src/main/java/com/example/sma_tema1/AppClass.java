package com.example.sma_tema1;

import static com.example.sma_tema1.MainActivity6.getCurrentTime;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.widget.Toast;

import com.example.sma_tema1.model.Payment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AppClass extends Application {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        Network networkInfo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            networkInfo = connectivityManager.getActiveNetwork();
        }
        return networkInfo != null;
    }

    public static void updateLocalBackup(Context context, Payment payment, boolean toAdd)
    {
        String fileName = payment.timestamp;

        try {
            if(toAdd)
            {
                FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(payment.copy());
                os.close();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Cannot access local data.", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean hasLocalStorage(Context context)
    {
        return context.getFilesDir().listFiles().length>0;
    }

    public static List<Payment> loadFromLocalBackup(Context context, int month)
    {
        try {
            List<Payment> payments = new ArrayList<>();

            for (File file : context.getFilesDir().listFiles())
            {
                FileInputStream fis = context.openFileInput(file.getName());
                ObjectInputStream is = new ObjectInputStream(fis);
                Payment payment = (Payment) is.readObject();
                if(month ==  MainActivity6.Month.monthFromTimestamp(getCurrentTime())) {
                    payments.add(payment);
                }
                is.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
