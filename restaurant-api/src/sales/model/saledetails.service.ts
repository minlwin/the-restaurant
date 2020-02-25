import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { SaleDetails } from './saledetails.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';
import { Sale } from './sale.entity';
import { SaleService } from './sale.service';
import { OrderDto } from './order.dto';

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

    searchForStatus(tableNumber?:string, state?:string):Promise<OrderDto[]> {
        let query = this.repo.createQueryBuilder('od')
            .leftJoinAndSelect('od.product' ,'p')
            .leftJoinAndSelect('od.sale', 's')
            .leftJoinAndSelect('s.tables', 't').where('1 = 1')

        if(state) {
            query = query.andWhere('od.status = :status', {status : state})
        }

        if(tableNumber) {
            query = query.andWhere('t.tableNumber like :tableNumber', { tableNumber : `${tableNumber}%`})
        }

        return query.getMany()
    }

    async saveBySale(saleId:number, details:SaleDetails[]) {

        let sale = await this.saleService.findById(saleId)

        for (let index = 0; index < details.length; index++) {
            const d = details[index];

            d.sale = sale
            if(d.id) {
                for(let index in sale.details) {
                    let  data = sale.details[index]
                    if(data.id == d.id) {
                        sale.details[index] = d
                    }
                }            
            } else {
                sale.details.push(d)
            }

            await this.repo.save(d)            
        }

        sale.subTotal = sale.details.filter(s => s.status != 'Cancel').map(s => s.quantity * s.unitPrice).reduce((a, b) => a + b)
        sale.tax = sale.subTotal / 100 * 5
        await this.saleService.save(sale)

        return sale
    }
}
