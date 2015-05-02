/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ApostaRemote;
import com.bm.bolaoservice.entity.Aposta;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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

    private XStream xStream;

    public ApostaResource() {
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public String buscarApostasUsuario(String user) {
        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Aposta> apostas = ejb.buscarPorUsuario(usuario);
        return xStream.toXML(apostas);
    }

}
