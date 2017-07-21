package com.segueme.seguemepsul.presenter;

import com.segueme.seguemepsul.model.EventosService;
import com.segueme.seguemepsul.model.pojo.Evento;
import com.segueme.seguemepsul.mvp.EventosMVP;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by FernandoDias on 19/01/17.
 */

public class EventosPresenter extends BasePresenter<EventosMVP.RequiredViewOpsEventos> implements EventosMVP.PresenterOpsEventos, EventosMVP.RequiredPresenterOpsEventos {

    private WeakReference<EventosMVP.RequiredViewOpsEventos> requiredViewOps;
    private EventosMVP.ModelOpsEventos modelOpsEventos;

    public EventosPresenter(EventosMVP.RequiredViewOpsEventos requiredViewOps) {
        this.requiredViewOps = new WeakReference<>(requiredViewOps);
        this.modelOpsEventos = new EventosService(this);
    }

    @Override
    public void atualizarListaEventos() {
        modelOpsEventos.pesquisarTodosEventos();
        requiredViewOps.get().showLoading("Carregando eventos...", SweetAlertDialog.PROGRESS_TYPE);

    }

    @Override
    public void onListaEventos(List<Evento> lista) {
        requiredViewOps.get().atualizarListaEventos(lista);
        requiredViewOps.get().hideLoading();
        requiredViewOps.get().showAlerta("Lista atualizada!");
    }


    @Override
    public void onConfigurationChanged(EventosMVP.RequiredViewOpsEventos requiredViewOps) {
        this.requiredViewOps = new WeakReference<>(requiredViewOps);
    }
}
