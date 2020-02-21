import { Controller, UseInterceptors, Get } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { ExcludeInterceptor } from 'src/common/exclude.interceptor';
import { Sale } from '../model/sale.entity';
import { SaleService } from '../model/sale.service';

@Controller("sales")
@UseInterceptors(new ExcludeInterceptor())
export class SaleController extends BaseControllerMutable<Sale> {

    constructor(
        private readonly saleService:SaleService
    ) { super(saleService, '/sales') }

    @Get('actives')
    getActives() {
        return this.saleService.getActiveVouchers()
    }
}
