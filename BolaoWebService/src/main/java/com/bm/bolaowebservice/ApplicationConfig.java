/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bm.bolaowebservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Anderson
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.bm.bolaowebservice.ApostaResource.class);
        resources.add(com.bm.bolaowebservice.CampeonatoResource.class);
        resources.add(com.bm.bolaowebservice.EquipeResource.class);
        resources.add(com.bm.bolaowebservice.PontuacaoUsuarioResource.class);
        resources.add(com.bm.bolaowebservice.UsuarioResource.class);
    }
    
}
