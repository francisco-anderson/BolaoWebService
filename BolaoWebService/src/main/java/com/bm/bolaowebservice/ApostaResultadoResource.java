/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ApostaResultadoRemote;
import com.bm.bolaoservice.entity.ApostaResultado;
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
@Path("apostaresultado")
public class ApostaResultadoResource {

    @Context
    private UriInfo context;

    @EJB
    private ApostaResultadoRemote ejb;

    public ApostaResultadoResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("id/{idAposta}/{idEquipe}/{idPartida}")
    public ApostaResultado buscarPorId(@PathParam("idAposta") Long idAposta,
            @PathParam("idEquipe") Long idEquipe,
            @PathParam("idPartida") Long idPartida) {
        return (ejb.buscarPorId(idAposta, idEquipe, idPartida));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("aposta/{id}")
    public List<ApostaResultado> buscarPorAposta(@PathParam("id") Long id) {

        return (ejb.buscarPorAposta(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{id}")
    public List<ApostaResultado> buscarPorEquipe(@PathParam("id") Long id) {

        return (ejb.buscarPorEquipe(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("partida/{id}")
    public List<ApostaResultado> buscarPorPartida(@PathParam("id") Long id) {

        return (ejb.buscarPorPartida(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{idEquipe}/partida/{idPartida}")
    public List<ApostaResultado> buscarPorEquipePartida(@PathParam("idEquipe") Long idEquipe, @PathParam("idPartida") Long idPartida) {

        return (ejb.buscarPorEquipePartida(idEquipe, idPartida));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(ApostaResultado apostaResultado) {
        ejb.salvar(apostaResultado);
    }

}
