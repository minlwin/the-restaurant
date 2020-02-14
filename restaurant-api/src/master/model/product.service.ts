import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseServiceMutable } from 'src/common/base.service.mutable';
import { Repository } from 'typeorm';
import { Product } from './product.entity';

@Injectable()
export class ProductService extends BaseServiceMutable<Product> {

    constructor(
        @InjectRepository(Product)
        repo:Repository<Product>
    ) { super(repo) }

}
