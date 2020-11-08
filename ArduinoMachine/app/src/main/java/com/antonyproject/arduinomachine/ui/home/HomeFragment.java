package com.antonyproject.arduinomachine.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.antonyproject.arduinomachine.MainActivity;
import com.antonyproject.arduinomachine.R;
import com.antonyproject.arduinomachine.ui.gallery.GalleryFragment;

import java.io.OutputStream;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button forw = root.findViewById(R.id.forward);
        Button back = root.findViewById(R.id.back);
        Button righ = root.findViewById(R.id.right);
        Button left = root.findViewById(R.id.left);
        Button stop = root.findViewById(R.id.stop);
        forw.setOnTouchListener(tl);
        back.setOnTouchListener(tl);
        left.setOnTouchListener(tl);
        righ.setOnTouchListener(tl);
        stop.setOnTouchListener(tl);
        return root;
    }

    View.OnTouchListener tl = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            try {
                //OutputStream outStream = MainActivity.clientSocket.getOutputStream();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        switch (v.getId()) {
                            case R.id.stop:
                                MainActivity.outStream.write("2".getBytes());
                                Log.d("res", "2");
                                break;
                            case R.id.forward:
                                MainActivity.outStream.write("0".getBytes());
                                Log.d("res", "0");
                                break;
                            case R.id.left:
                                MainActivity.outStream.write("4".getBytes());
                                Log.d("res", "4");
                                break;
                            case R.id.right:
                                MainActivity.outStream.write("3".getBytes());
                                Log.d("res", "3");
                                break;
                            case R.id.back:
                                MainActivity.outStream.write("1".getBytes());
                                Log.d("res", "1");
                                break;
                            default:
                                MainActivity.outStream.write("1000".getBytes());
                                Log.d("res", "100");
                                break;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        MainActivity.outStream.write("2".getBytes());
                        Log.d("res", "stop");
                        break;
                }
            }catch(Exception eee){
                Log.d("debug",eee.getMessage());
            }
            return false;
        }
    };

    View.OnClickListener cl =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(MainActivity.clientSocket != null){
                try {
                    //OutputStream outStream = MainActivity.clientSocket.getOutputStream();
                    switch (v.getId()) {
                        case R.id.stop:
                            MainActivity.outStream.write("2".getBytes());
                            break;
                        case R.id.forward:
                            MainActivity.outStream.write("0".getBytes());
                            break;
                        case R.id.left:
                            MainActivity.outStream.write("4".getBytes());
                            break;
                        case R.id.right:
                            MainActivity.outStream.write("3".getBytes());
                            break;
                        case R.id.back:
                            MainActivity.outStream.write("1".getBytes());
                            break;
                        default:
                            MainActivity.outStream.write("1000".getBytes());
                            break;

                    }


                }catch (Exception ee){
                    Log.d("Exception BT",ee.getMessage());
                    Toast toast = Toast.makeText(getContext(),"5".getBytes().toString(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }else {
                Toast tost = Toast.makeText(getContext(),"Подключения нет зайдите в девайсы и установите подключение вручную",Toast.LENGTH_LONG);
                tost.show();
            }
        }
    };
}