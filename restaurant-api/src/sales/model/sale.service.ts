import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Sale } from './sale.entity';
import { SaleDetails } from './saledetails.entity';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class SaleService extends BaseService<Sale> {

    constructor(
        @InjectRepository(Sale)
        repo:Repository<Sale>,
        @InjectRepository(SaleDetails)
        private readonly orders:Repository<SaleDetails>
    ) { super(repo) }

    save(sale:Sale) {
        sale.details.forEach(d => {
            d.sale = sale
        })
        return this.repo.save(sale)
    }

    getActiveVouchers() {
        return this.repo.find({status : 'Active'})
    }

    getActiveVouchersByTableId(id:number) {
        return this.repo.find({
            tables : {id : id},
            status : 'Active'
        })
    }

    search(from?:string, to?:string, status?:string, tableNumber?:string) {

        let query = this.repo.createQueryBuilder('s').leftJoinAndSelect('s.tables' , 't').where('1 = 1')

        if(from) {
            query = query.andWhere('s.saleDate >= :from', {from : new Date(from)})
        }

        if(to) {
            query = query.andWhere('s.saleDate <= :to', {to : new Date(to)})
        }

        if(status) {
            query = query.andWhere('s.status = :status', {status : status})
        }

        if(tableNumber) {
            query = query.andWhere('LOWER(t.tableNuumber) like :table', { table : `${tableNumber.toLocaleLowerCase()}%` })
        }

        return query.getMany()
    }
}