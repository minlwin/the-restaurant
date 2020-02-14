import { Controller } from '@nestjs/common';
import { ProductService } from '../model/product.service';
import { Product } from '../model/product.entity';
import { BaseController } from 'src/common/base.controller';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';

@Controller('products')
export class ProductController extends BaseControllerMutable<Product> {

    constructor(service:ProductService) {
        super(service, '/products')
    }

}
