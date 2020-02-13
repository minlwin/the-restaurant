import { Controller, Post, Body, Put, UseInterceptors } from '@nestjs/common';
import { SaleService } from '../model/sale.service';
import { Sale } from '../model/sale.entity';
import { BaseController } from 'src/common/base.controller';
import { ExcludeInterceptor } from 'src/common/exclude.interceptor';

@Controller("sales")
@UseInterceptors(new ExcludeInterceptor())
export class SaleController extends BaseController<Sale> {

    constructor(
        service:SaleService
    ) { super(service, '/sales') }
}
