/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.PontuacaoEquipeRemote;
import com.bm.bolaoservice.entity.PontuacaoEquipe;
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
@Path("pontuacaoequipe")
public class PontuacaoEquipeResource {

    @Context
    private UriInfo context;

    @EJB
    private PontuacaoEquipeRemote ejb;

    public PontuacaoEquipeResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{id}")
    public List<PontuacaoEquipe> buscarPorEquipe(@PathParam("id") Long id) {

        return (ejb.buscarPorEquipe(id));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("campeonato/{id}")
    public List<PontuacaoEquipe> buscarPorCampeonato(@PathParam("id") Long id) {
        return (ejb.buscarPorCampeonato(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{idEquipe}/campeonato/{idCampeonato}")
    public PontuacaoEquipe buscarPorEquipeCampeonato(@PathParam("idEquipe") Long idEquipe, @PathParam("idCampeonato") Long idCampeonato) {
        return (ejb.buscarPorCampeonatoEquipe(idCampeonato, idEquipe));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(PontuacaoEquipe pontuacaoEquipe) {
        ejb.salvar(pontuacaoEquipe);
    }

}
