import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Category } from './category.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class CategoryService extends BaseService<Category> {
    
    constructor(
        @InjectRepository(Category)
        repo:Repository<Category>) {
            super(repo)
        }
}
