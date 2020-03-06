import { Controller, Get, Post, Body, Res, Put, Param, Query, UseGuards } from '@nestjs/common';
import { SaleDetailsService } from '../model/saledetails.service';
import { BaseController } from 'src/common/base.controller';
import { SaleDetails } from '../model/saledetails.entity';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller("orders")
export class SaleDetailsController extends BaseController<SaleDetails> {
 
    constructor(
        private readonly detailsService:SaleDetailsService
    ) {
        super(detailsService)
    }

    @Get('search')
    @UseGuards(JwtAuthGuard)
    search(@Query('table') table?:string, @Query('status') status?:string) {
        return this.detailsService.searchForStatus(table, status)
    }

    @Post('sale/:id')
    @UseGuards(JwtAuthGuard)
    async createBySale(@Param('id') saleId:number, @Body() t:SaleDetails[], @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/sales/${savedResult.id}`)
    }

    @Put('sale/:id')
    @UseGuards(JwtAuthGuard)
    async updateBySale(@Param('id') saleId:number, @Body() t:SaleDetails[], @Res() res:any) {
        let savedResult = await this.detailsService.saveBySale(saleId, t)
        res.redirect(`/sales/${savedResult.id}`)
    }     
}
