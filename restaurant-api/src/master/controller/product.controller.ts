import { Controller, Get, Param, Query, Body, Post } from '@nestjs/common';
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

    @Get('search')
    search( @Query('type') type:string, @Query('categoryId') categoryId:number = 0, @Query('name') name:string) {
        return this.productservice.search(type, categoryId, name)
    }

    @Post('upload')
    upload(@Body() list:Product[]) {
        return this.productservice.upload(list)
    }
}
