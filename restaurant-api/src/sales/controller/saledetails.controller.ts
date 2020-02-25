import { Controller, Get, Post, Body, Res, Put, Param, Query } from '@nestjs/common';
import { SaleDetailsService } from '../model/saledetails.service';
import { BaseController } from 'src/common/base.controller';
import { SaleDetails } from '../model/saledetails.entity';

@Controller("orders")
export class SaleDetailsController extends BaseController<SaleDetails> {
 
    constructor(
        private readonly detailsService:SaleDetailsService
    ) {
        super(detailsService)
    }

    @Get('search')
    search(@Query('table') table?:string, @Query('status') status?:string) {
        return this.detailsService.searchForStatus(table, status)
    }

    @Post('sale/:id')
    async createBySale(@Param('id') saleId:number, @Body() t:SaleDetails[], @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/sales/${savedResult.id}`)
    }

    @Put('sale/:id')
    async updateBySale(@Param('id') saleId:number, @Body() t:SaleDetails[], @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/sales/${savedResult.id}`)
    }     
}
