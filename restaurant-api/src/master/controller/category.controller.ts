import { Controller, Get, Query, Body, Post } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Category } from '../model/category.entity';
import { CategoryService } from '../model/category.service';
import { TYPES } from '../model/category.type'

@Controller('categories')
export class CategoryController extends BaseControllerMutable<Category> {

    constructor(private readonly myService:CategoryService) {
        super(myService, '/categories')
    }

    @Get('types')
    getTypes() {
        return TYPES
    }

    @Get('search')
    search(@Query('type') type:string, @Query("name") name:string) {
        return this.myService.search(type, name)
    }

    @Get('searchwithmenus')
    searchWithMenus(@Query('type') type:string, @Query('name') name:string) {
        return this.myService.searchWithMenuCount(type, name)
    }

    @Post('upload')
    upload(@Body() list:Category[]) {
        return this.myService.upload(list)
    }
}
