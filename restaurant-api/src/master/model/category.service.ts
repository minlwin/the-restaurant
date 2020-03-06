import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseServiceMutable } from 'src/common/base.service.mutable';
import { Repository } from 'typeorm';
import { Category } from './category.entity';
import { CategoryDTO } from './category.dto';

@Injectable()
export class CategoryService extends BaseServiceMutable<Category> {
    
    constructor(
        @InjectRepository(Category)
        repo:Repository<Category>) {
            super(repo)
        }

    search(type:string, name:string) {

        let query = this.repo.createQueryBuilder().where('type = :type', {type : type})

        if(name) {
            query = query.andWhere('LOWER(name) like :name', {name : `${name.toLocaleLowerCase()}%`})
        }

        return query.getMany()
    }
    
    searchWithMenuCount(type:string, name:string):Promise<CategoryDTO[]> {
        return this.repo.createQueryBuilder('c')
            .addSelect('c.id', 'id')
            .addSelect('c.type', 'type')
            .addSelect('c.name', 'name')
            .addSelect('count(p.id)', 'menus')
            .leftJoin('product', 'p', 'c.id = p.categoryId')
            .where("c.type = :type and LOWER(c.name) like :name", {
                type: type,
                name : `${name.toLocaleLowerCase()}%`
            })
            .groupBy('c.id')
            .getRawMany()
    }

    upload(list:Category[]) {
        return this.repo.save(list)
    }
}
