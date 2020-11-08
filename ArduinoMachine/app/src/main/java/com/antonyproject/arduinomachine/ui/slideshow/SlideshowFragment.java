package com.antonyproject.arduinomachine.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Button action5 = root.findViewById(R.id.action5);
        Button action6 = root.findViewById(R.id.action6);
        Button action7 = root.findViewById(R.id.action7);
        Button action8 = root.findViewById(R.id.action8);
        Button action9= root.findViewById(R.id.action9);
        Button action10 = root.findViewById(R.id.action10);
        action5.setOnClickListener(cl);
        action6.setOnClickListener(cl);
        action7.setOnClickListener(cl);
        action8.setOnClickListener(cl);
        action9.setOnClickListener(cl);
        action10.setOnClickListener(cl);
        return root;
    }
    View.OnClickListener cl =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(MainActivity.clientSocket != null){
                try {
                    switch (v.getId()) {
                        case R.id.action5:
                            MainActivity.outStream.write("5".getBytes());
                            Toast r = Toast.makeText(getContext(),"5".getBytes().toString(),Toast.LENGTH_SHORT);
                            r.show();
                            break;
                        case R.id.action6:
                            MainActivity.outStream.write("6".getBytes());
                            break;
                        case R.id.action7:
                            MainActivity.outStream.write("7".getBytes());
                            break;
                        case R.id.action8:
                            MainActivity.outStream.write("8".getBytes());
                            break;
                        case R.id.action9:
                            MainActivity.outStream.write("9".getBytes());
                            break;
                        case R.id.action10:
                            MainActivity.outStream.write("10".getBytes());
                            break;
                        default:
                            MainActivity.outStream.write("1000".getBytes());
                            break;

                    }
                }catch (Exception ee){
                    Log.d("Exception BT",ee.getMessage());
                    Toast tost = Toast.makeText(getContext(),"Подключения нет зайдите в девайсы и установите подключение вручную",Toast.LENGTH_SHORT);
                    tost.show();
                }
            }else {
                Toast tost = Toast.makeText(getContext(),"Подключения нет зайдите в девайсы и установите подключение вручную",Toast.LENGTH_SHORT);
                tost.show();
            }
        }
    };
}