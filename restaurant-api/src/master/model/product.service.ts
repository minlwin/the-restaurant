import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Product } from './product.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class ProductService extends BaseService<Product> {

    constructor(
        @InjectRepository(Product)
        repo:Repository<Product>
    ) { super(repo) }

}
