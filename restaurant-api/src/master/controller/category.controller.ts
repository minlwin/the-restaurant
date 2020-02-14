import { Controller } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Category } from '../model/category.entity';
import { CategoryService } from '../model/category.service';

@Controller('categories')
export class CategoryController extends BaseControllerMutable<Category> {

    constructor(service:CategoryService) {
        super(service, '/categories')
    }
}
