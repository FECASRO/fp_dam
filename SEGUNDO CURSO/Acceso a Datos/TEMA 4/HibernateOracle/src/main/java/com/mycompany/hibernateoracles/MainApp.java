/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernateoracles;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author MCCLA
 */
public class MainApp {
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        
        SessionFactory factory = HibernateUtil.getSessionFactory();

        
        Session session = factory.getCurrentSession();
        
        try {
            
            session.beginTransaction();

            
            Departamento dept = session.get(Departamento.class, 1);  

            
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}