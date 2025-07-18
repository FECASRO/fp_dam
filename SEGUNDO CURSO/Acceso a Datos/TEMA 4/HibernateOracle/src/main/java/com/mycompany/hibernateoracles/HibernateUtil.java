/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateoracles;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author MCCLA
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") 
                .build();

            
            Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Departamento.class) // Añadir clase anotada
                .getMetadataBuilder()
                .build();

           
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed." + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}