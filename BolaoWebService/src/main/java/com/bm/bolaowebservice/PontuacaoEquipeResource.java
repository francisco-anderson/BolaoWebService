/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.PontuacaoEquipeRemote;
import com.bm.bolaoservice.entity.PontuacaoEquipe;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
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
    
    private XStream xStream;
    
    public PontuacaoEquipeResource(){
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{id}")
    public String buscarPorEquipe(@PathParam("id")Long id){
        
        return xStream.toXML(ejb.buscarPorEquipe(id));
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("campeonato/{id}")
    public String buscarPorCampeonato(@PathParam("id")Long id){
        return xStream.toXML(ejb.buscarPorCampeonato(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{idEquipe}/campeonato/{idCampeonato}")
    public String buscarPorEquipeCampeonato(@PathParam("idEquipe")Long idEquipe,@PathParam("idCampeonato") Long idCampeonato){
        return xStream.toXML(ejb.buscarPorCampeonatoEquipe(idCampeonato, idEquipe));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void atualizar(String pontuacaoEquipe){
        PontuacaoEquipe pe = (PontuacaoEquipe) xStream.fromXML(pontuacaoEquipe);
        ejb.salvar(pe);
    }
    
}
