package com.segueme.seguemepsul.model.pojo;

import com.google.firebase.database.DataSnapshot;
import com.segueme.seguemepsul.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by FernandoDias on 18/01/17.
 */

public class Evento  {

    private String descricaoEvento;
    private Date dtEventoFim;
    private Date dtEventoInicio;
    private String hrEvento;
    private String localEvento;
    private String nmEvento;
    private String observacao;
    private String urlImgEvento;

    public static List<Evento> createLista(DataSnapshot dataSnapshot) {

        List<Evento> lista = new ArrayList<>();
        Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();

        while (iterator.hasNext()) {

            HashMap eventoHash = (HashMap) iterator.next().getValue();
            Evento evento = new Evento();

            evento.setUrlImgEvento((String) eventoHash.get("urlImgEvento"));
            evento.setObservacao((String) eventoHash.get("observacao"));
            evento.setNmEvento((String) eventoHash.get("nmEvento"));
            evento.setLocalEvento((String) eventoHash.get("localEvento"));
            evento.setDescricaoEvento((String) eventoHash.get("descricaoEvento"));
            evento.setHrEvento((String) eventoHash.get("hrEvento"));

            evento.setDtEventoInicio(Util.createDateWithTime((Long) eventoHash.get("dtEventoInicio")));
            evento.setDtEventoFim(Util.createDateWithTime((Long) eventoHash.get("dtEventoFim")));

            lista.add(evento);

        }

        return lista;
    }


    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public Date getDtEventoFim() {
        return dtEventoFim;
    }

    public void setDtEventoFim(Date dtEventoFim) {
        this.dtEventoFim = dtEventoFim;
    }

    public Date getDtEventoInicio() {
        return dtEventoInicio;
    }

    public void setDtEventoInicio(Date dtEventoInicio) {
        this.dtEventoInicio = dtEventoInicio;
    }

    public String getHrEvento() {
        return hrEvento;
    }

    public void setHrEvento(String hrEvento) {
        this.hrEvento = hrEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public String getNmEvento() {
        return nmEvento;
    }

    public void setNmEvento(String nmEvento) {
        this.nmEvento = nmEvento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUrlImgEvento() {
        return urlImgEvento;
    }

    public void setUrlImgEvento(String urlImgEvento) {
        this.urlImgEvento = urlImgEvento;
    }
}
