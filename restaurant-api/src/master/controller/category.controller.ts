import { Controller } from '@nestjs/common';
import { CategoryService } from '../model/category.service';
import { Category } from '../model/category.entity';
import { BaseController } from 'src/common/base.controller';

@Controller('categories')
export class CategoryController extends BaseController<Category> {

    constructor(service:CategoryService) {
        super(service, '/categories')
    }
}
