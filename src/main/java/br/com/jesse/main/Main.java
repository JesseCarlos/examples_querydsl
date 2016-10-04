package br.com.jesse.main;

import br.com.jesse.model.QModel;
import br.com.jesse.model.QSale;
import br.com.jesse.services.ModelService;
import br.com.jesse.services.SaleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examples-querydsl");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final SaleService saleService = new SaleService(entityManager);

        entityManager.getTransaction().begin();
        new ModelService(entityManager).updateModelBMWZ4ToZ3();
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }
}
