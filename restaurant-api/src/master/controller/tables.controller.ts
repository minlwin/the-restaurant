import { Controller, Get, Query, Post, Body, UseGuards } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Tables } from '../model/tables.entity';
import { TablesService } from '../model/tables.service';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller('tables')
export class TablesController extends BaseControllerMutable<Tables> {

    constructor(private readonly tblService:TablesService) {
        super(tblService, '/tables')
    }

    @Get('search')
    @UseGuards(JwtAuthGuard)
    search(@Query("number") tableNumber:string) {
        return this.tblService.search(tableNumber)
    }

    @Post('upload')
    @UseGuards(JwtAuthGuard)
    upload(@Body() tables:Tables[]) {
        return this.tblService.upload(tables)
    }
}
