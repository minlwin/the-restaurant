import { Controller, Get, Query } from '@nestjs/common';
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
}
