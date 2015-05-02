/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.CampeonatoRemote;
import com.bm.bolaoservice.entity.Campeonato;
import com.bm.bolaoservice.entity.Usuario;
import com.thoughtworks.xstream.XStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("campeonato")
public class CampeonatoResource {

    @Context
    private UriInfo context;
    @EJB
    private CampeonatoRemote ejb;

    private XStream xStream;

    public CampeonatoResource() {
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario")
    public String buscarCampeonatosUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Campeonato> campeonatos = ejb.buscarPorUsuario(usuario);
        return xStream.toXML(campeonatos);

    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/{id}")
    public String buscarCampeonatoPorId(@PathParam("id") Long id) {

        System.out.println(xStream.toXML(ejb.consultaPorId(id)));
        return xStream.toXML(ejb.consultaPorId(id));

    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/{dataInicio}/{dataFim}/{status}")
    public String buscarPorDataInicioStatus(@PathParam("dataInicio") Date datacomeco,
            @PathParam("dataFim") Date datafim,
            @PathParam("status") String status) {

        return xStream.toXML(ejb.buscarPorDatainicioStatus(datacomeco, datafim, status));

    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/nome/{nome}")
    public String buscarPorNome(@PathParam("nome") String nome) {

        return xStream.toXML(ejb.buscarPorNome(nome));
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/status/{status}")
    public String buscarPorStatus(@PathParam("status") String status) {

        return xStream.toXML(ejb.buscarPorStatus(status));
    }

}
