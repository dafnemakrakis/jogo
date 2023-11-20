package com.example.jogotcc.NivelUm;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.jogotcc.DoubleClickListener;
import com.example.jogotcc.R;
import com.example.jogotcc.VideoButton;

public class FaseDoisNivelUmFragment extends Fragment {

    private VideoButton videoResposta2;
    private VideoButton videoResposta3;
    private ImageButton playButtonR2, pauseButtonR2, audioButtonR2;
    private ImageButton playButtonR, pauseButtonR, audioButtonR;
    private ImageButton voltarFasesButton;
    private ImageView feedbackImageView;
    private static final int DURATION_FEEDBACK_IMAGE = 3000;
    private boolean isAudioOn = true;
    private boolean isVideoResposta2Correct = false; // Defina conforme necessário
    private boolean isVideoResposta3Correct = true; // Defina conforme necessário

    public FaseDoisNivelUmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fase_dois_nivel_um, container, false);


        videoResposta3 = view.findViewById(R.id.videoResposta3);
        playButtonR2 = view.findViewById(R.id.playButtonR2);
        pauseButtonR2 = view.findViewById(R.id.pauseButtonR2);
        audioButtonR2 = view.findViewById(R.id.audioButtonR2);

        videoResposta2 = view.findViewById(R.id.videoResposta2);
        playButtonR = view.findViewById(R.id.playButtonR);
        pauseButtonR = view.findViewById(R.id.pauseButtonR);
        audioButtonR = view.findViewById(R.id.audioButtonR);

        voltarFasesButton = view.findViewById(R.id.voltarFasesButton);

        feedbackImageView = view.findViewById(R.id.feedbackImageView);

        // Configurar a URI do vídeo para resposta 2
        Uri videoRespostaUri2 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.doisum);
        videoResposta2.setVideoURI(videoRespostaUri2);

        // Configurar a URI do vídeo para resposta 3
        Uri videoRespostaUri3 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.tresum);
        videoResposta3.setVideoURI(videoRespostaUri3);

        setupFeedbackListeners(videoResposta2, isVideoResposta2Correct);
        setupFeedbackListeners(videoResposta3, isVideoResposta3Correct);

        setupVideoControls(videoResposta2, playButtonR2, pauseButtonR2, audioButtonR2);
        setupVideoControls(videoResposta3, playButtonR, pauseButtonR, audioButtonR);

        voltarFasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar de volta para a seleção de níveis
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigateUp();
            }
        });

        return view;
    }

    private void setupFeedbackListeners(VideoButton videoButton, boolean isCorrect) {
        videoButton.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick(View v) {
                showFeedbackImage(isCorrect);
            }
        });
    }

    private void setupVideoControls(VideoButton videoButton, ImageButton playButton, ImageButton pauseButton, ImageButton audioButton) {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoButton.isPlaying()) {
                    videoButton.start();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoButton.isPlaying()) {
                    videoButton.pause();
                }
            }
        });

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAudioOn) {
                    // Desligar o áudio
                    if (videoButton.isPlaying()) {
                        videoButton.pause(); // Pausa a reprodução
                    }
                } else {
                    // Ligar o áudio
                    if (!videoButton.isPlaying()) {
                        videoButton.start(); // Inicia a reprodução
                    }
                }
                isAudioOn = !isAudioOn; // Alternar entre ligado e desligado
            }
        });
    }

    private void showFeedbackImage(boolean isCorrect) {
        // Defina a imagem de feedback aqui
        int feedbackImageResource = isCorrect ? R.drawable.certo : R.drawable.errado;
        feedbackImageView.setImageResource(feedbackImageResource);
        feedbackImageView.setVisibility(View.VISIBLE);

        // Ocultar a imagem após um período específico
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                feedbackImageView.setVisibility(View.GONE);
            }
        }, DURATION_FEEDBACK_IMAGE);
    }
}
