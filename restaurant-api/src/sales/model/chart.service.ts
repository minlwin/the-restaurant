import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { SaleDetails } from './saledetails.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { TYPES } from 'src/common/category.type';
import { currentDateEnd, currentDateStart } from 'src/common/date.utils';
import moment = require('moment');
import { CategoryService } from 'src/master/model/category.service';
import { ProductService } from 'src/master/model/product.service';

const DATE_FORMAT = "YYYYMMDD"
@Injectable()
export class ChartService {

    

    constructor(
        @InjectRepository(SaleDetails)
        private readonly repo:Repository<SaleDetails>,
        private readonly categories:CategoryService,
        private readonly products:ProductService
    ){}

    search(type?:string, category?:number, from?:string, to?:string) {

        let startDate = currentDateStart()
        startDate.setMonth(startDate.getMonth() - 1)

        let fromDate:Date = from ? new Date(from) : startDate
        let toDate:Date = to ? new Date(to) : currentDateEnd()

        if(!type) {
            return this.loadByTypes(fromDate, toDate)
        }

        if(type && !category) {
            return this.loadByCategories(type, fromDate, toDate)
        }

        if(category) {
            return this.loadByProducts(category, fromDate, toDate)
        }
        
    }

    private async loadByTypes(from:Date, to:Date) {

        let chartData = new Map<String, Map<String, Number>>()

        for(let type of TYPES) {
            let map = new Map<String, Number>()
            let start = new Date(from.getTime())
            
            while(moment(start).isBefore(moment(to))) {

                let next = new Date(start.getTime())
                next.setDate(next.getDate() + 1)

                map[moment(next).format(DATE_FORMAT)] = await this.loadChart('s.saleDate >= :start and s.saleDate <= :end and c.type = :type', {
                    start: start,
                    end: next,
                    type: type
                })
                start = next
            }

            chartData[type] = map
        }

        return chartData
    }

    private async loadByCategories(type:string, from:Date, to:Date) {
        let list = await this.categories.search(type, null)
        let chartData = new Map<String, Map<String, Number>>()

        for(let c of list) {
            let map = new Map<String, Number>()
            let start = new Date(from.getTime())
           
            while(moment(start).isBefore(moment(to))) {

                let next = new Date(start.getTime())
                next.setDate(next.getDate() + 1)

                map[moment(next).format(DATE_FORMAT)] = await this.loadChart('s.saleDate >= :start and s.saleDate <= :end and c.id = :catId', {
                    start: start,
                    end: next,
                    catId: c.id
                })
                start = next
            }

            chartData[c.name] = map
        }

        return chartData
    }

    private async loadByProducts(category:number, from:Date, to:Date) {
        let list = await this.products.findByCategory(category)
        let chartData = new Map<String, Map<String, Number>>()

        for(let p of list) {
            let map = new Map<String, Number>()
            let start = new Date(from.getTime())
            
            while(moment(start).isBefore(moment(to))) {

                let next = new Date(start.getTime())
                next.setDate(next.getDate() + 1)
                map[moment(next).format(DATE_FORMAT)] = await this.loadChart('s.saleDate >= :start and s.saleDate <= :end and p.id = :pId', {
                    start: start,
                    end: next,
                    pId: p.id
                })
                start = next
            }

            chartData[p.name] = map
        }

        return chartData
    }

    private async loadChart(query:string, param:any) {
        let result = await this.repo.createQueryBuilder('sd')
            .select('sum(sd.quantity)', 'sum')
            .leftJoin('sd.product', 'p')
            .leftJoin('p.category', 'c')
            .leftJoin('sd.sale', 's')
            .where(query, param).getRawOne()
        return result.sum || 0
    }
}
