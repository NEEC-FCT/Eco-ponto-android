package info.androidhive.bottomnavigation.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import info.androidhive.bottomnavigation.R;


public class GlassChoosed extends Fragment {


    Context thiscontext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();

        View view = inflater.inflate(R.layout.choicevidro, null);

        final Button b1 = (Button) view.findViewById(R.id.b1);
        final Button b3 = (Button) view.findViewById(R.id.b3);
        final Button b5 = (Button) view.findViewById(R.id.b5);
        final Button b7 = (Button) view.findViewById(R.id.b7);
        final Button b2 = (Button) view.findViewById(R.id.b2);

        final Button b4 = (Button) view.findViewById(R.id.b4);
        final Button b6 = (Button) view.findViewById(R.id.b6);
        final Button b8 = (Button) view.findViewById(R.id.b8);
        final Button b10 = (Button) view.findViewById(R.id.b10);
        final Button b15 = (Button) view.findViewById(R.id.b15);
        final Button b17 = (Button) view.findViewById(R.id.b17);
        final Button b19 = (Button) view.findViewById(R.id.b19);
        final Button b13 = (Button) view.findViewById(R.id.b13);

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Indicações");
                builder.setMessage("Solicite à Direção o abate\n" +
                        "Após autorização para o abate solicite a recolha à DAT (pedido de assistência), através do email\n" +
                        "div.at.sg.helpdesk@fct.unl.pt");

                builder.setPositiveButton("Já tenho", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "div.at.sg.helpdesk@fct.unl.pt", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Eletrodomésticos, pilhas e baterias");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Corpo");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirLixo();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://bd2.fct.unl.pt/v7/downloads/dat/ef025_03_entrega_res_perigosos_0118.pdf";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirEcoponto();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirEcoponto();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirEcoponto();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirEcoponto();
            }
        });


        return view;

    }


    public void abrirLixo() {
        SharedPreferences settings = getActivity().getSharedPreferences("FRAG", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Abrir", "lixo");
        editor.commit();

        loadFragment(new MapaFragment());
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void abrirEcoponto() {
        SharedPreferences settings = getActivity().getSharedPreferences("FRAG", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Abrir", "Ecoponto");
        editor.commit();

        loadFragment(new MapaFragment());
    }


}