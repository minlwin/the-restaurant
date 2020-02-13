import { Controller, Get } from '@nestjs/common';
import { SaleDetailsService } from '../model/saledetails.service';
import { BaseController } from 'src/common/base.controller';
import { SaleDetails } from '../model/saledetails.entity';

@Controller("orders")
export class SaleDetailsController extends BaseController<SaleDetails> {
 
    constructor(
        service:SaleDetailsService
    ) {
        super(service, '/orders')
    }
}
