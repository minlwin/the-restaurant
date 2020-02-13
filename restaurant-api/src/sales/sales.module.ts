import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Product } from 'src/master/model/product.entity';
import { SaleController } from './controller/sale.controller';
import { SaleDetailsController } from './controller/saledetails.controller';
import { Sale } from './model/sale.entity';
import { SaleService } from './model/sale.service';
import { SaleDetails } from './model/saledetails.entity';
import { SaleDetailsService } from './model/saledetails.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([
            Sale,
            SaleDetails,
            Product
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
