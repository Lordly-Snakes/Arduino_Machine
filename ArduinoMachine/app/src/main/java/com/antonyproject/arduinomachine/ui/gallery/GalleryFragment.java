package com.antonyproject.arduinomachine.ui.gallery;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.antonyproject.arduinomachine.MainActivity;
import com.antonyproject.arduinomachine.R;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.UUID;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class GalleryFragment extends Fragment {

    BluetoothAdapter BTAdapter;

    // SPP UUID сервиса
    private final static int REQUEST_ENABLE_BT = 1;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Button btn = root.findViewById(R.id.scan_button);
        btn.setOnClickListener(cl);

        return root;

    }

    final View.OnClickListener cl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Connect();
        }
    };

    public void Connect(){
        if (BTAdapter.isEnabled()) {
            try {
                EditText t = getActivity().findViewById(R.id.editTextTextPersonName);
                String s = String.valueOf(t.getText());
                BluetoothDevice device = BTAdapter.getRemoteDevice(s);
                Method m = device.getClass().getMethod(
                        "createRfcommSocket", new Class[]{int.class});

                MainActivity.clientSocket = (BluetoothSocket) m.invoke(device, 1);
                MainActivity.clientSocket.connect();
                MainActivity.outStream = MainActivity.clientSocket.getOutputStream();
                TextView st = getActivity().findViewById(R.id.Stat);
                st.setText("Status: Connect");
            } catch (Exception ee) {
                Log.d("qwer", ee.getMessage());
                Toast tostr = Toast.makeText(getContext(), "Error mac-adress", Toast.LENGTH_LONG);
                tostr.show();
            }
        }
        else{
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            Toast tostr =Toast.makeText(getContext(),"Включите бт и поробуйте снова",Toast.LENGTH_LONG);
            tostr.show();
        }
    }

}

