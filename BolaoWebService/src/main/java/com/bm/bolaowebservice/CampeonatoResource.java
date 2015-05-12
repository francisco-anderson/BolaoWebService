/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.CampeonatoRemote;
import com.bm.bolaoservice.entity.Campeonato;
import com.bm.bolaoservice.entity.Usuario;
import java.util.Date;
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
@Path("campeonato")
public class CampeonatoResource {

    @Context
    private UriInfo context;
    @EJB
    private CampeonatoRemote ejb;

    public CampeonatoResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public List<Campeonato> buscarCampeonatosUsuario(Usuario usuario) {

        return ejb.buscarPorUsuario(usuario);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/{id}")
    public Campeonato buscarCampeonatoPorId(@PathParam("id") Long id) {

//        System.out.println(xStream.toXML(ejb.consultaPorId(id)));
        return ejb.consultaPorId(id);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/{dataInicio}/{dataFim}/{status}")
    public List<Campeonato> buscarPorDataInicioStatus(@PathParam("dataInicio") Date datacomeco,
            @PathParam("dataFim") Date datafim,
            @PathParam("status") String status) {

        return (ejb.buscarPorDatainicioStatus(datacomeco, datafim, status));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/nome/{nome}")
    public List<Campeonato> buscarPorNome(@PathParam("nome") String nome) {

        return (ejb.buscarPorNome(nome));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/status/{status}")
    public List<Campeonato> buscarPorStatus(@PathParam("status") String status) {

        return (ejb.buscarPorStatus(status));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    public void atualizar(Campeonato campeonato) {
        ejb.salvar(campeonato);
    }

}
