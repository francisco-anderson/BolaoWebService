/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.EquipeRemote;
import com.bm.bolaoservice.entity.Equipe;
import com.bm.bolaoservice.entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("equipe")
public class EquipeResource {

    @Context
    private UriInfo context;
    @EJB
    private EquipeRemote ejb;

    public EquipeResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public List<Equipe> buscarEquipesUsuario(Usuario usuario) {

        return ejb.buscarPorUsuario(usuario);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("campeonato/{idcampeonato}")
    public List<Equipe> buscarEquipePorCampeonato(@PathParam("idcampeonato") Long id) {
        return (ejb.buscarEquipesPorCampeonato(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(Equipe equipe) {
        ejb.salvar(equipe);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("pontuacao/{idUsuario}/{idCampeonato}/{tipo}")
    public List<Equipe> buscarPorPontuacaoGrupo(@PathParam("idUsuario") Long idUsuario,
            @PathParam("idCampeonato") Long idCampeonato,
            @PathParam("tipo") String tipo) {

        return ejb.buscarEquipePorPontuacaoGrupo(idUsuario, idCampeonato, tipo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("{id}")
    public Equipe buscarPorId(@PathParam("id") Long id) {

        return (ejb.buscarPorId(id));
    }

}
