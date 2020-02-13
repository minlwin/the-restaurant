import { Controller, UseInterceptors } from '@nestjs/common';
import { BaseController } from 'src/common/base.controller';
import { ExcludeInterceptor } from 'src/common/exclude.interceptor';
import { Sale } from '../model/sale.entity';
import { SaleService } from '../model/sale.service';

@Controller("sales")
@UseInterceptors(new ExcludeInterceptor())
export class SaleController extends BaseController<Sale> {

    constructor(
        service:SaleService
    ) { super(service, '/sales') }
}
