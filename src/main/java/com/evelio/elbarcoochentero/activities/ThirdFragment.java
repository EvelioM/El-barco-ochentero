package com.evelio.elbarcoochentero.activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.evelio.elbarcoochentero.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ThirdFragment extends Fragment {
    final int READ_CALL_LOG = 10;
    final int CONTACTS_PERMISSIONS = 5;
    Activity activity;
    TextView textView;


    public ThirdFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_three, container, false);


        textView = (TextView) view.findViewById(R.id.textView2);
        Button botoncontactos = (Button) view.findViewById(R.id.buttoncontactos);
        Button btnsave = (Button) view.findViewById(R.id.save);
        Button btnload = (Button) view.findViewById(R.id.load);


        botoncontactos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Context applicationContext = MainLogged.getContextOfApplication();
                applicationContext.getContentResolver();

                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSIONS);

                Uri contactsuri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                textView.setText("");

                String[] projection =
                        {
                                ContactsContract.CommonDataKinds.Phone._ID,
                                ContactsContract.CommonDataKinds.Phone.NUMBER,
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                        };


                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

                    String tipoLlamada = "";
                    String telefono;
                    telefono = getPhone(contactsuri);

                } else {
                    Toast.makeText(applicationContext, "No tenemos permisos para leer los contactos", Toast.LENGTH_LONG).show();
                }
            }


            public String getName(Uri uri) {
                Context applicationContext = MainLogged.getContextOfApplication();
                applicationContext.getContentResolver();
                /*valor a devolver*/
                String name = null;
                /*Obtener referencia del Content Resolver*/
                ContentResolver conres = applicationContext.getContentResolver();
                /*Consultar el nombre del contacto*/
                Cursor c = conres.query(uri, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if (c.moveToFirst()) {
                    name = c.getString(0);
                }
                /*y cerrar el cursor*/
                c.close();
                return name;
            }

            private String getPhone(Uri uri) {

                Context applicationContext = MainLogged.getContextOfApplication();
                applicationContext.getContentResolver();
                /*tems para el id y el tlfno*/
                String id = null;
                String phone = null;
                String name = null;
                /*Primera consulta*/
                //Obtener el _ID

                String selectionArgs = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " =? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + "= " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
                ContentResolver conres = applicationContext.getContentResolver();
                Cursor c = conres.query(uri, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if (c.moveToFirst()) {
                    int i = 0;
                    do {
                        name = c.getString(0);

                        Cursor phoneCursor = applicationContext.getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, selectionArgs, new String[]{name}, null);
                        if (phoneCursor.moveToFirst()) {
                            phone = phoneCursor.getString(0);
                            textView.append(name + phone + "\n");
                        }
                        phoneCursor.close();
                        i++;
                    } while (c.moveToNext() && i <= 40);
                }
                c.close();


                return phone;
            }


        });


        btnsave.setOnClickListener(new View.OnClickListener() {

            /*@Override
            public void onClick(View view) {



            }*/
            @Override
            public void onClick(View v){
                Context applicationContext = MainLogged.getContextOfApplication();
                applicationContext.getContentResolver();
                String estado;
                boolean memok=false;
                estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.MEDIA_MOUNTED)){
                    memok = true;
                }

                //textView.setText(estado.toString()+": "+memok);
                if (memok==true){
                    try{
                        File ruta = new File(activity.getExternalFilesDir(null),"ficheroexterno.txt");
                        Toast toast1 =Toast.makeText(activity,ruta.toString(), Toast.LENGTH_SHORT);
                        toast1.show();
                        OutputStreamWriter salida = new OutputStreamWriter(new FileOutputStream(ruta));
                        //salida.append(textoescrito.getText().toString());

                        int start = textView.getLayout().getLineStart(0);
                        int end = textView.getLayout().getLineEnd(textView.getLineCount() - 1);
                        String displayed = textView.getText().toString().substring(start, end);
                        salida.write(displayed);
                        //salida.write("Otra vez con ficheros, esta vez externos");
                        salida.close();
                    }catch (Exception ex){
                        Log.e("Ficherosexternos", "Error al escribir fichero en memoria externa");
                    }
                }
            }
        });

        btnload.setOnClickListener(new View.OnClickListener() {

            /*@Override
            public void onClick(View view) {



            }*/

            @Override
            public void onClick(View v) {
               
                try{
                    File ruta = new File(activity.getExternalFilesDir(null),"ficheroexterno.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
                    String cadena = br.readLine();
                    br.close();
                    Toast toast1 =Toast.makeText(activity,cadena.toString(), Toast.LENGTH_SHORT);
                    textView.setText(cadena.toString());
                    toast1.show();
                }catch (Exception ex){
                    Log.e("Ficherosexternos", "Error al leer fichero en memoria externa");
                }

            }
        });


        return view;


    }
}
