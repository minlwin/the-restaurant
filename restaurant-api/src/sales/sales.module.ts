import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { SaleController } from './controller/sale.controller';
import { SaleDetailsController } from './controller/saledetails.controller';
import { Sale } from './model/sale.entity';
import { SaleService } from './model/sale.service';
import { SaleDetails } from './model/saledetails.entity';
import { SaleDetailsService } from './model/saledetails.service';
import { SummaryController } from './controller/summary.controller';
import { SummaryService } from './model/summary.service';
import { Tables } from 'src/master/model/tables.entity';
import { MasterModule } from 'src/master/master.module';

@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails,
            Tables
        ]),
        MasterModule
    ],
    controllers: [
        SaleController, 
        SaleDetailsController,
        SummaryController
    ],
    providers: [
        SaleService,
        SaleDetailsService,
        SummaryService
    ],
})
export class SalesModule {}
