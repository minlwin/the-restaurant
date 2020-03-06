import { Controller, UseInterceptors, Get, Query, UseGuards } from '@nestjs/common';
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
    async getActives() {
        let result = await this.saleService.getActiveVouchers()
        return result
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
