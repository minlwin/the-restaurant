import { Controller, Get } from '@nestjs/common';
import { SummaryService } from '../model/summary.service';

@Controller('summary')
export class SummaryController {

    constructor(private readonly summaryService:SummaryService){}

    @Get()
    getSummary() {
        return this.summaryService.getSummary()
    }
}
