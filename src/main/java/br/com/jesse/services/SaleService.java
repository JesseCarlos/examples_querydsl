package br.com.jesse.services;

import br.com.jesse.model.AmountByBrand;
import br.com.jesse.model.QModel;
import br.com.jesse.model.QSale;
import br.com.jesse.model.Sale;
import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class SaleService {

    private EntityManager entityManager;

    public SaleService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Sale> findSalesWhereManufactureYearGOE2014() {
        QSale sale = QSale.sale;

        return new JPAQueryFactory(entityManager)
                .selectFrom(sale)
                .where(sale.manufactureYear.goe(2014))
                .fetch();
    }

    public List<Long> amountSales2015() {
        QSale sale = QSale.sale;

        return new JPAQueryFactory(entityManager)
                .select(sale.count())
                .from(sale)
                .where(sale.manufactureYear.eq(2015))
                .fetch();
    }

    public List<Sale> findSalesBrandAUDIAndBMW() {
        QSale sale = QSale.sale;
        QModel model = QModel.model;

        return new JPAQueryFactory(entityManager)
                .selectFrom(sale)
                .innerJoin(sale.model, model)
                .where(model.brand.in("AUDI", "BMW"))
                .fetch();
    }

    public List<Tuple> amountSalesGroupingByBrand() {
        QSale sale = QSale.sale;
        QModel model = QModel.model;

        return new JPAQueryFactory(entityManager)
                .select(model.brand, sale.count())
                .from(sale)
                .innerJoin(sale.model, model)
                .groupBy(model.brand)
                .fetch();

    }

    public Map<String, Long> amountSalesGroupingByBrandTransformToMap() {
        QSale sale = QSale.sale;
        QModel model = QModel.model;

        return new JPAQueryFactory(entityManager)
                .select(model.brand, sale.count())
                .from(sale)
                .innerJoin(sale.model, model)
                .groupBy(model.brand)
                .transform(GroupBy.groupBy(model.brand).as(sale.count()));

    }

    public List<AmountByBrand> amountSalesGroupingByBrandTransformToClass() {
        QSale sale = QSale.sale;
        QModel model = QModel.model;

        return new JPAQueryFactory(entityManager)
                .select(Projections.bean(AmountByBrand.class, model.brand, sale.count().as("amount")))
                .from(sale)
                .innerJoin(sale.model, model)
                .groupBy(model.brand)
                .fetch();
    }

    public void deleteSalesAUDI() {
        QSale sale = QSale.sale;

        new JPAQueryFactory(entityManager)
                .delete(sale)
                .where(sale.model.brand.eq("AUDI"))
                .execute();
    }
}