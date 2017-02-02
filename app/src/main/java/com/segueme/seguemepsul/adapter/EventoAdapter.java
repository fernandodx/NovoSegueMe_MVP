package com.segueme.seguemepsul.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.segueme.seguemepsul.R;
import com.segueme.seguemepsul.model.pojo.Evento;
import com.segueme.seguemepsul.util.Util;

import java.util.List;

/**
 * Created by FernandoDias on 18/01/17.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {


    private List<Evento> listaEvento;
    private Context context;
    private OnEventoActions onEventoActions;


    public EventoAdapter(List<Evento> listaEvento, Context context, OnEventoActions onEventoActions) {
        this.listaEvento = listaEvento;
        this.context = context;
        this.onEventoActions = onEventoActions;
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.evento_item_adapter, parent, false);

        EventoViewHolder holder = new EventoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EventoViewHolder holder, final int position) {

        Evento evento = listaEvento.get(position);

        holder.nomeEventoTextView.setText(evento.getNmEvento());
        holder.dataDiaMesTextView.setText(Util.formatarDataDiaMes(evento.getDtEventoInicio()).toUpperCase());
        holder.dataAnoTextView.setText(Util.formatarAno(evento.getDtEventoInicio()));
        holder.fotoEventoImageView.setImageBitmap(Util.StringBase64ToBitmap(evento.getUrlImgEvento()));
        holder.horaTextView.setText(evento.getHrEvento());

         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onEventoActions.clickEvento(position, v);
             }
         });

    }

    @Override
    public int getItemCount() {
        return listaEvento == null ? 0 : listaEvento.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {

        TextView dataDiaMesTextView;
        TextView horaTextView;
        TextView dataAnoTextView;
        ImageView fotoEventoImageView;
        TextView nomeEventoTextView;
        CardView cardView;

        public EventoViewHolder(View view) {
            super(view);

            this.dataDiaMesTextView = (TextView) view.findViewById(R.id.dataDiaMes);
            this.dataAnoTextView = (TextView) view.findViewById(R.id.dataAno);
            this.horaTextView = (TextView) view.findViewById(R.id.hora);
            this.fotoEventoImageView = (ImageView) view.findViewById(R.id.img_evento);
            this.nomeEventoTextView = (TextView) view.findViewById(R.id.nome_evento);
            this.cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

    public interface OnEventoActions {

        void clickEvento(int position, View view);
    }
}
