import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { currentDateEnd, currentDateStart } from 'src/common/date.utils';
import { TablesService } from 'src/master/model/tables.service';
import { Repository } from 'typeorm';
import { Sale } from './sale.entity';
import { SaleDetails } from './saledetails.entity';
import { SummaryDTO } from './summary.dto';
import moment = require('moment');

@Injectable()
export class SummaryService {

    constructor(
        private readonly tableService:TablesService,
        @InjectRepository(Sale)
        private readonly saleRepo:Repository<Sale>,
        @InjectRepository(SaleDetails)
        private readonly orderRepo:Repository<SaleDetails>
    ) {}

    async getSummary() {
        let summary:SummaryDTO = {} as SummaryDTO
        summary.pendingOrders = await this.getPendingOrders()
        summary.activeVouchers= await this.getActiveVoucher()
        summary.availableSeats = await this.getAvailableSeats()
        summary.saleToday = await this.getSaleForToday()
        return summary
    }

    private async getAvailableSeats():Promise<number> {

        let ids = await this.saleRepo
            .createQueryBuilder()
            .select('tablesId as id')
            .where('status = :status', {status : 'Active'}).getRawMany()

        let params = ids.map(a => a.id)

        return this.tableService.findActiveCount(params)
    }

    private async getActiveVoucher():Promise<number> {
        return this.saleRepo.createQueryBuilder()
            .where('status = :status', {status : 'Active'})
            .getCount()
    }

    private async getSaleForToday():Promise<number> {

        let sales = await this.saleRepo.createQueryBuilder()
            .where('saleDate >= :from and saleDate <= :to', {
                from : currentDateStart(),
                to: currentDateEnd()
            }).getMany()

        return sales.map(s => s.subTotal + s.tax).reduce((a, b) => a + b, 0)
    }

    private async getPendingOrders():Promise<number> {
        return this.orderRepo.createQueryBuilder()
            .where("status not in ('Finished', 'Cancel')")
            .getCount()
    }
}
