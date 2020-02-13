import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CategoryController } from './controller/category.controller';
import { ProductController } from './controller/product.controller';
import { TablesController } from './controller/tables.controller';
import { Category } from './model/category.entity';
import { CategoryService } from './model/category.service';
import { Product } from './model/product.entity';
import { ProductService } from './model/product.service';
import { Tables } from './model/tables.entity';
import { TablesService } from './model/tables.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([Category, Product, Tables])
    ],
    controllers: [
        CategoryController,
        ProductController,
        TablesController
    ],
    providers: [
        CategoryService,
        ProductService,
        TablesService
    ],
})
export class MasterModule {}
