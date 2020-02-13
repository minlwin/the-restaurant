import { Controller } from '@nestjs/common';
import { ProductService } from '../model/product.service';
import { Product } from '../model/product.entity';
import { BaseController } from 'src/common/base.controller';

@Controller('products')
export class ProductController extends BaseController<Product> {

    constructor(service:ProductService) {
        super(service, '/products')
    }

}
