package com.segueme.seguemepsul.view.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segueme.seguemepsul.R;
import com.segueme.seguemepsul.adapter.EventoAdapter;
import com.segueme.seguemepsul.model.pojo.Evento;
import com.segueme.seguemepsul.mvp.EventosMVP;
import com.segueme.seguemepsul.mvp.StateMaintainer;
import com.segueme.seguemepsul.presenter.EventosPresenter;
import com.segueme.seguemepsul.view.activity.MainMobileActivity;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by FernandoDias on 18/01/17.
 */

public class ListaEventosFragment extends BaseFragment
                                  implements EventosMVP<EventosMVP.RequiredViewOpsEventos> , EventosMVP.RequiredViewOpsEventos, EventoAdapter.OnEventoActions  {


    private RecyclerView eventosRecyclerView;
    private LinearLayoutManager layoutManager;

    private List<Evento> listaEventos;

    private EventosMVP.PresenterOpsEventos presenterOpsEventos;

    private StateMaintainer stateMaintainer;
    private SweetAlertDialog loading;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.lista_eventos_fragment, viewGroup, false);

        eventosRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

        EventoAdapter eventoAdapter = new EventoAdapter(getListaEventos(), getContext(), this);

        eventosRecyclerView.setLayoutManager(layoutManager);
        eventosRecyclerView.setAdapter(eventoAdapter);


        startMVP();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenterOpsEventos.atualizarListaEventos();

    }

    @Override
    public void atualizarListaEventos(List<Evento> lista) {

        setListaEventos(lista);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                EventoAdapter eventoAdapter = new EventoAdapter(getListaEventos(), getActivity().getBaseContext() , ListaEventosFragment.this);
                eventosRecyclerView.setAdapter(eventoAdapter);

            }
        });
    }

    @Override
    public void showLoading(String titulo, int tipo) {
        loading = new SweetAlertDialog(getContext(), tipo);
        loading.getProgressHelper().setBarColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        loading.setTitleText(titulo);
        loading.setCancelable(false);
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.hide();
    }


    @Override
    public void clickEvento(int position, View view) {

        Evento evento = listaEventos.get(position);

        DetalheEventoFragment detalheEventoFragment = new DetalheEventoFragment();
        detalheEventoFragment.setEvento(evento);

        ((MainMobileActivity) getActivity()).addFragment(detalheEventoFragment);
    }

    @Override
    public String getTagFragment() {
        return getClass().getSimpleName();
    }

    public List<Evento> getListaEventos() {
        if(listaEventos == null) {
            listaEventos = new ArrayList<>();
        }
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }


    @Override
    public void startMVP() {

        stateMaintainer = new StateMaintainer(this.getFragmentManager(), getTag());

        if(stateMaintainer.firstTimeIn()) {

            initialize(this);

        }else{

            reInitialize(this);

        }

    }

    @Override
    public void initialize(RequiredViewOpsEventos requiredViewOps) {
        Log.d(getTag(), "initialize");

        presenterOpsEventos = new EventosPresenter(requiredViewOps);
        stateMaintainer.put(presenterOpsEventos.getClass().getSimpleName(), presenterOpsEventos);

    }

    @Override
    public void reInitialize(RequiredViewOpsEventos requiredViewOps) {

        presenterOpsEventos = stateMaintainer.get(presenterOpsEventos.getClass().getSimpleName());

        if(presenterOpsEventos == null) {

            initialize(requiredViewOps);

        }else{
            ((EventosPresenter) presenterOpsEventos).onConfigurationChanged(requiredViewOps);
        }

    }
}
