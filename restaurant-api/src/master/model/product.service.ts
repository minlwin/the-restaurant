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

    findByCategory(categoryId:number) {
        return this.repo.find({category : {id : categoryId}})
    }

    upload(list:Product[]) {
        return this.repo.save(list)
    }

    search(type:string, categoryId:number, name:string) {

        let query = this.repo.createQueryBuilder('p')
            .leftJoinAndSelect('p.category', 'c').where('1 = 1')

        if(categoryId) {
            query = query.andWhere('p.categoryId = :categoryId', {categoryId : categoryId})
        } else {
            query = query.andWhere('c.type = :type', {type : type})
        }

        if(name) {
            query = query.andWhere('LOWER(p.name) like :name or LOWER(p.code) like :code', 
                {
                    name: `${name}%`,
                    code: `${name}%`
                })
        }

        return query.getMany()
    }
}
