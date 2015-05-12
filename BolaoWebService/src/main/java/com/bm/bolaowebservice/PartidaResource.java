/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.PartidaRemote;
import com.bm.bolaoservice.entity.Partida;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Anderson
 */
@Path("partida")
public class PartidaResource {

    @Context
    private UriInfo context;

    @EJB
    private PartidaRemote ejb;

    public PartidaResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("{id}")
    public Partida buscarPartida(@PathParam("id") Long id) {

        return (ejb.buscarPartidaPorId(id));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/campeonato/{id}")
    public List<Partida> buscarPorCampeonato(@PathParam("id") Long id) {
        return ejb.buscarPorCampeonato(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(Partida partida) {
        ejb.salvar(partida);
    }

}
