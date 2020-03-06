import { Controller, Get, Query, Body, Post, UseGuards } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Category } from '../model/category.entity';
import { CategoryService } from '../model/category.service';
import { TYPES } from 'src/common/category.type';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller('categories')
export class CategoryController extends BaseControllerMutable<Category> {

    constructor(private readonly myService:CategoryService) {
        super(myService, '/categories')
    }

    @UseGuards(JwtAuthGuard)
    @Get('types')
    getTypes() {
        return TYPES
    }

    @UseGuards(JwtAuthGuard)
    @Get('search')
    search(@Query('type') type:string, @Query("name") name:string) {
        return this.myService.search(type, name)
    }

    @Get('searchwithmenus')
    @UseGuards(JwtAuthGuard)
    searchWithMenus(@Query('type') type:string, @Query('name') name:string) {
        return this.myService.searchWithMenuCount(type, name)
    }

    @Post('upload')
    @UseGuards(JwtAuthGuard)
    upload(@Body() list:Category[]) {
        return this.myService.upload(list)
    }
}
