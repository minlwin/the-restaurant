import { Module, Global } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Category } from './model/category.entity';
import { Product } from './model/product.entity';
import { Tables } from './model/tables.entity';
import { CategoryController } from './controller/category.controller';
import { ProductController } from './controller/product.controller';
import { TablesController } from './controller/tables.controller';
import { CategoryService } from './model/category.service';
import { ProductService } from './model/product.service';
import { TablesService } from './model/tables.service';

@Global()
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
