/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ApostaResultadoRemote;
import com.bm.bolaoservice.entity.ApostaResultado;
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
@Path("apostaequipepartida")
public class ApostaResultadoResource {
    
    @Context
    private UriInfo context;
    
    @EJB
    private ApostaResultadoRemote ejb;
    
    private XStream xStream;
    
    public ApostaResultadoResource(){
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("id/{idAposta}/{idEquipe}/{idPartida}")
    public String buscarPorId(@PathParam("idAposta")Long idAposta,
                                @PathParam("idEquipe")Long idEquipe,
                                    @PathParam("idPartida")Long idPartida){
        return xStream.toXML(ejb.buscarPorId(idAposta, idEquipe, idPartida));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("aposta/{id}")
    public String buscarPorAposta(@PathParam("id")Long id){
        
        return xStream.toXML(ejb.buscarPorAposta(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{id}")
    public String buscarPorEquipe(@PathParam("id")Long id){
        
        return xStream.toXML(ejb.buscarPorEquipe(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("partida/{id}")
    public String buscarPorPartida(@PathParam("id")Long id){
        
        return xStream.toXML(ejb.buscarPorPartida(id));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("equipe/{idEquipe}/partida/{idPartida}")
    public String buscarPorEquipePartida(@PathParam("idEquipe")Long idEquipe,@PathParam("idPartida")Long idPartida){
        
        return xStream.toXML(ejb.buscarPorEquipePartida(idEquipe, idPartida));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void atualizar(String apostaEquipePartida){
        ApostaResultado a = (ApostaResultado) xStream.fromXML(apostaEquipePartida);
        ejb.salvar(a);
    }
    
}
