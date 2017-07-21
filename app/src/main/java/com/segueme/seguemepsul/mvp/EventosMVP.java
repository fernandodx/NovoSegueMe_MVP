package com.segueme.seguemepsul.mvp;

import com.segueme.seguemepsul.model.pojo.Evento;

import java.util.List;

/**
 * Created by FernandoDias on 19/01/17.
 */

public interface EventosMVP<T> extends BaseMVP<T> {

    interface PresenterOpsEventos {

        void atualizarListaEventos();

    }

    interface RequiredViewOpsEventos {

        void atualizarListaEventos(List<Evento> lista);
        void showLoading(String titulo, int tipo);
        void hideLoading();
        void showAlerta(String alerta);

    }

    interface ModelOpsEventos {

        void pesquisarTodosEventos();

    }

    interface RequiredPresenterOpsEventos {

        void onListaEventos(List<Evento> lista);

    }




}
