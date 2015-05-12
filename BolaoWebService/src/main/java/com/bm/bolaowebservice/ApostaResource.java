/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ApostaRemote;
import com.bm.bolaoservice.entity.Aposta;
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
@Path("aposta")
public class ApostaResource {

    @Context
    private UriInfo context;
    @EJB
    private ApostaRemote ejb;

    public ApostaResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public List<Aposta> buscarApostasUsuario(Usuario usuario) {
        return ejb.buscarPorUsuario(usuario);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvar(Aposta aposta) {
        ejb.salvar(aposta);
    }

}
