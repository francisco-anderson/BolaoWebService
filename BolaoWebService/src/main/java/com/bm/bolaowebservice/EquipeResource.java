/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.EquipeRemote;
import com.bm.bolaoservice.entity.Equipe;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
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

    private XStream xStream;

    public EquipeResource() {
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public String buscarEquipesUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Equipe> equipes = ejb.buscarPorUsuario(usuario);
        return xStream.toXML(equipes);

    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("campeonato/{idcampeonato}")
    public String buscarEquipePorCampeonato(@PathParam("idcampeonato") Long id) {
        return xStream.toXML(ejb.buscarEquipesPorCampeonato(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void atualizar(String e) {
        Equipe equipe = (Equipe) xStream.fromXML(e);
        ejb.salvar(equipe);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("pontuacao/{idUsuario}/{idCampeonato}/{tipo}")
    public String buscarPorPontuacaoGrupo(@PathParam("idUsuario") Long idUsuario,
            @PathParam("idCampeonato") Long idCampeonato,
            @PathParam("tipo") String tipo) {

        List<Equipe> e = ejb.buscarEquipePorPontuacaoGrupo(idUsuario, idCampeonato, tipo);
        return xStream.toXML(e);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("{id}")
    public String buscarPorId(@PathParam("id")Long id){
        
        return xStream.toXML(ejb.buscarPorId(id));
    }

}
