import { Controller, Get, Query, UseGuards } from '@nestjs/common';
import { SummaryService } from '../model/summary.service';
import { ChartService } from '../model/chart.service';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller('summary')
export class SummaryController {

    constructor(
        private readonly summaryService:SummaryService,
        private readonly chartService:ChartService
    ){}

    @Get()
    @UseGuards(JwtAuthGuard)
    getSummary() {
        return this.summaryService.getSummary()
    }

    @Get('chart')
    @UseGuards(JwtAuthGuard)
    getChartData(@Query('type') type?:string, 
        @Query('category') category?:number, 
        @Query('from') from?:string, 
        @Query('to') to?:string) {
            return this.chartService.search(type, category, from, to)
    }
}
