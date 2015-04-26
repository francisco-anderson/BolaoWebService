/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.CampeonatoRemote;
import com.bm.bolaoservice.ejb.UsuarioRemote;
import com.bm.bolaoservice.entity.Campeonato;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Anderson
 */
@Path("bolao")
public class BolaoResource {

    @Context
    private UriInfo context;

    @EJB
    private UsuarioRemote usuarioejb;
    @EJB
    private CampeonatoRemote campeonatoejb;
    
    private final XStream xStream;
  

    /**
     * Creates a new instance of UsuarioResource
     */
    public BolaoResource() {
        
        xStream = new XStream();
        
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/login")
    public String realizarLogin(Usuario usuario) {
        //TODO return proper representation object
        return xStream.toXML(usuarioejb.consultarLogin(usuario.getEmail(), usuario.getSenha()));

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/novoUsuario")
    public String cadastrarUsuario(Usuario usuario) {

        usuario = usuarioejb.novoUsuario(usuario);
        if (usuario == null) {
            return "E-mail Ja Cadastrado";
        }
        return "OK";

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/campeonato/id/{id}")
    public String buscarCampeonatoPorId(@PathParam("id") Long id) {
        return xStream.toXML(campeonatoejb.consultaPorId(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/campeonato/buscarporDataInicioStatus/")
    public String buscarPorDataInicioStatus(@FormParam("datacomeco") Date datacomeco,
            @FormParam("datafim") Date datafim,
            @FormParam("status") String status) {

        return xStream.toXML(campeonatoejb.buscarPorDatainicioStatus(datacomeco, datafim, status));

    }

    @GET
    @Produces("application/xml")
    @Path("/campeonato/nome/{nome}")
    public String buscarPorNome(@PathParam("nome") String nome) {
        return  xStream.toXML(campeonatoejb.buscarPorNome(nome));
    }

    @GET
    @Produces("application/xml")
    @Path("/campeonato/status/{status}")
    public String buscarPorStatus(@PathParam("status") String status) {
        return xStream.toXML(campeonatoejb.buscarPorStatus(status));
    }
    
    @GET
    @Produces("application/xml")
    @Path("campeonato/equipe/{idcampeonato}")
    public String buscarEquipePorCampeonato(@PathParam("idcampeonato")Long id){
        return xStream.toXML(campeonatoejb.buscarEquipesPorCampeonato(id));
    }

}
