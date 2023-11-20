package com.example.jogotcc.NivelTres;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.jogotcc.R;

public class FaseDoisNivelTresFragment extends Fragment {

    private VideoView videoView;
    private ImageButton playButton, pauseButton, audioButton;
    private ImageButton resposta1Button, resposta2Button, resposta3Button, resposta4Button;
    private ImageButton voltarFasesButton;

    private boolean isAudioOn = true;

    private NavController navController;

    public FaseDoisNivelTresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fase_um_nivel_um, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        videoView = view.findViewById(R.id.videoView);
        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        audioButton = view.findViewById(R.id.audioButton);
        // resposta1Button = view.findViewById(R.id.resposta1Button);
        //  resposta2Button = view.findViewById(R.id.resposta2Button);
        //  resposta3Button = view.findViewById(R.id.resposta3Button);
        //  resposta4Button = view.findViewById(R.id.resposta4Button);
        voltarFasesButton = view.findViewById(R.id.voltarFasesButton);


        // Configurar a URI do vídeo
        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.teste);
        videoView.setVideoURI(videoUri);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
            }
        });

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAudioOn) {
                    // Desligar o áudio
                    if (videoView.isPlaying()) {
                        videoView.pause(); // Pausa a reprodução
                    }
                } else {
                    // Ligar o áudio
                    if (!videoView.isPlaying()) {
                        videoView.start(); // Inicia a reprodução
                    }
                }
                isAudioOn = !isAudioOn; // Alternar entre ligado e desligado
            }
        });

        //  resposta1Button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View v) {
        // Implementar lógica para verificar a resposta e fornecer feedback
        //    }
        //  });




        //botão de voltar
        voltarFasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar de volta para a seleção de níveis
                navController.navigateUp();
            }
        });

        return view;
    }
}

