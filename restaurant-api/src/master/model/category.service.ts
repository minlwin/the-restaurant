import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseServiceMutable } from 'src/common/base.service.mutable';
import { Repository } from 'typeorm';
import { Category } from './category.entity';

@Injectable()
export class CategoryService extends BaseServiceMutable<Category> {
    
    constructor(
        @InjectRepository(Category)
        repo:Repository<Category>) {
            super(repo)
        }
    
    search(name:string) {
        return this.repo.createQueryBuilder()
            .where("LOWER(name) like :name", 
            {name : `${name.toLocaleLowerCase()}%`}).getMany()
    }
}
