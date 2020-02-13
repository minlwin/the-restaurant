import { Module } from '@nestjs/common';
import { SaleController } from './controller/sale.controller';
import { SaleDetailsController } from './controller/saledetails.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { SaleService } from './model/sale.service';
import { Sale } from './model/sale.entity';
import { SaleDetails } from './model/saledetails.entity';
import { SaleDetailsService } from './model/saledetails.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails
        ])
    ],
    controllers: [
        SaleController, 
        SaleDetailsController],
    providers: [
        SaleService,
        SaleDetailsService
    ],
})
export class SalesModule {}
