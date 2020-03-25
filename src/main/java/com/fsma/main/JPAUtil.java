package com.fsma.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
  private static final String PERSISTENCE_UNIT_NAME = "postgres-container";
  private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

  public static EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

}