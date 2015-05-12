/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.PontuacaoUsuarioRemote;
import com.bm.bolaoservice.entity.PontuacaoUsuario;
import com.bm.bolaoservice.entity.Usuario;
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

    public PontuacaoUsuarioResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public List<PontuacaoUsuario> buscarPontuacaoUsuario(Usuario usuario) {
        return ejb.buscarPorUsuario(usuario);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizar(PontuacaoUsuario pontuacaoUsuario) {
        ejb.salvar(pontuacaoUsuario);
    }

}
