import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { SaleDetails } from './saledetails.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';
import { Sale } from './sale.entity';
import { SaleService } from './sale.service';

@Injectable()
export class SaleDetailsService extends BaseService<SaleDetails> {

    constructor(
        private readonly saleService:SaleService,
        @InjectRepository(SaleDetails)
        repo:Repository<SaleDetails>
    ) { super(repo) }
    
    findBySale(sale:Sale) {
        return this.repo.find({
            sale : { id : sale.id }
        })
    }

    async saveBySale(saleId:number, details:SaleDetails) {

        let sale = await this.saleService.findById(saleId)
        details.sale = sale
        if(details.id) {
            for(let index in sale.details) {
                let  data = sale.details[index]
                if(data.id == details.id) {
                    sale.details[index] = details
                }
            }            
        } else {
            sale.details.push(details)
        }
        sale.subTotal = sale.details.map(s => s.quantity * s.unitPrice).reduce((a, b) => a + b)
        sale.tax = details.sale.subTotal / 100 * 5
        await this.saleService.save(details.sale)

        return details
    }
}
