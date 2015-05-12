/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ResultadoRemote;
import com.bm.bolaoservice.entity.Resultado;
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
@Path("equipepartida")
public class ResultadoResource {

    @Context
    private UriInfo context;

    @EJB
    private ResultadoRemote ejb;

    public ResultadoResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{idEquipe}/partida/{idPartida}")
    public Resultado buscarPorId(@PathParam("idEquipe") Long idEquipe, @PathParam("idPartida") Long idPartida) {
        return (ejb.buscarPorId(idEquipe, idPartida));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{id}")
    public List<Resultado> buscarPorEquipe(@PathParam("id") Long id) {
        return (ejb.buscarPorEquipe(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("partida/{id}")
    public List<Resultado> buscarPorPartida(@PathParam("id") Long id) {
        return (ejb.buscarPorPartida(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(Resultado resultado) {
        ejb.salvar(resultado);
    }

}
