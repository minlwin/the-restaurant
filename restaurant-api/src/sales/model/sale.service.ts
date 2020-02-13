import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Sale } from './sale.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';
import { SaleDetails } from './saledetails.entity';

@Injectable()
export class SaleService extends BaseService<Sale> {

    constructor(
        @InjectRepository(Sale)
        repo:Repository<Sale>,
        @InjectRepository(SaleDetails)
        private readonly orders:Repository<SaleDetails>
    ) { super(repo) }

    save(t:Sale) {

        let sale = this.repo.save(t)
        
        sale.then(s => {
            t.details.forEach(d => {
                d.sale = s
                this.orders.save(d)
            })    
        })
        return sale
    }

}