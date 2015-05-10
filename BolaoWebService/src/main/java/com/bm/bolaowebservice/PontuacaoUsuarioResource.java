/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.PontuacaoUsuarioRemote;
import com.bm.bolaoservice.entity.PontuacaoUsuario;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
import java.util.List;
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
@Path("pontuacaousuario")
public class PontuacaoUsuarioResource {

    @Context
    private UriInfo context;
    @EJB
    private PontuacaoUsuarioRemote ejb;

    private XStream xStream;

    public PontuacaoUsuarioResource() {

        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public String buscarPontuacaoUsuario(String user) {
        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<PontuacaoUsuario> pontuacaoUsuarios = ejb.buscarPorUsuario(usuario);
        return xStream.toXML(pontuacaoUsuarios);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void atualizar(String p){
        PontuacaoUsuario pontuacaoUsuario = (PontuacaoUsuario)xStream.fromXML(p);
        ejb.salvar(pontuacaoUsuario);
    }

}
