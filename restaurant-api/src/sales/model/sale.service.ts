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
}