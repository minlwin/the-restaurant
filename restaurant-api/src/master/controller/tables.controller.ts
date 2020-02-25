import { Controller, Get, Query, Post, Body } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Tables } from '../model/tables.entity';
import { TablesService } from '../model/tables.service';

@Controller('tables')
export class TablesController extends BaseControllerMutable<Tables> {

    constructor(private readonly tblService:TablesService) {
        super(tblService, '/tables')
    }

    @Get('search')
    search(@Query("number") tableNumber:string) {
        return this.tblService.search(tableNumber)
    }

    @Post('upload')
    upload(@Body() tables:Tables[]) {
        return this.tblService.upload(tables)
    }
}
