package br.com.jesse.services;

import br.com.jesse.model.QModel;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class ModelService {

    private EntityManager entityManager;

    public ModelService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void updateModelBMWZ4ToZ3() {
        QModel model = QModel.model;

        new JPAQueryFactory(entityManager)
                .update(model)
                .set(model.description, "Z3")
                .where(model.description.eq("Z4"))
                .execute();
    }
}