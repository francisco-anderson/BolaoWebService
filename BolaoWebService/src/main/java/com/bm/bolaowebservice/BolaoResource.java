/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import com.bm.bolaoservice.ejb.ApostaRemote;
import com.bm.bolaoservice.ejb.CampeonatoRemote;
import com.bm.bolaoservice.ejb.EquipeRemote;
import com.bm.bolaoservice.ejb.PontuacaoUsuarioRemote;
import com.bm.bolaoservice.ejb.UsuarioRemote;
import com.bm.bolaoservice.entity.Aposta;
import com.bm.bolaoservice.entity.Campeonato;
import com.bm.bolaoservice.entity.Equipe;
import com.bm.bolaoservice.entity.PontuacaoUsuario;
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
import javax.ws.rs.PUT;
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
    @EJB
    private EquipeRemote equipeejb;
    @EJB
    private ApostaRemote apostaejb;
    @EJB
    private PontuacaoUsuarioRemote pontuacaoUsuarioRemote;

    private XStream xStream;

    /**
     * Creates a new instance of UsuarioResource
     */
    public BolaoResource() {
        xStream = new XStream();
        xStream.processAnnotations(Usuario.class);

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario/login")
    public String realizarLogin(String user) {
        //TODO return proper representation object       
        Usuario usuario = (Usuario) xStream.fromXML(user);
        return xStream.toXML(usuarioejb.consultarLogin(usuario.getEmail(), usuario.getSenha()));

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario/campeonatos")
    public String buscarCampeonatosUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Campeonato> campeonatos = campeonatoejb.buscarPorUsuario(usuario);
        return xStream.toXML(campeonatos);

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario/equipes")
    public String buscarEquipesUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Equipe> equipes = equipeejb.buscarPorUsuario(usuario);
        return xStream.toXML(equipes);

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario/apostas")
    public String buscarApostasUsuario(String user) {
        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<Aposta> apostas = apostaejb.buscarPorUsuario(usuario);
        return xStream.toXML(apostas);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/usuario/pontuacaousuario")
    public String buscarPontuacaoUsuario(String user) {
        Usuario usuario = (Usuario) xStream.fromXML(user);
        List<PontuacaoUsuario> pontuacaoUsuarios = pontuacaoUsuarioRemote.buscarPorUsuario(usuario);
        return xStream.toXML(pontuacaoUsuarios);
    }

    @PUT
    @Consumes
    @Path("/usuario")
    public void salvarUsuario(String user) {

        usuarioejb.salvar((Usuario) xStream.fromXML(user));

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/usuario/novoUsuario")
    public String cadastrarUsuario(String user) {

        Usuario usuario = (Usuario) xStream.fromXML(user);
        usuario = usuarioejb.novoUsuario(usuario);
        if (usuario == null) {
            return "E-mail Ja Cadastrado";
        }
        return "OK";

    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/campeonato/id/{id}")
    public String buscarCampeonatoPorId(@PathParam("id") Long id) {

        System.out.println(xStream.toXML(campeonatoejb.consultaPorId(id)));
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
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/campeonato/nome/{nome}")
    public String buscarPorNome(@PathParam("nome") String nome) {

        return xStream.toXML(campeonatoejb.buscarPorNome(nome));
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("/campeonato/status/{status}")
    public String buscarPorStatus(@PathParam("status") String status) {

        return xStream.toXML(campeonatoejb.buscarPorStatus(status));
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8")
    @Path("campeonato/equipes/{idcampeonato}")
    public String buscarEquipePorCampeonato(@PathParam("idcampeonato") Long id) {

        return xStream.toXML(campeonatoejb.buscarEquipesPorCampeonato(id));
    }

}
