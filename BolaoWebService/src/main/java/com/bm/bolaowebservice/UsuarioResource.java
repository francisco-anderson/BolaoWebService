/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.UsuarioRemote;
import com.bm.bolaoservice.entity.Usuario;
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

    public UsuarioResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Usuario realizarLogin(Usuario usuario) {    
             
        return ejb.consultarLogin(usuario.getEmail(), usuario.getSenha());

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvarUsuario(Usuario user) {

        ejb.salvar(user);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/novoUsuario")
    public String cadastrarUsuario(Usuario usuario) {
      
        usuario = ejb.novoUsuario(usuario);
        if (usuario == null) {
            return "E-mail Ja Cadastrado";
        }
        return "OK";

    }

}
