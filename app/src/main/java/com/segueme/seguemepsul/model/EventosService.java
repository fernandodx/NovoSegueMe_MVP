package com.segueme.seguemepsul.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.segueme.seguemepsul.model.pojo.Evento;
import com.segueme.seguemepsul.mvp.EventosMVP;
import com.segueme.seguemepsul.util.FirebaseUtil;

import java.util.List;

/**
 * Created by FernandoDias on 19/01/17.
 */

public class EventosService  implements EventosMVP.ModelOpsEventos {

    private EventosMVP.RequiredPresenterOpsEventos requiredPresenterOpsEventos;

    public EventosService(EventosMVP.RequiredPresenterOpsEventos requiredPresenterOpsEventos) {
        this.requiredPresenterOpsEventos = requiredPresenterOpsEventos;
    }

    @Override
    public void pesquisarTodosEventos() {

        Query query = FirebaseUtil.getInstance().getEventos().orderByChild("dtEventoInicio");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Evento> lista = Evento.createLista(dataSnapshot);
                requiredPresenterOpsEventos.onListaEventos(lista);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
