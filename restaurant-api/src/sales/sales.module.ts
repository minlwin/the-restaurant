import { Module } from '@nestjs/common';
import { SaleController } from './controller/sale.controller';
import { SaleDetailsController } from './controller/saledetails.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { SaleService } from './model/sale.service';
import { Sale } from './model/sale.entity';
import { SaleDetails } from './model/saledetails.entity';
import { SaleDetailsService } from './model/saledetails.service';
import { MasterModule } from 'src/master/master.module';
import { Product } from 'src/master/model/product.entity';

@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails,
            Product
        ]),
        MasterModule
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
