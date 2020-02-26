import { Controller, Get, Query, Body, Post } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Category } from '../model/category.entity';
import { CategoryService } from '../model/category.service';

@Controller('categories')
export class CategoryController extends BaseControllerMutable<Category> {

    constructor(private readonly myService:CategoryService) {
        super(myService, '/categories')
    }

    @Get('search')
    search(@Query("name") name:string) {
        return this.myService.search(name)
    }

    @Get('searchwithmenus')
    searchWithMenus(@Query('name') name:string) {
        return this.myService.searchWithMenuCount(name)
    }

    @Post('upload')
    upload(@Body() list:Category[]) {
        return this.myService.upload(list)
    }
}
