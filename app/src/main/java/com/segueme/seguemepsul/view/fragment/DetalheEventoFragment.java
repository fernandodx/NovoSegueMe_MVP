package com.segueme.seguemepsul.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.segueme.seguemepsul.R;
import com.segueme.seguemepsul.model.pojo.Evento;
import com.segueme.seguemepsul.util.Util;

/**
 * Created by FernandoDias on 20/01/17.
 */

public class DetalheEventoFragment extends BaseFragment {


    private ImageView eventoImageView;
    private TextView localEventoTextView;
    private TextView dataEventoTextView;
    private TextView horaEventoTextView;
    private TextView descricacaoTextView;
    private TextView obsTextView;

    private Evento evento;



    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = layoutInflater.inflate(R.layout.detalhe_evento_fragment, viewGroup, false);

        eventoImageView = (ImageView) view.findViewById(R.id.img_evento);
        localEventoTextView = (TextView) view.findViewById(R.id.nome_local);
        dataEventoTextView = (TextView) view.findViewById(R.id.data_evento);
        horaEventoTextView = (TextView) view.findViewById(R.id.hora_evento);
        descricacaoTextView = (TextView) view.findViewById(R.id.descricao_evento);
        obsTextView = (TextView) view.findViewById(R.id.obs_evento);

        eventoImageView.setImageBitmap(Util.StringBase64ToBitmap(getEvento().getUrlImgEvento()));
        localEventoTextView.setText(getEvento().getLocalEvento());
        dataEventoTextView.setText(Util.formatarDataDiaMes(getEvento().getDtEventoInicio()));
        horaEventoTextView.setText(getEvento().getHrEvento());

        if(getEvento().getDescricaoEvento() != null && !getEvento().getDescricaoEvento().isEmpty()){
            descricacaoTextView.setText(getEvento().getDescricaoEvento());
        }else{
            descricacaoTextView.setVisibility(View.GONE);
        }

        if(getEvento().getObservacao() != null && !getEvento().getObservacao().isEmpty()) {
            obsTextView.setText(getEvento().getObservacao());
        }else{
            obsTextView.setVisibility(View.GONE);
        }



        return  view;

    }

    @Override
    public void onPause() {
        super.onPause();



    }



    @Override
    public String getTagFragment() {
        return getClass().getSimpleName();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
