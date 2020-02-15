import { Controller, Get, Param } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';

import { Product } from '../model/product.entity';
import { ProductService } from '../model/product.service';

@Controller('products')
export class ProductController extends BaseControllerMutable<Product> {

    constructor(private readonly productservice:ProductService) {
        super(productservice, '/products')
    }

    @Get('category/:id')
    findByCategory(@Param('id') categoryId:number) {
        return this.productservice.findByCategory(categoryId)
    }
}
