import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Sale } from './sale.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';
import { SaleDetailsService } from './saledetails.service';
import { SaleDetails } from './saledetails.entity';

@Injectable()
export class SaleService extends BaseService<Sale> {

    constructor(
        @InjectRepository(Sale)
        repo:Repository<Sale>,
        @InjectRepository(SaleDetails)
        private readonly orders:Repository<SaleDetails>
    ) { super(repo) }

    async save(t:Sale) {
        let sale = await this.repo.save(t)
        sale.details.forEach(d => {
            d.sale = sale
            this.orders.save(d)
        })
        return sale
    }

    async findById(id:number) {
        let sale = await this.repo.findOne(id)
        let details = await this.orders.find({sale : {id : id}})
        sale.details = details
        return sale
    }

}