import { Controller, UseInterceptors, Get, Query, UseGuards, Param } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { ExcludeInterceptor } from 'src/common/exclude.interceptor';
import { Sale } from '../model/sale.entity';
import { SaleService } from '../model/sale.service';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller("sales")
@UseInterceptors(new ExcludeInterceptor())
export class SaleController extends BaseControllerMutable<Sale> {

    constructor(
        private readonly saleService:SaleService
    ) { super(saleService, '/sales') }

    @Get('actives')
    @UseGuards(JwtAuthGuard)
    getActives() {
        return this.saleService.getActiveVouchers()
    }

    @Get('actives/:table')
    @UseGuards(JwtAuthGuard)
    getActivesByTable(@Param('table') table:number) {
        return this.saleService.getActiveVouchersByTableId(table)
    }

    @Get('search')
    @UseGuards(JwtAuthGuard)
    search(@Query("from") from?:string, 
        @Query("to") to?:string, 
        @Query("status") status?:string, 
        @Query("tableNumber") tableNumber?:string) {
            return this.saleService.search(from, to, status, tableNumber)
    }


}
