/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.UsuarioRemote;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Anderson
 */
@Path("usuario")
public class UsuarioResource {
    
    @Context
    private UriInfo context;
    @EJB
    private UsuarioRemote ejb;
    
    
    private XStream xStream;
    
    
    public UsuarioResource(){
        
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/login")
    public String realizarLogin(String user) {             
        
        Usuario usuario = (Usuario) xStream.fromXML(user);
        return xStream.toXML(ejb.consultarLogin(usuario.getEmail(), usuario.getSenha()));
        
    }

    
    @PUT
    @Consumes    
    public void salvarUsuario(String user) {
        
        ejb.salvar((Usuario) xStream.fromXML(user));
        
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/novoUsuario")
    public String cadastrarUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        usuario = ejb.novoUsuario(usuario);
        if (usuario == null) {
            return "E-mail Ja Cadastrado";
        }
        return "OK";

    }
    
}
